package product.info.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import product.info.model.Invoice;
import product.info.service.InvoiceService;

@Controller
public class FileDownloadController {
	
	@Autowired
	InvoiceService invoiceService;

	 @RequestMapping("/pdf/{fileName:.+}")
	    public void downloadPDFResource( HttpServletRequest request, 
	                                     HttpServletResponse response, 
	                                     @PathVariable("fileName") String fileName) 
	    {
	        //If user is not authorized - he should be thrown out from here itself
	         
	        //Authorized user will download the file
	        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/documents/");
	        Path file = Paths.get(dataDirectory, fileName);
	        if (Files.exists(file)) 
	        {
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            } 
	            catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	 
	 @GetMapping("/invoicePdf")
	 public ModelAndView downloadPdf(@RequestParam("id") Integer id){
		 Invoice invoice = invoiceService.getOneInvoice(id);
		 List<Invoice> invoiceList = new ArrayList<Invoice>();
		 invoiceList.add(invoice);
		 return new ModelAndView("pdfView", "invoiceList", invoiceList);

	 }
}
