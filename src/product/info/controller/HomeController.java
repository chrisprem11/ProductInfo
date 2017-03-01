package product.info.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import product.info.DTO.CustomerDTO;
import product.info.DTO.InvoiceDTO;
import product.info.DTO.ItemDTO;
import product.info.model.Customer;
import product.info.model.Invoice;
import product.info.model.Item;
import product.info.service.CustomerService;
import product.info.service.InvoiceService;
import product.info.service.ItemService;

@Controller
public class HomeController {

	@Autowired
	ItemService itemService;

	@Autowired
	CustomerService customerService;

	@Autowired
	InvoiceService invoiceService;

	List<Item> selections = null;

	@GetMapping("/")
	public String getHomePage(Model model) {
		double totalPayment=0;
		double totalPendingAmount=0;
		double finalAmount = 0;
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		for(int i=0;i<invoiceList.size();i++){
			totalPayment+=invoiceList.get(i).getCustomerGivenAmount();
			totalPendingAmount += invoiceList.get(i).getAmountDeviation();
			finalAmount+= invoiceList.get(i).getFinalAmount();
		}
		double totalPaymentPercent= Math.round(((totalPayment/finalAmount)*100));
		double totalPendingPercent= Math.round(((totalPendingAmount/finalAmount)*100));
		model.addAttribute("invoiceList", invoiceList);
		model.addAttribute("totalPayment", totalPayment);
		model.addAttribute("totalPendingAmount", Math.round(totalPendingAmount));
		model.addAttribute("totalPaymentPercent", totalPaymentPercent);
		model.addAttribute("totalPendingPercent", totalPendingPercent);
		return "index";
	}

	@GetMapping("/showItems")
	public String getAllItems(Model model) {
		List<Item> allItems = itemService.getAllItems();
		model.addAttribute("allItems", allItems);
		return "itemsPage";
	}

	@GetMapping("/addItem")
	public String addItem(Model model, HttpServletRequest req) {
		String error = req.getParameter("error");
		model.addAttribute("error", error);
		return "addItems";
	}

	@PostMapping("/addItem")
	public String createItem(@ModelAttribute("ItemDetails") ItemDTO itemDTO, Model model) {
		Item addItem = itemService.addItem(itemDTO);
		if (addItem == null) {
			System.out.println("Item not saved !");
			model.addAttribute("error", "Somthing went wrong. Item was not saved.");
			return "redirect:/addItem";
		} else {
			System.out.println("Item saved.");
		}
		return "redirect:/";
	}

	@GetMapping("/customers")
	public String getAllCustomer(Model model) {
		List<Customer> allCustomers = customerService.getAllCustomers();
		model.addAttribute("customers", allCustomers);
		return "customers";
	}

	@GetMapping("/addCustomer")
	public String addCustomer(@ModelAttribute("customerDetails") CustomerDTO customerDTO, Model model) {
		return "addCustomer";
	}

	@PostMapping("/addCustomer")
	public String createCustomer(@ModelAttribute("customerDetails") CustomerDTO customerDTO, Model model) {
		Customer customer = customerService.createNewCustomer(customerDTO);
		if (customer == null) {
			System.out.println("Customer not saved");
			return "redirect:/addCustomer";
		} else {
			System.out.println("Customer created.");
			System.out.println(customer);
		}
		return "redirect:/";
	}

	@GetMapping("/invoice")
	public String getInvoice(@ModelAttribute("invoiceDetails") InvoiceDTO invoice, Model model) {
		return "invoice";
	}

	@GetMapping("/findItem")
	public String matchBarcode(@ModelAttribute("invoiceDetails") InvoiceDTO invoiceDTO, Model model,
			HttpServletRequest request) {
		String message = "";
		Boolean isFoundRepeatedItem = false;
		String itemName = request.getParameter("itemName");
		Item oneItem = itemService.fetchItem(itemName);

		if (selections == null)
			selections = new ArrayList<Item>();

		if (oneItem == null) {
			message = "Invalid Barcode.Product not found";
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
		System.out.println(selections.toString());
		model.addAttribute("itemList", selections);
		model.addAttribute("message", message);
		return "invoice";
	}

	@PostMapping("/invoice")
	public String createInvoice(@ModelAttribute("invoiceDetails") InvoiceDTO invoiceDTO, Model model) {
		Invoice createOrder = invoiceService.createInvoice(invoiceDTO, selections);
		selections.clear();
		if (createOrder != null) {
			System.out.println("From controller- everything is saved");
		} else {
			System.out.println("Null");
		}
		return "redirect:/getInvoice?id=" + createOrder.getId();
	}

	@GetMapping("/invoiceList")
	public String getInvoiceList(Model model) {
		List<Invoice> invoiceList = invoiceService.getInvoiceList();
		model.addAttribute("invoiceList", invoiceList);
		return "invoiceList";
	}

	@GetMapping("/getInvoice")
	public String getOneInvoice(@RequestParam("id") Integer id, Model model) {
		Invoice oneInvoice = invoiceService.getOneInvoice(id);
		if (oneInvoice == null) {
			System.out.println("Invoice not found.");
		} else {
			System.out.println("Invoice found.");
		}
		model.addAttribute("invoice", oneInvoice);
		return "oneInvoice";
	}

	@PostMapping("/completeOrder")
	public String completeOrder(@ModelAttribute("completeOrder") CustomerDTO customerDTO, Model model) {
		Invoice oneInvoice = invoiceService.getOneInvoice(customerDTO.getInvoiceId());
		if (oneInvoice == null) {
			System.out.println("Invoice not found.");
		} else {
			System.out.println("Invoice found.");
		}
		invoiceService.completeOrder(customerDTO, oneInvoice);

		model.addAttribute("invoice", oneInvoice);
		return "redirect:/getInvoice?id=" + oneInvoice.getId();
	}

	@GetMapping("/quickInvoice")
	public String doQuickPay(Model model) {

		
		return "quickPay";
	}
}
