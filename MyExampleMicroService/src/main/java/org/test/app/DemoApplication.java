package org.test.app;

import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.server.ResourceConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Basic application to register all resources.
 * 
 * @author r.witte
 */

public class DemoApplication
    extends ResourceConfig {

    public DemoApplication() {
        packages( "org.test" );
    }

    /**
     * The ObjectMapper to serialize the Rx Obsersables of the service methods to JSON via Jackson
     * 
     * @author r.witte
     */
//    public static class ObjectMapperProvider
//        implements ContextResolver<ObjectMapper> {
//
//        @Override
//        public ObjectMapper getContext( final Class<?> type ) {
//            return new ObjectMapper().enable( SerializationFeature.INDENT_OUTPUT );
//        }
//    }

}