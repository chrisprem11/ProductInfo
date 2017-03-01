package product.info.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import product.info.model.Invoice;
import product.info.service.InvoiceService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	InvoiceService invoiceService;

	@RequestMapping(value = "/getDataChart", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double>> getInvoiceData() {
		List<Invoice> invoiceData = invoiceService.getInvoiceList();
		HashMap<String, Double> chartData = new HashMap<>();
		for (int i = 0; i < invoiceData.size(); i++) {
			chartData.put(invoiceData.get(i).getMonth(), invoiceData.get(i).getCustomerGivenAmount());
		}
		return new ResponseEntity<HashMap<String, Double>>(chartData, HttpStatus.OK);
	}
}
