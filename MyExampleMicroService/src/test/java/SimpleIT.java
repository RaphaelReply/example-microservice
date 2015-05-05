import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.junit.Assert;
import org.junit.Test;
import org.test.entity.Customer;
import org.test.helper.APIPaths;


public class SimpleIT {

    @Test
    public void testGetCustomers() {

        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(new JacksonFeature());
        clientConfig.register(Customer.class);
 
        Client client = ClientBuilder.newClient(clientConfig);
 
        WebTarget webTarget = client
                .target("http://localhost:8080/" + APIPaths.CUSTOMERS);
 
        Builder request = webTarget.request(MediaType.APPLICATION_JSON);
 
        Response response = request.get();
        Assert.assertTrue(response.getStatus() == 200);
                           
        Customer[] customer = response.readEntity(Customer[].class);

        Assert.assertTrue("At least one customer is present",
                          customer.length > 0);
    }
}