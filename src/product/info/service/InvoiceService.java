package product.info.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.info.DTO.CustomerDTO;
import product.info.DTO.InvoiceDTO;
import product.info.model.Customer;
import product.info.model.Invoice;
import product.info.model.Item;
import product.info.repository.CustomerRepository;
import product.info.repository.DiscountRepository;
import product.info.repository.InvoiceRepository;
import product.info.repository.ItemRepository;

@Service
public class InvoiceService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	DiscountRepository discountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	InvoiceRepository invoiceRepository;

	
	public String getMonth(int number) {
		String month = "";
		switch (number) {
		case 0:
			month = "January";
			break;
		case 1:
			month = "February";
			break;
		case 2:
			month = "March";
			break;
		case 3:
			month = "April";
			break;
		case 4:
			month = "May";
			break;
		case 5:
			month = "June";
			break;
		case 6:
			month = "July";
			break;
		case 7:
			month = "August";
			break;
		case 8:
			month = "September";
			break;
		case 9:
			month = "October";
			break;
		case 10:
			month = "November";
			break;
		case 11:
			month = "December";
			break;
		}
		return month;
	}

	public List<String> getOrderDetails(List<Item> selections) {

		List<String> orderDetails = new ArrayList<String>();
		if (selections != null) {
			String quantity = "";
			for (Item object : selections) {
				quantity = String.valueOf(object.getQuantity());
				orderDetails.add("{ " + object.getItemName() + " | " + object.getItemCost() + " | " + quantity + " }");
			}

		}
		return orderDetails;
	}

	public List<Invoice> getInvoiceList() {
		List<Invoice> invoiceList = invoiceRepository.findAll();
		return invoiceList;
	}

	public Invoice getOneInvoice(Integer id) {
		Invoice oneInvoice = invoiceRepository.findById(id);
		return oneInvoice;
	}

	public void completeOrder(CustomerDTO customerDTO, Invoice oneInvoice) {

		long millis = System.currentTimeMillis();
		Date endDate = new Date(millis);
		double amountDeviation = (oneInvoice.getFinalAmount() - customerDTO.getCustomerAmount());
		oneInvoice.setOrderStatus("complete");
		oneInvoice.setEndDate(endDate);
		oneInvoice.setCustomerGivenAmount(customerDTO.getCustomerAmount());
		oneInvoice.setAmountDeviation(amountDeviation);
		invoiceRepository.save(oneInvoice);
	}

	/*
	 * This method is used to calculate the final amount after applying all the
	 * three discounts. This method also generates one bill amount per Order.
	 * 
	 * @param invoiceDTO,selections
	 * 
	 * @return generated invoice object
	 */
	public Invoice createInvoice(InvoiceDTO invoiceDTO, List<Item> selections) {

		// Get the customer first
		Customer currentCustomer = customerRepository.findByContact(invoiceDTO.getContact());

		// Conversion of all discount figures into respective decimals
		double discountPercentItemCost = Math.floor((invoiceDTO.getDiscountItemCost() / 100) * 100) / 100;
		double discountPercentAdditionalCost = Math.floor((invoiceDTO.getDiscountAdditionalCost() / 100) * 100) / 100;
		double discountPercentNetAmount = Math.floor((invoiceDTO.getDiscountNetAmount() / 100) * 100) / 100;

		// Calculation of all the three discount amount
		List<Double> priceList = new ArrayList<Double>();
		Item item = new Item();
		double netAmount = 0;
		for (int i = 0; i < selections.size(); i++) {
			item = itemRepository.findByItemName(selections.get(i).getItemName());
			int quantity = selections.get(i).getQuantity();
			double itemBasePrice = item.getItemCost();
			double itemAdditionalCost = item.getAdditionalCost();
			double totalCost = (itemBasePrice + itemAdditionalCost);
			double finalAmount = totalCost * quantity;
			netAmount += finalAmount;
			
			// Discount Calculation - On ItemCost and Additional Cost
			double discountOnItemCost = (discountPercentItemCost * itemBasePrice);
			double discountedAmountOnItemCost = (itemBasePrice - discountOnItemCost);
			double discountOnAdditionalCost = (discountPercentAdditionalCost * itemAdditionalCost);
			double discountedAmountOnAdditionalCost = (itemAdditionalCost - discountOnAdditionalCost);
			double totalDiscountedNetAmount = ((discountedAmountOnItemCost + discountedAmountOnAdditionalCost)
					* quantity);

			// Discount on sumTOtal
			double discountOnNetAmount = (discountPercentNetAmount * totalDiscountedNetAmount);
			double finalAmountToBePaid = Math.round((totalDiscountedNetAmount - discountOnNetAmount));
			priceList.add(finalAmountToBePaid);
		}

		// Calculation of all discounted amount in the final price list
		double finalPriceAfterAllDeductions = 0;
		for (int i = 0; i < priceList.size(); i++) {
			finalPriceAfterAllDeductions += priceList.get(i);
		}
		Invoice generateInvoice = completeOrderAndGenerateTheInvoice(invoiceDTO, selections,
				finalPriceAfterAllDeductions, currentCustomer, netAmount);
		return generateInvoice;
	}

	/*
	 * This method is used to save the order details and generate the complete invoice.
	 */
	private Invoice completeOrderAndGenerateTheInvoice(InvoiceDTO invoiceDTO, List<Item> selections,
			double finalPriceAfterAllDeductions, Customer currentCustomer, double netAmount) {
		
		// Set Month of the sale
		long startTime = System.currentTimeMillis();
		Date startDate = new Date(startTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int monthNumber = cal.get(Calendar.MONTH);
		String currentMonth = getMonth(monthNumber);

		String invoicePrefix = invoiceDTO.getInvoiceNumber();
		int invoiceSuffix = (int) (Math.random() * 100);
		String invoiceNumber = invoicePrefix + invoiceSuffix;

		List<String> orderDetails = getOrderDetails(selections);

		double totalAdditionalCost = 0;
		for (int i = 0; i < selections.size(); i++) {
			totalAdditionalCost += (selections.get(i).getAdditionalCost() * selections.get(i).getQuantity());
		}
		Invoice newOrder = new Invoice();
		newOrder.setCustomer(currentCustomer);
		newOrder.setAdditionalCost(totalAdditionalCost);
		newOrder.setDiscountNetAmount(0);
		newOrder.setDiscountAdditionalCost(0);
		newOrder.setDiscountItemCost(0);
		newOrder.setExpectedAmount((0.7 * netAmount));// 70% of the net amount
		newOrder.setFinalAmount(finalPriceAfterAllDeductions);
		newOrder.setOrderDetails(orderDetails.toString());
		newOrder.setNetAmount(netAmount);
		newOrder.setOrderStatus("Pending");
		newOrder.setStartDate(startDate);
		newOrder.setEndDate(new Date());
		newOrder.setMonth(currentMonth);
		newOrder.setInvoiceNumber(invoiceNumber);

		return invoiceRepository.save(newOrder);

	}

}
