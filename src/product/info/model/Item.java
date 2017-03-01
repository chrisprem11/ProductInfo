package product.info.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="additional_cost")
	private double additionalCost;

	@Column(name="additonal_field")
	private String additonalField;

	@Column(name="item_cost")
	private double itemCost;

	@Column(name="item_name")
	private String itemName;
	
	
	private int quantity;

	//bi-directional many-to-one association to Discount
	@OneToMany(mappedBy="item")
	private List<Discount> discounts;

	public Item() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAdditionalCost() {
		return this.additionalCost;
	}

	public void setAdditionalCost(double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public String getAdditonalField() {
		return this.additonalField;
	}

	public void setAdditonalField(String additonalField) {
		this.additonalField = additonalField;
	}

	public double getItemCost() {
		return this.itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<Discount> getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

	public Discount addDiscount(Discount discount) {
		getDiscounts().add(discount);
		discount.setItem(this);

		return discount;
	}

	public Discount removeDiscount(Discount discount) {
		getDiscounts().remove(discount);
		discount.setItem(null);

		return discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}