package product.info.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shop_address database table.
 * 
 */
@Entity
@Table(name="shop_address")
@NamedQuery(name="ShopAddress.findAll", query="SELECT s FROM ShopAddress s")
public class ShopAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String address;

	//bi-directional many-to-one association to Shop
	@ManyToOne
	private Shop shop;

	public ShopAddress() {
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

	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}