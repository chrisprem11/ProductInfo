package product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.info.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findById(int id);
	
	public Customer findByContact(int contact);
}
