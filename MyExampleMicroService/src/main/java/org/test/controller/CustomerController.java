package org.test.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.test.entity.Customer;
import org.test.error.AppException;
import org.test.helper.APIPaths;

/**
 * This controller class is the facade for the RESTful API of the customer section. IMPORTANT NOTE: It is very
 * simplified, so no persistence of the data is done here.
 * 
 * @author r.witte
 */

@Path( APIPaths.CUSTOMERS )
@Component
public class CustomerController {

    private ArrayList<Customer> customers = new ArrayList<Customer>( Arrays.asList( new Customer( "Herr", "Sven",
                                                                                                  "Brenner" ) ) );

    // Get the collection of all customers
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public List<Customer> getCustomer()
        throws AppException {

        if ( customers.isEmpty() )
            throw new AppException(
                                    Response.Status.NOT_FOUND.getStatusCode(), // 404
                                    404, "No customers found!",
                                    "There are not customers currently present in the ArrayList" );

        return customers;
    }

    // Get a specific customer by id
    @Path( "{id}" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public Customer getCustomerById( @PathParam( "id" ) Long id )
        throws AppException {

        return findCustomerById( id );
    }

    // Create a new customer
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_HTML )
    public Response createCustomer( Customer customer )
        throws AppException {

        if ( customer == null )
            throw new AppException(
                                    Response.Status.BAD_REQUEST.getStatusCode(), // 400
                                    400, "No creation possible",
                                    "The customers was null, so no customer was given via JSON as input" );

        customer.setCustomerId( generateId() );
        customer.setActive( true );
        customers.add( customer );

        return Response.status( Response.Status.OK )// 200
        .entity( "The customer was created with ID " + customer.getCustomerId() ).build();
    }

    // Partial update - simplified to constantly changing the salutation
    @POST
    @Path( "{id}" )
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.TEXT_HTML )
    public Response partialUpdateCustomer( @PathParam( "id" ) Long id, Customer customer )
        throws AppException {

        Customer customerToChange = findCustomerById( id );

        if ( customer.getSalutation() == null ) {
            throw new AppException(
                                    Response.Status.BAD_REQUEST.getStatusCode(), // 400
                                    400, "No update possible. Please specify the new salutation",
                                    "The salutation was null, so no salutation was given via JSON as input" );
        }

        customerToChange.setSalutation( customer.getSalutation() );

        return Response.status( Response.Status.OK )// 200
        .entity( "The customer salutation has been changed to " + customer.getSalutation()
                     + " for the customer with ID " + id ).build();
    }

    private Customer findCustomerById( long id )
        throws AppException {
        for ( Customer cur : customers ) {
            if ( cur.getCustomerId() == id ) {
                return cur;
            }
        }
        throw new AppException( Response.Status.NOT_FOUND.getStatusCode(), // 404
                                404, "The requested customer was not found", "Check if the customer with id " + id
                                    + " really exists in the ArrayList" );
    }

    private long generateId() {
        return (long) ( Math.random() * Math.pow( 10, 10 ) );
    }
}
