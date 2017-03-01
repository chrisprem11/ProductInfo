package product.info.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the discount database table.
 * 
 */
@Entity
@NamedQuery(name="Discount.findAll", query="SELECT d FROM Discount d")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="discount_additional_cost")
	private double discountAdditionalCost;

	@Column(name="discount_item_cost")
	private double discountItemCost;

	@Column(name="discount_net_amount")
	private double discountNetAmount;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	//bi-directional many-to-one association to Item
	@ManyToOne
	private Item item;

	public Discount() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDiscountAdditionalCost() {
		return this.discountAdditionalCost;
	}

	public void setDiscountAdditionalCost(double discountAdditionalCost) {
		this.discountAdditionalCost = discountAdditionalCost;
	}

	public double getDiscountItemCost() {
		return this.discountItemCost;
	}

	public void setDiscountItemCost(double discountItemCost) {
		this.discountItemCost = discountItemCost;
	}

	public double getDiscountNetAmount() {
		return this.discountNetAmount;
	}

	public void setDiscountNetAmount(double discountNetAmount) {
		this.discountNetAmount = discountNetAmount;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}