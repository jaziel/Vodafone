package nz.co.vodafone.rest.resource;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;
import org.junit.Test;

import nz.co.vodafone.rest.model.Customer;

public class CustomersResourceTest extends JerseyTestNg.ContainerPerClassTest {


	@Override
	protected Application configure() {
        return new ResourceConfig(CustomersResource.class);
    }
	
	@Test
	public void createCustomerTest() throws Exception{
		Customer customer = new Customer("Jaziel", "30 Randolph ST, Acukland", "+640212950648");
		Response response = target().path("customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.CREATED.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void getCustomersTest() throws Exception {
		Response response = target().path("customers").request().get();
		List<Customer> customers = response.readEntity(new GenericType<List<Customer>>(){});
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals(3	, customers.size());
	}
	
	@Test
	public void getCustomerTest() throws Exception {
		Response response = target().path("customers/" + 1).request(MediaType.APPLICATION_JSON).get();
		Customer customer = response.readEntity(Customer.class);
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Jaziel", customer.getName());
		
	}
	
	@Test
	public void updateCustomerTest() throws Exception {
		Customer customer = new Customer("Jaziel", "3A/30 Randolph ST, Acukland", "+640212950648");
		Response response = target().path("customers/" + 1).request(MediaType.APPLICATION_JSON).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void deleteCustomersTest() throws Exception {
		Response response = target().path("customers").request().delete();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		Response response = target().path("customers/" + 3 ).request().delete();
		assertEquals(Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void whenSavingCustomerNameIsEmptyThenError(){
		Customer customer = new Customer("","33 Randolph St, Auckland","0212982345");
		Response response = target().path("customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void whenSavingCustomerPhoneIsEmptyThenError(){
		Customer customer = new Customer("Luis","34 Randolph St, Auckland","");
		Response response = target().path("customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void whenDeletingInvalidCustomerThenNoModify(){
		Response response = target().path("customers/" + 11 ).request().delete();
		assertEquals(Status.NOT_MODIFIED.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void whenUpdatingInvalidCustomerThenError(){
		Customer customer = new Customer(11,"Jaziel F. Leandro", "55 Randolph St, Auckland", "+640212223344");
		Response response = target().path("customers/" + 11).request(MediaType.APPLICATION_JSON).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
		
	}
	
	@Test
	public void whenSavingInvalidCustomerPhoneNumberThenError(){
		Customer customer = new Customer("Jaziel F. Leandro", "55 Randolph St, Auckland", "+asvxc");
		Response response = target().path("customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void whenGetingInvalidCustomerThenNoContent(){
		Response response = target().path("customers/" + 10).request(MediaType.APPLICATION_JSON).get();
		assertEquals(Status.NO_CONTENT.getStatusCode(), response.getStatus());
		
	}
	
	@Test
	public void whenSavingInvalidCharCustomerNameThenError(){
		Customer customer = new Customer("Luis+_)(","55 Randolph St, Auckland","+640212223344");
		Response response = target().path("customers").request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
	}

}
