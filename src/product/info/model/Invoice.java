package product.info.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the invoice database table.
 * 
 */
@Entity
@NamedQuery(name="Invoice.findAll", query="SELECT i FROM Invoice i")
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="additional_cost")
	private double additionalCost;

	@Column(name="discount_additional_cost")
	private double discountAdditionalCost;

	@Column(name="discount_item_cost")
	private double discountItemCost;

	@Column(name="discount_net_amount")
	private double discountNetAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="expected_amount")
	private double expectedAmount;

	@Column(name="final_amount")
	private double finalAmount;

	@Column(name="net_amount")
	private double netAmount;

	@Column(name="order_status")
	private String orderStatus;

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;
	
	
	private String orderDetails;
	
	private String month;
	
	private double amountDeviation;
	
	private double customerGivenAmount;
	
	private String invoiceNumber;
	
	public Invoice() {
	}

	public String getOrderDetails() {
		return orderDetails;
	}



	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
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

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getExpectedAmount() {
		return this.expectedAmount;
	}

	public void setExpectedAmount(double expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public double getFinalAmount() {
		return this.finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public double getNetAmount() {
		return this.netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public double getAmountDeviation() {
		return amountDeviation;
	}

	public void setAmountDeviation(double amountDeviation) {
		this.amountDeviation = amountDeviation;
	}

	public double getCustomerGivenAmount() {
		return customerGivenAmount;
	}

	public void setCustomerGivenAmount(double customerGivenAmount) {
		this.customerGivenAmount = customerGivenAmount;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	
}