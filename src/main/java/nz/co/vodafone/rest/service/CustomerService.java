package nz.co.vodafone.rest.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nz.co.vodafone.rest.model.Customer;

public class CustomerService {
	
	private Map<Integer, Customer> customerMap;
	private int count = 1;
	private static CustomerService instance = new CustomerService();
	
	
	private CustomerService(){
		customerMap = new HashMap<Integer, Customer>();
		Customer customer = new Customer(count++, "Jaziel", "30 Randolph ST, Auckland", "+640212950648");
		customerMap.put(customer.getId(), customer);
		customer = new Customer(count++, "Fernando", "31 Randolph ST, Auckland", "+640212824326");
		customerMap.put(customer.getId(), customer);
		customer = new Customer(count++, "Leandro", "32 Randolph ST, Auckland", "+640212167844");
		customerMap.put(customer.getId(), customer);
	}
	
	public static CustomerService getInstance(){
		return instance;
	}

	public Customer createCustomer(Customer customer){
		customer.setId(count++);
		customerMap.put(customer.getId(), customer);
		return customer;
	}
	
	public Customer updateCustomer(Integer id, Customer customer){
		if(customerMap.containsKey(id)){
			customerMap.put(id, customer);
		} else{
			return null;
		}
		return customer;
	}
	
	public Collection<Customer> getAllCustomer(){
		return customerMap.values();
	}
	
	public Customer getCustomer(Integer id){
		return customerMap.get(id);
	}

	public void deleteCustomers() {
		customerMap.clear();
	}

	public Customer deleteCustomer(int id) {
		return customerMap.remove(id);
		
	}

}
