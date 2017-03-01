package product.info.DTO;

public class InvoiceDTO {

	private String itemName;
	
	private int quantity;
	
	private int contact;
	
	private double discountAdditionalCost;

	private double discountItemCost;

	private double discountNetAmount;
	
	private String invoiceNumber;


	public InvoiceDTO() {

	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getContact() {
		return contact;
	}


	public void setContact(int contact) {
		this.contact = contact;
	}


	public double getDiscountAdditionalCost() {
		return discountAdditionalCost;
	}


	public void setDiscountAdditionalCost(double discountAdditionalCost) {
		this.discountAdditionalCost = discountAdditionalCost;
	}


	public double getDiscountItemCost() {
		return discountItemCost;
	}


	public void setDiscountItemCost(double discountItemCost) {
		this.discountItemCost = discountItemCost;
	}


	public double getDiscountNetAmount() {
		return discountNetAmount;
	}


	public void setDiscountNetAmount(double discountNetAmount) {
		this.discountNetAmount = discountNetAmount;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	

}
