package lecture.service;

import com.visma.lecture.common.database.Database;
import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;
import com.visma.lecture.service.ShopService;
import lecture.util.ShopTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertSame;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.format;

public class ShopServiceTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private ShopRepository shopRepository;
    private ShopService shopService;
    private ShopRepository bigShopRepository;
    private ShopService bigShopService;

    @Before
    public void setUp() throws Exception {

        shopRepository = new ShopRepository(new ShopTestUtil().getItems());
        shopService = new ShopService(shopRepository);
        bigShopRepository = new ShopRepository(Database.itemTable);
        bigShopService = new ShopService(bigShopRepository);

    }

    /**
     * Oppgave 1 test
     */
    @Test
    public void testGetMapPerLocation() throws Exception {

        Map<ItemLocation, List<Item>> map = shopService.getMapPerLocation();

        // Check if its total 2 lists in the map
        assertEquals(4, map.size());

        // Check if its 3 elemts for oslo location
        assertEquals(3, map.get(ItemLocation.OSLO).size());

        // Check if its 3 elemts for hamar location
        assertEquals(3, map.get(ItemLocation.HAMAR).size());

        //Check if its 3 elements for drammen location
        assertEquals(3, map.get(ItemLocation.DRAMMEN).size());

        //Check if its 1 elemnt for sarpsborg location
        assertEquals(1, map.get(ItemLocation.SARPSBORG).size());
    }


    /**
     * Oppgave 2 test
     */
    @Test
    public void testGetMapPerType() throws Exception {

        Map<ItemType, List<Item>> map = shopService.getMapPerType();

        // Check if its total 3 lists in the map
        assertEquals(3, map.size());

        // Check if its 2 elements for clothing list
        assertEquals(3, map.get(ItemType.CLOTHING).size());

        // Check if its 2 elements for beverage list
        assertEquals(4, map.get(ItemType.BEVERAGE).size());

        // Check if its 2 elements for electronic list
        assertEquals(3, map.get(ItemType.ELECTRONICS).size());
    }

    /**
     * Oppgave 3 test
     */
    @Test
    public void testGetMapPerProducer() throws Exception {

        Map<String, List<Item>> map = shopService.getMapPerProducer();

        //Check if its total 6 lists in map
        assertEquals(10, map.size());

        // Check if its 1 elements in first list
        assertEquals(1, map.get("Producer1 Test1").size());

        // Check if its 1 elements in second list
        assertEquals(1, map.get("Producer2 Test2").size());

        // Check if its 1 elements in third list
        assertEquals(1, map.get("Producer3 Test3").size());

        // Check if its 1 elements in fourth list
        assertEquals(1, map.get("Producer4 Test4").size());

        // Check if its 1 elements in fifth list
        assertEquals(1, map.get("Producer5 Test5").size());

        // Check if its 1 elements in sixth list
        assertEquals(1, map.get("Producer6_Test6").size());

        //Check if its 1 element in seventh list
        assertEquals(1, map.get("Producer7 Test7").size());

        //Check if its 1 element in eight list
        assertEquals(1, map.get("Producer8 Test8").size());

        //Check if its 1 element in nineth list
        assertEquals(1, map.get("Producer9 Test9").size());
    }

    /**
     * Oppgave 4 test
     */
    @Test
    public void testGetMapOfItemsHasUnder1500InStock() throws Exception {

        Map<Boolean, List<Item>> map = shopService.getMapOfItemsHasUnder1500InStock();

        // Check if its 6 elemnts in first list where all item have stock under 1500
        assertEquals(10, map.get(true).size());

        // Check if its 0 elements in second list where all item have stock over 1500
        assertEquals(0, map.get(false).size());
    }

    /**
     * Oppgave 5 test
     */
    @Test
    public void testGetItemByID() throws Exception {

        Item item = shopService.findItemByID(2001);

        // Testing by using method getListOfAllItems() in ShopRepository
        // and getting index 0 wich is Item id: 2001, should be the same as the item i recived
        // in method call above shopService.findByItemID(2001)
        assertEquals(shopRepository.getListOfAllItems().get(1), item);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetItemByIDButNoItemFound() throws Exception {

        Item item = shopService.findItemByID(3000);
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetItemByIDButInvalidInput() throws Exception {

        Item item = shopService.findItemByID(null);
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetItemByIDButInvalidNegativeInput() throws Exception {

        Item item = shopService.findItemByID(-1);
    }

    /**
     * Oppgave 6 test
     */
    @Test
    public void testGetAllProducersSeperatedByX() throws Exception {

        String item = shopService.getAllProducersSeperatedByX(" ");

        assertEquals(
                "Producer0 Test0 Producer1 Test1 Producer2 Test2 Producer3 Test3 Producer4 Test4 Producer5 Test5 Producer6_Test6 Producer7 Test7 Producer8 Test8 Producer9 Test9",
                item);
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetAllProducersSeperatedByXWithInvalidInput() throws Exception {

        String item = shopService.getAllProducersSeperatedByX("");
    }

    /**
     * Oppgave 7 test
     */
    @Test
    public void testGetListOfAllLocationsWthMoreThanXInStock() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithMoreThanXInStock(1);

        System.out.println(list);

        assertEquals(1, list.size());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListOfAllLocationsWthMoreThanXInStockWithInvalidInput() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithMoreThanXInStock(-1);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListOfAllLocationsWthMoreThanXInStockWithEmptyList() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithMoreThanXInStock(10);
    }

    /**
     * Oppgave 8 test
     */
    @Test
    public void testGetListOfAllLocationsWithLessThenXInStock() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithLessThanXInStock(2);

        assertEquals(3, list.size());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListOfAllLocationsWithLessThenXInStockWithInvalidInput() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithLessThanXInStock(-1);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListOfAllLocationsWithLessThenXInStockWithEmpty() throws Exception {

        List<ItemLocation> list = shopService.getListOfAllLocationsWithLessThanXInStock(0);
    }

    /**
     * Oppgave 9 test
     */
    @Test
    public void testGetListOfLocationXWithMoreThanYInStock() throws Exception {

        List<Item> list = shopService.getListOfLocationXWithMoreThanYInStock(ItemLocation.OSLO, 0);

        assertEquals(3, list.size());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListOfLocationXWithMoreThanYInStockWithInvalidInput() throws Exception {

        List<Item> list = shopService.getListOfLocationXWithMoreThanYInStock(null, -1);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListOfLocationXWithMoreThanYInStockWithEmptyList() throws Exception {

        List<Item> list = shopService.getListOfLocationXWithMoreThanYInStock(ItemLocation.SARPSBORG, 0);

    }

    /**
     * Oppgave 10 test
     */
    @Test
    public void testGetListOfLocationXWithLessThanYInStock() throws Exception{

        List<Item> list = shopService.getListOfLocationXWithLessThanYInStock(ItemLocation.DRAMMEN, 4);

        assertEquals(1, list.size());
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListOfLocationXWithLessThanYInStockWithEmptyList() throws Exception {

        List<Item> list = shopService.getListOfLocationXWithLessThanYInStock(ItemLocation.DRAMMEN, 3);
    }

    @Test
    public void testGetListofAllItemsWithNameStartWithX() throws Exception {

        List<Item> list = bigShopService.getListofAllItemsWithNameStartWithX("B");

        assertEquals(9, list.size());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListofAllItemsWithNameStartWithXWithInvalidInput() throws Exception {

        List<Item> list = bigShopService.getListofAllItemsWithNameStartWithX("BA");

    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListofAllItemsWithNameStartWithXWithEmptyList() throws Exception {

        List<Item> list = bigShopService.getListofAllItemsWithNameStartWithX("W");

    }

    /**
     * Oppgave 12 test
     */
    @Test
    public void testGetAverageOfItemStockInLocationX() throws Exception {

        double result = shopService.getAverageItemStockInLocationX(ItemLocation.DRAMMEN);

        assertEquals(4.0, result);
    }

    /**
     * Oppgave 13 test
     */
    @Test
    public void testgetItemWithMostInStock() throws Exception {

        Item item = shopService.getItemWithMostInStock();

        assertEquals(item, shopRepository.getListOfAllItems().get(8));
    }

    /**
     * Oppgave 14 test
     */
    @Test
    public void testGetItemWithMostLessInStock() throws Exception {

        Item item = shopService.getItemWithMostLessInStock();

        assertEquals(item, shopRepository.getListOfAllItems().get(0));
    }

    /**
     * Oppgave 15 test
     */

    /**
     * Oppgave 16 test
     */

    /**
     * Oppgave 19 test
     */
    @Test
    public void testGetListWithoutDuplicates() throws Exception {

        List<Item> distinctElements = bigShopService.getAllItemsWithoutDuplicates();

        assertEquals(75, distinctElements.size());
    }

    /**
     * Oppgave 20 test
     */

    /**
     * Oppgave 21 test
     */
    @Test
    public void testGetSumOfAllItemsStock() throws Exception {

        int result = shopService.sumAllStockInList();

        assertEquals(18, result);
    }
}