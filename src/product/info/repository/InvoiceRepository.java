package product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.info.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	Invoice findById(int id);
}
 