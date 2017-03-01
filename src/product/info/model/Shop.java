package product.info.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shop database table.
 * 
 */
@Entity
@NamedQuery(name="Shop.findAll", query="SELECT s FROM Shop s")
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="shop_detail_one")
	private String shopDetailOne;

	@Column(name="shop_name")
	private String shopName;

	//bi-directional many-to-one association to ShopAddress
	@OneToMany(mappedBy="shop")
	private List<ShopAddress> shopAddresses;

	public Shop() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopDetailOne() {
		return this.shopDetailOne;
	}

	public void setShopDetailOne(String shopDetailOne) {
		this.shopDetailOne = shopDetailOne;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<ShopAddress> getShopAddresses() {
		return this.shopAddresses;
	}

	public void setShopAddresses(List<ShopAddress> shopAddresses) {
		this.shopAddresses = shopAddresses;
	}

	public ShopAddress addShopAddress(ShopAddress shopAddress) {
		getShopAddresses().add(shopAddress);
		shopAddress.setShop(this);

		return shopAddress;
	}

	public ShopAddress removeShopAddress(ShopAddress shopAddress) {
		getShopAddresses().remove(shopAddress);
		shopAddress.setShop(null);

		return shopAddress;
	}

}