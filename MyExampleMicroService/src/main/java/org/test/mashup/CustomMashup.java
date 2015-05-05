package org.test.mashup;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.rx.rxjava.RxObservable;
import org.glassfish.jersey.server.Uri;
import org.test.entity.Customer;
import org.test.helper.APIPaths;

import rx.Observable;
import rx.schedulers.Schedulers;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixObservableCommand;

/**
 * Custom mashup class which uses RxJava and Hystrix for managing the asynchronous calls to the backend
 * service(s).
 * @author r.witte
 *
 */

@Singleton
@Path( APIPaths.CUSTOMERS_HYSTRIX )
@Produces( MediaType.APPLICATION_JSON )
public class CustomMashup {

    private static Logger LOGGER = Logger.getLogger( CustomMashup.class.getName() );
    private static final int CUSTOMCOMMANDTIMEOUT = 900;

    @Uri( APIPaths.CUSTOMERS )
    private WebTarget customerWebTarget;

    @GET
    public void observable( @Suspended
    final AsyncResponse async ) {
        new CustomerCommand( APIPaths.CUSTOMERS_HYSTRIX, CUSTOMCOMMANDTIMEOUT ).observe().observeOn( Schedulers.io() ).subscribe( async::resume );
    }

    private class CustomerCommand
        extends CommonCommand<List<Customer>> {

        public CustomerCommand( String path, int timeout ) {
            super( path, timeout );
        }

        @Override
        protected Observable<List<Customer>> resumeWithFallback() {
            handleErrors();
            return Observable.just( Collections.emptyList() );
        }

        @Override
        protected Observable<List<Customer>> construct() {
            return RxObservable.from( customerWebTarget ).request().rx().get( new GenericType<List<Customer>>() {
            } );
        }
    }

    private abstract class CommonCommand<R>
        extends HystrixObservableCommand<R> {
        private final String path;

        public CommonCommand( String path, int timeout ) {
            super( Setter.withGroupKey( HystrixCommandGroupKey.Factory.asKey( "MyExampleMicroServiceCommand" ))
                   .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                                                 .withExecutionTimeoutInMilliseconds(timeout)));
            this.path = path;
        }

        protected void handleErrors() {
            final String message;
            if ( isFailedExecution() ) {
                message = getMessagePrefix() + "failed: " + getFailedExecutionException().getMessage();
            }
            else if ( isResponseTimedOut() ) {
                message = getMessagePrefix() + "timed out";
            }
            else {
                message = getMessagePrefix() + "has some unknown failure";
            }
            LOGGER.warning( message );
        }

        private String getMessagePrefix() {
            return this.getClass().getSimpleName() + " [ path = " + path + "]: ";
        }
    }

}
