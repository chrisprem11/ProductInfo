package product.info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.info.DTO.ItemDTO;
import product.info.model.Item;
import product.info.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public Item addItem(ItemDTO itemDTO) {
		Item newItem = new Item();
		newItem.setItemName(itemDTO.getItemName());
		newItem.setItemCost(itemDTO.getItemCost());
		newItem.setAdditionalCost(itemDTO.getAdditionalCost());
		newItem.setAdditonalField("future use");
		return itemRepository.save(newItem);
	}

	public Item fetchItem(String itemName) {
		return itemRepository.findByItemName(itemName);
	}
}
