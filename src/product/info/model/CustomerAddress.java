package product.info.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_address database table.
 * 
 */
@Entity
@Table(name="customer_address")
@NamedQuery(name="CustomerAddress.findAll", query="SELECT c FROM CustomerAddress c")
public class CustomerAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public CustomerAddress() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}