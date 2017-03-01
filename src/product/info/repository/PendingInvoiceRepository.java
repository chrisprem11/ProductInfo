package product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.info.model.PendingInvoice;

@Repository
public interface PendingInvoiceRepository extends JpaRepository<PendingInvoice, Integer> {

}
