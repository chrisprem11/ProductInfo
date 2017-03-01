package product.info.DTO;

public class ItemDTO {

	private String itemName;
	private double itemCost;
	private double additionalCost;
	private String additonalField;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemCost() {
		return itemCost;
	}

	public void setItemCost(double itemCost) {
		this.itemCost = itemCost;
	}

	public double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(double additionalCost) {
		this.additionalCost = additionalCost;
	}

	public String getAdditonalField() {
		return additonalField;
	}

	public void setAdditonalField(String additonalField) {
		this.additonalField = additonalField;
	}

}
