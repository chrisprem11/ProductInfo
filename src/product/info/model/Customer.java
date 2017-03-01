package product.info.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="additonal_detail_one")
	private String additonalDetailOne;

	@Column(name="additonal_detail_two")
	private String additonalDetailTwo;

	private int contact;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to CustomerAddress
	@OneToMany(mappedBy="customer",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private List<CustomerAddress> customerAddresses;

	//bi-directional many-to-one association to Discount
	@OneToMany(mappedBy="customer")
	private List<Discount> discounts;

	//bi-directional many-to-one association to Invoice
	@OneToMany(mappedBy="customer")
	private List<Invoice> invoices;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdditonalDetailOne() {
		return this.additonalDetailOne;
	}

	public void setAdditonalDetailOne(String additonalDetailOne) {
		this.additonalDetailOne = additonalDetailOne;
	}

	public String getAdditonalDetailTwo() {
		return this.additonalDetailTwo;
	}

	public void setAdditonalDetailTwo(String additonalDetailTwo) {
		this.additonalDetailTwo = additonalDetailTwo;
	}

	public int getContact() {
		return this.contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<CustomerAddress> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddress addCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddress removeCustomerAddress(CustomerAddress customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

	public List<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Discount addDiscount(Discount discount) {
		getDiscounts().add(discount);
		discount.setCustomer(this);

		return discount;
	}

	public Discount removeDiscount(Discount discount) {
		getDiscounts().remove(discount);
		discount.setCustomer(null);

		return discount;
	}

	public List<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public Invoice addInvoice(Invoice invoice) {
		getInvoices().add(invoice);
		invoice.setCustomer(this);

		return invoice;
	}

	public Invoice removeInvoice(Invoice invoice) {
		getInvoices().remove(invoice);
		invoice.setCustomer(null);

		return invoice;
	}

}