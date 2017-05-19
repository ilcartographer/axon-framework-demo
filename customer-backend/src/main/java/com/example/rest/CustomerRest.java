package com.example.rest;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mamiller
 * Date: 3/28/17
 * Time: 11:17 AM
 */
@Named
@Path("/")
public class CustomerRest {

    private static List<Customer> customers = new ArrayList<>();

    static {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("Customer 1");
        customer1.setEmail("customer1@gmail.com");

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("Customer 2");
        customer2.setEmail("customer2@gmail.com");

        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setName("Customer 3");
        customer3.setEmail("customer3@gmail.com");

        Customer customer4 = new Customer();
        customer4.setId(4);
        customer4.setName("Customer 4");
        customer4.setEmail("customer4@gmail.com");

        Customer customer5 = new Customer();
        customer5.setId(5);
        customer5.setName("Customer 5");
        customer5.setEmail("customer5@gmail.com");

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
        customers.add(customer5);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
        return customers;
    }

    @GET
    @Path("customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@QueryParam("id") long id) {
        Customer cli = null;

        for(Customer c : customers) {
            if(c.getId() == id)
                cli = c;
        }

        return cli;
    }
}
