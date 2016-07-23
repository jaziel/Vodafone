package nz.co.vodafone.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {

	private int id;
	private String name;
	private String address;
	private String telephone;

	public Customer() {
		super();
	}

	public Customer(String name, String address, String telephone) {
		super();
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}
	
	public Customer(int id, String name, String address, String telephone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
