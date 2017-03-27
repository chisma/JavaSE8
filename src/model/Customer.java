package model;

public class Customer {

	private static int customerNo = 1;
	private int customerId;
	private String name;
	private String email;

	public Customer(String name, String email) {
		customerId = customerNo++;
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer id: " + customerId + ", Name: " + name + ", Email: " + email;
	}

}

