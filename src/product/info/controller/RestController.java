package product.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import product.info.DTO.CustomerDTO;
import product.info.DTO.InvoiceDTO;
import product.info.DTO.ItemDTO;
import product.info.model.Customer;
import product.info.model.Invoice;
import product.info.model.Item;
import product.info.service.CustomerService;
import product.info.service.InvoiceService;
import product.info.service.ItemService;

/**
 * This is a rest controller that handles all the REST calls
 * 
 * @author Premnath Christopher
 *
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ItemService itemService;

	@Autowired
	CustomerService customerService;

	List<Item> selections = null;

	/*
	 * This method gets all required data to load on the landing page. This
	 * returns the home page.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ResponseEntity<List<Double>> getHomePage() {
		List<Double> priceList = new ArrayList<Double>();
		double totalPayment = 0;
		double totalPendingAmount = 0;
		double finalAmount = 0;
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		for (int i = 0; i < invoiceList.size(); i++) {
			totalPayment += invoiceList.get(i).getCustomerGivenAmount();
			totalPendingAmount += invoiceList.get(i).getAmountDeviation();
			finalAmount += invoiceList.get(i).getFinalAmount();
		}
		double totalPaymentPercent = Math.round(((totalPayment / finalAmount) * 100));
		double totalPendingPercent = Math.round(((totalPendingAmount / finalAmount) * 100));
		priceList.add(totalPayment);
		priceList.add(totalPendingAmount);
		priceList.add(totalPaymentPercent);
		priceList.add(totalPendingPercent);
		System.out.println(invoiceList);
		return new ResponseEntity<List<Double>>(priceList, HttpStatus.OK);
	}

	/*
	 * This method is used to get the values and other data to which will be
	 * used to create a chart.
	 * 
	 */
	@RequestMapping(value = "/getDataChart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double>> getInvoiceData() {
		List<Invoice> invoiceData = invoiceService.getInvoiceList();
		HashMap<String, Double> chartData = new HashMap<>();
		for (int i = 0; i < invoiceData.size(); i++) {
			chartData.put(invoiceData.get(i).getMonth(), invoiceData.get(i).getCustomerGivenAmount());
		}
		return new ResponseEntity<HashMap<String, Double>>(chartData, HttpStatus.OK);
	}

	/*
	 * This method lists all the items that have been already added.
	 */
	@RequestMapping(value = "/fetchAllItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> allItems = itemService.getAllItems();
		if (allItems == null) {
			System.out.println("Item not saved. Error occurred.");
			return new ResponseEntity<List<Item>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Item>>(allItems, HttpStatus.OK);
	}

	/*
	 * This method adds the items to the Item list
	 */
	@RequestMapping(value = "/addNewItem", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> createOneItem(@RequestBody ItemDTO itemDTO) {
		Item addItem = itemService.addItem(itemDTO);
		return new ResponseEntity<Item>(addItem, HttpStatus.OK);
	}

	/*
	 * This method retrieves all the customers.//Need to fix fetch type here!
	 */
	@RequestMapping(value = "/fetchAllCustomer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> allCustomers = customerService.getAllCustomers();
		System.out.println("All Customers - " + allCustomers);
		if (allCustomers == null) {
			System.out.println("Error occured. Unable to fetch all the customer.");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);

	}

	/*
	 * This method creates new Customer
	 */
	@RequestMapping(value = "/addNewCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createOneCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = customerService.createNewCustomer(customerDTO);
		if (customer == null) {
			System.out.println("Customer not saved");

		} else {
			System.out.println("Customer created.");
			System.out.println(customer);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	/*
	 * ' This method lists all the generated or stored invoices
	 */
	@RequestMapping(value = "/fetchAllInvoices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Invoice>> getAllInvoices() {
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		if (invoiceList == null) {
			System.out.println("List of invoices not found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);
	}

	/*
	 * This method renders a view to see a particular invoice
	 * 
	 * @param- invoiceId
	 * 
	 * @return -Invoice object
	 */
	@RequestMapping(value = "/getOneInvoice/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Invoice> getOneInvoice(@PathVariable("id") Integer id) {
		Invoice oneInvoice = invoiceService.getOneInvoice(id);
		if (oneInvoice == null) {
			System.out.println("The invoice could not be found. Error occurred.");
		}
		return new ResponseEntity<Invoice>(oneInvoice, HttpStatus.OK);
	}

	/*
	 * This method completes the order by receiving the amount from the customer
	 * and updating all the invoice details.
	 */
	@RequestMapping(value = "/completePendingOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Invoice> completePendingOrder(@RequestBody CustomerDTO customerDTO) {
		Invoice oneInvoice = invoiceService.getOneInvoice(customerDTO.getInvoiceId());
		if (oneInvoice == null) {
			System.out.println("Invoice not found.");
		} else {
			System.out.println("Invoice found.");
		}
		invoiceService.completeOrder(customerDTO, oneInvoice);
		return new ResponseEntity<Invoice>(oneInvoice, HttpStatus.OK);
	}

	/*
	 * This method adds items to the cart. Repeatations is checked and quantity
	 * is increased with the amount
	 */
	@RequestMapping(value = "findOneItem", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> getCartList(HttpServletRequest request) {

		String message = "";
		Boolean isFoundRepeatedItem = false;
		String itemName = request.getParameter("itemName");
		Item oneItem = itemService.fetchItem(itemName);

		if (selections == null)
			selections = new ArrayList<Item>();

		if (oneItem == null) {
			message = "Invalid Barcode.Product not found";
			System.out.println("Product not found. Invalid item");
		} else {
			oneItem.setQuantity(oneItem.getQuantity() + 1);
			double basePrice = oneItem.getItemCost();

			System.out.println(" Checking for repeatations");
			for (int i = 0; i < selections.size(); i++) {
				if (selections.get(i).getId() == oneItem.getId()) {
					System.out.println("Index Matched !");
					int getQuantity = selections.get(i).getQuantity() + 1;
					double netAmount = (basePrice * getQuantity);
					System.out
							.println("Quantity -" + getQuantity + "| Price -" + basePrice + "| NetAmount" + netAmount);
					selections.get(i).setQuantity(getQuantity);
					selections.get(i).setItemCost(netAmount);
					isFoundRepeatedItem = true;
					break;
				}
			}
			if (!isFoundRepeatedItem)
				selections.add(oneItem);

		}
		return new ResponseEntity<List<Item>>(selections, HttpStatus.OK);
	}

	/*
	 * This method generates the invoice for the items that were selected and
	 * ordered.
	 */
	@RequestMapping(value = "/takeOrder", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Invoice> placeOrder(@RequestBody InvoiceDTO invoiceDTO) {
		Invoice createOrder = invoiceService.createInvoice(invoiceDTO, selections);
		selections.clear();
		if (createOrder != null) {
			System.out.println("From controller- everything is saved");
		} else {
			System.out.println("Null");
		}
		return new ResponseEntity<Invoice>(createOrder, HttpStatus.OK);

	}
}
