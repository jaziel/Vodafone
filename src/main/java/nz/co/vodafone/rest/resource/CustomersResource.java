package nz.co.vodafone.rest.resource;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import nz.co.vodafone.rest.model.Customer;
import nz.co.vodafone.rest.service.CustomerService;

@Path("/customers")
public class CustomersResource {

	@Context
	Request request;
	@Context
	UriInfo uriInfo;
	private CustomerService service = CustomerService.getInstance();
	private String phoneNumberExpression = "^\\+[1-9]{1}[0-9]{3,14}$";
	private String nameExpression = "^[\\p{L} .'-]+$";

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getCustomers() {
		return service.getAllCustomer();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Customer getCustomer(@PathParam("id") int id) {
		return service.getCustomer(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer) throws Exception {
		Response response;
		if(customer.getName().isEmpty()){
			throw new Exception("Customer name is empty!");
		}else{
			if(!customer.getName().matches(nameExpression)){
				throw new Exception("Customer name is invalid!");
			}
		}
		if(customer.getTelephone().isEmpty()){
			throw new Exception("Customer telephone is empty!");
		}else{
			if(!customer.getTelephone().matches(phoneNumberExpression)){
				throw new Exception("Customer telephone is invalid!");
			}
		}
		customer = service.createCustomer(customer);
		response = Response.created(uriInfo.getAbsolutePath()).build();
		return response;
	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") int id, Customer customer) {
		Response response;
		customer = service.updateCustomer(id, customer);
		if(customer == null){
			response = Response.serverError().build();
		}else{
			response = Response.ok(customer).build();
		}
		return response;
	}
	
	@DELETE
	public void deleteCustomers(){
		service.deleteCustomers();
	}

	@DELETE
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") int id) {
		Response response;
		Customer customer = service.deleteCustomer(id);
		if(customer == null){
			response = Response.notModified().build();
		}else{
			response = Response.ok().build();
		}
		return response;
	}
}
