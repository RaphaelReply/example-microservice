package org.test.error;

/**
 * Custom exception class for provisioning a detailed error message if an API call is not successful. 
 * @author r.witte
 *
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -6350844131765839646L;
    
    private int http_status_code, app_error_code; 
    private String message, developerMessage;	
	
    /**
     * Constructor for creation of an AppExection with provision of all parameters.
     * @param http_status_code - the HTTP status code
     * @param app_error_code - the internal error code (more differentiated error code than HTTP error code)
     * @param message - the message for end users of the API
     * @param developerMessage - a more detailed message for developers 
     */
	public AppException(int status, int app_error_code, String message,
			String developerMessage) {
		super(message);
		this.http_status_code = status;
		this.app_error_code = app_error_code;
		this.message = message;
		this.developerMessage = developerMessage;
	}

	public AppException() { }

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
