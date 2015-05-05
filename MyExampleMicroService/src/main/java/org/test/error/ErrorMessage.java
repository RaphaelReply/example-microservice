package org.test.error;

import java.lang.reflect.InvocationTargetException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Class for the representation of the error message which is shown to the API caller in case the call is not
 * successful.
 * 
 * @author r.witte
 */
public class ErrorMessage {

    private int http_status_code, app_error_code;

    private String message, developerMessage;

    public ErrorMessage( AppException ex ) {
        try {
            BeanUtils.copyProperties( this, ex );
        }
        catch ( IllegalAccessException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch ( InvocationTargetException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ErrorMessage( NotFoundException ex ) {
        this.http_status_code = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
    }

    public ErrorMessage() {

    }

    public int getHttp_status_code() {
        return http_status_code;
    }

    public void setHttp_status_code( int http_status_code ) {
        this.http_status_code = http_status_code;
    }

    public int getApp_error_code() {
        return app_error_code;
    }

    public void setApp_error_code( int app_error_code ) {
        this.app_error_code = app_error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage( String developerMessage ) {
        this.developerMessage = developerMessage;
    }

}
