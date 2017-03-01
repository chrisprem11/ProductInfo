package product.info.DTO;

public class CustomerDTO {

	private int invoiceId;

	private String firstName;

	private String lastName;

	private int contact;

	private String additonalDetailOne;

	private String additonalDetailTwo;

	private String address;

	private double customerAmount;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getAdditonalDetailOne() {
		return additonalDetailOne;
	}

	public void setAdditonalDetailOne(String additonalDetailOne) {
		this.additonalDetailOne = additonalDetailOne;
	}

	public String getAdditonalDetailTwo() {
		return additonalDetailTwo;
	}

	public void setAdditonalDetailTwo(String additonalDetailTwo) {
		this.additonalDetailTwo = additonalDetailTwo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getCustomerAmount() {
		return customerAmount;
	}

	public void setCustomerAmount(double customerAmount) {
		this.customerAmount = customerAmount;
	}

}
