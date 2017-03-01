package product.info.viewResolver;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import product.info.model.Invoice;

/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * 
 * @author www.codejava.net
 *
 */
@Component
public class PDFBuilder extends AbstractITextPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
		@SuppressWarnings("unchecked")
		List<Invoice> invoiceList = (List<Invoice>) model.get("invoiceList");

		doc.add(new Paragraph("Invoice Details -  Customer Copy"));
		doc.add(new Paragraph("---------------------------------------------------------------------------------------------"));
		doc.add(new Paragraph("Customer Name - " + invoiceList.get(0).getCustomer().getFirstName() + " "
				+ invoiceList.get(0).getCustomer().getLastName()));

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100.0f);
		table.setWidths(new float[] { 3.0f, 8.0f, 4.0f, 3.8f, 3.0f, 4.5f, 4.5f, 3.5f });
		table.setSpacingBefore(10);

		// define font for table header row
		Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
		font.setColor(BaseColor.YELLOW);

		// define table header cell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(BaseColor.BLACK);
		cell.setPadding(3);

		// write table header
		cell.setPhrase(new Phrase("Invoice Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Details", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Final Discounted Amount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Received Amount", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Balance", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Ordered on", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Completed on", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Order Status", font));
		table.addCell(cell);

		// write table row data
		for (Invoice order : invoiceList) {
			table.addCell(String.valueOf(order.getInvoiceNumber()));
			table.addCell(String.valueOf(order.getOrderDetails()));
			table.addCell(String.valueOf(order.getFinalAmount()));
			table.addCell(String.valueOf(order.getCustomerGivenAmount()));
			table.addCell(String.valueOf(Math.round(order.getAmountDeviation())));
			table.addCell(String.valueOf(order.getStartDate()));
			table.addCell(String.valueOf(order.getEndDate()));
			table.addCell(String.valueOf(order.getOrderStatus()));
		}

		doc.add(table);

	}

}
