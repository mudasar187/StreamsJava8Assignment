package lecture.util;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;

import java.util.ArrayList;
import java.util.List;

public class ShopTestUtil {
	
	private final List<Item> items;
	
	public ShopTestUtil(){
		items = new ArrayList<>();
		items.add(new Item(2001, "Producer1 Test1", ItemLocation.OSLO, ItemType.BEVERAGE, 1));
		items.add(new Item(2002, "Producer2 Test2", ItemLocation.OSLO, ItemType.ELECTRONICS, 1));
		items.add(new Item(2003, "Producer3 Test3", ItemLocation.OSLO, ItemType.CLOTHING, 1));
		items.add(new Item(2004, "Producer4 Test4", ItemLocation.HAMAR, ItemType.BEVERAGE, 1));
		items.add(new Item(2005, "Producer5 Test5", ItemLocation.HAMAR, ItemType.ELECTRONICS, 1));
		items.add(new Item(2006, "Producer6_Test6", ItemLocation.HAMAR, ItemType.CLOTHING, 1));
	}
	
	public List<Item> getItems() {
		return items;
	}
}
