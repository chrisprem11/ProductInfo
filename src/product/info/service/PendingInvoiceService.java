package product.info.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.info.model.PendingInvoice;
import product.info.repository.PendingInvoiceRepository;

@Service
public class PendingInvoiceService {

	@Autowired
	PendingInvoiceRepository pendingInvoiceRepository;

	public PendingInvoice savePendingInvoice(PendingInvoice object) {
		return pendingInvoiceRepository.save(object);
	}

}
