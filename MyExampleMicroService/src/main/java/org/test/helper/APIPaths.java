package org.test.helper;

public final class APIPaths {

    public static final byte MAJOR_VERSION = 0;

    private static final byte MINOR_VERSION = 1;

    private static final byte PATCH_VERSION = 0;

    public static final String CURRENT_VERSION = "v" + MAJOR_VERSION + "." + MINOR_VERSION + "." + PATCH_VERSION;
    
    public static final String CUSTOMERS = CURRENT_VERSION + "/customers";
    
    public static final String CUSTOMERS_HYSTRIX = CURRENT_VERSION + "/hystrix";

}
