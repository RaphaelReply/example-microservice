package org.test.entity;

/** 
 * Class for the entity Customer.
 * @author r.witte
 *
 */
public class Customer {

    private long customerId;

    private String salutation, firstname, lastname;

    private boolean active;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId( long bookId ) {
        this.customerId = bookId;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation( String salutation ) {
        this.salutation = salutation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive( boolean active ) {
        this.active = active;
    }

    // default constructor - needed
    public Customer() {

    }

    public Customer( long customerId, String salutation, String firstname, String lastname ) {
        this.customerId = customerId;
        this.salutation = salutation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = true;
    }

    public Customer( String salutation, String firstname, String lastname ) {
        this.customerId = (long) ( Math.random() * Math.pow( 10, 10 ) );
        this.salutation = salutation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = true;
    }

}
