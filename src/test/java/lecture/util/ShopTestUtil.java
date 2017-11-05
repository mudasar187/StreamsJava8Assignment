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
		items.add(new Item(2007, "Producer7 Test7", ItemLocation.DRAMMEN, ItemType.BEVERAGE, 3));
		items.add(new Item(2008, "Producer8 Test8", ItemLocation.DRAMMEN, ItemType.CLOTHING, 5));
		items.add(new Item(2009, "Producer9 Test9", ItemLocation.DRAMMEN, ItemType.ELECTRONICS, 4));



	}
	
	public List<Item> getItems() {
		return items;
	}
}
