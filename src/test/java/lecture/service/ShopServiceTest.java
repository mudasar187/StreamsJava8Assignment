package lecture.service;

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

    @Before
    public void setUp() throws Exception {

        shopRepository = new ShopRepository(new ShopTestUtil().getItems());
        shopService = new ShopService(shopRepository);
    }

    @Test
    public void shouldThrowExceptionOnMissingInput() throws Exception {

        expected.expect(InvalidCriteriaException.class);
        expected.expectMessage("Input was null, empty or lower than 0.");
    }

    /**
     * Oppgave 1 test
     */
    @Test
    public void testGetMapPerLocation() throws Exception {

        Map<ItemLocation, List<Item>> map = shopService.getMapPerLocation();

        // Check if its total 2 lists in the map
        assertEquals(2, map.size());

        // Check if its 3 elemts for oslo location
        assertEquals(3, map.get(ItemLocation.OSLO).size());

        // Check if its 3 elemts for hamar location
        assertEquals(3, map.get(ItemLocation.HAMAR).size());
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
        assertEquals(2, map.get(ItemType.CLOTHING).size());

        // Check if its 2 elements for beverage list
        assertEquals(2, map.get(ItemType.BEVERAGE).size());

        // Check if its 2 elements for electronic list
        assertEquals(2, map.get(ItemType.ELECTRONICS).size());
    }

    /**
     * Oppgave 3 test
     */
    @Test
    public void testGetMapPerProducer() throws Exception {

        Map<String, List<Item>> map = shopService.getMapPerProducer();

        //Check if its total 6 lists in map
        assertEquals(6, map.size());

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
    }

    /**
     * Oppgave 4 test
     */
    @Test
    public void testGetMapOfItemsHasUnder1500InStock() throws Exception {

        Map<Boolean, List<Item>> map = shopService.getMapOfItemsHasUnder1500InStock();

        // Check if its 6 elemnts in first list where all item have stock under 1500
        assertEquals(6, map.get(true).size());

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
        assertEquals(shopRepository.getListOfAllItems().get(0), item);
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
}