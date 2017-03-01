package product.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import product.info.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

	public Item findByItemName(String name);
}
