package com.visma.lecture.repository;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository class for shop
 *
 * @author Leo-Andreas Ervik
 */
public class ShopRepository {

	private final List<Item> items;

	public ShopRepository(List<Item> items) {
		this.items = items;
	}

	public Item findItemById(Integer id) {
		return items.stream()
				.filter(e -> e.getItemID().equals(id))
				.findFirst()
				.orElse(null);
	}

	public Boolean create(Item item) {
		return items.add(item);
	}

	public Boolean update(Item item) {
		Item i = findItemById(item.getItemID());
		delete(i.getItemID());
		return create(item);
	}

	public Boolean delete(Integer itemId) {
		return items.removeIf(e -> e.getItemID().equals(itemId));
	}

	/**
	 * Oppgave 2
	 */
	public List<Item> getListOfAllItems() {

		return items.stream().collect(Collectors.toList());
	}

	/**
	 * Oppgave 3
	 */
	public List<Item> getListOfItemsByRange(int fromStart, int toEnd) {

		return items.stream()
				.skip(fromStart)
				.limit(toEnd)
				.collect(Collectors.toList());
	}

	/**
	 * Oppgave 4
	 */
	public List<Item> getListPerItemPerLocation(ItemLocation location) {

		return items.stream()
				.filter(item -> item.getItemLocation().equals(location))
				.collect(Collectors.toList());

	}

	/**
	 * Oppgave 5
	 */
	public List<Item> getListPerItemPerType(ItemType type) {

		return items.stream()
				.filter(item -> item.getItemType().equals(type))
				.collect(Collectors.toList());
	}

	/**
	 * Oppgave 6
	 */
	public List<Item> getListPerItemPerProducer(String name) {

		return items.stream()
				.filter(item -> item.getItemName().replace("_", " ").contains(name))
				.collect(Collectors.toList());
	}


}
