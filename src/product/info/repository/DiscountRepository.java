package product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.info.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
