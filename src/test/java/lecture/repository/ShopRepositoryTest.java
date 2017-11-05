package lecture.repository;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;
import lecture.util.ShopTestUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Leo-Andreas Ervik
 */
public class ShopRepositoryTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();

    private List<Item> items;
    private ShopRepository shopRepository;

    @Before
    public void setUp() throws Exception {

        items = new ShopTestUtil().getItems();
        shopRepository = new ShopRepository(items);
    }

    @Test
    public void findItemById() throws Exception {

        assertThat(shopRepository.findItemById(2001), is(items.get(0)));
    }

    @Test
    public void shouldReturnNullNotFoundById() throws Exception {

        assertThat(shopRepository.findItemById(1), nullValue());
    }

    @Test
    public void add() throws Exception {

        Integer size = items.size();
        Boolean add = shopRepository.create(
                new Item(2010, "Producer11 Test11", ItemLocation.SARPSBORG, ItemType.CLOTHING, 1));

        assertThat(add, is(Boolean.TRUE));
        assertThat(items.size(), is(size + 1)); //replace with .getAll() when implemented
        assertThat(shopRepository.findItemById(2010).getItemName(), is("Producer11 Test11"));
    }

    @Test
    public void update() throws Exception {

        Boolean update = shopRepository.update(
                new Item(2001, "Producer10 Test1", ItemLocation.OSLO, ItemType.BEVERAGE, 10));

        assertThat(update, is(Boolean.TRUE));
        assertThat(shopRepository.findItemById(2001).getStock(), is(10));
        assertThat(shopRepository.findItemById(2001).getItemName(), is("Producer10 Test1"));
    }

    @Test
    public void remove() throws Exception {

        Boolean remove = shopRepository.delete(2001);

        assertThat(remove, is(Boolean.TRUE));
        assertThat(items.size(), is(8)); //replace with .getAll() when implemented
        assertThat(shopRepository.findItemById(2001), nullValue());
    }

    /**
     * Oppgave 2 test
     */
    @Test
    public void testGetListOfAllItems() throws Exception {

        assertEquals(items.size(), shopRepository.getListOfAllItems().size());
    }


    /**
     * Oppgave 3 test
     */
    @Test
    public void testGetListOfItemsByRange() throws Exception {

        List<Item> list = shopRepository.getListOfItemsByRange(1, 3);

        assertEquals(items.get(1), list.get(0));
        assertEquals(items.get(2), list.get(1));
        assertEquals(items.get(3), list.get(2));

    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListOfItemsByRangeWithNegativeInvalidInput() throws Exception {
        List<Item> list = shopRepository.getListOfItemsByRange(-1, -1);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListOfItemsByRangeWithEmptyList() throws Exception {
        List<Item> list = shopRepository.getListOfItemsByRange(0, 0);
    }


    /**
     * Oppgave 4 test
     */
    @Test
    public void testGetListPerItemPerLocation() throws Exception {

        List<Item> itemsFromOslo = shopRepository.getListPerItemPerLocation(ItemLocation.OSLO);

        itemsFromOslo.stream().forEach(itemsOslo -> assertEquals(ItemLocation.OSLO, itemsOslo.getItemLocation()));
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListPerItemPerLocationWithInvalidInput() throws Exception {

        List<Item> itemsFromOslo = shopRepository.getListPerItemPerLocation(null);
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListPerItemPerLocationWithEmptyList() throws Exception {

        List<Item> itemsFromOslo = shopRepository.getListPerItemPerLocation(ItemLocation.SARPSBORG);
    }

    /**
     * Oppgave 5 test
     */
    @Test
    public void testGetListPerItemPerType() throws Exception {

        List<Item> itemsBeverage = shopRepository.getListPerItemPerType(ItemType.BEVERAGE);

        itemsBeverage.stream().forEach(itemType -> assertEquals(ItemType.BEVERAGE, itemType.getItemType()));
    }


    /**
     * Oppgave 6 test
     */
    @Test
    public void testGetListPerItemPerProducer() throws Exception {

        List<Item> itemsFromProducer = shopRepository.getListPerItemPerProducer("Producer6 Test6");

        assertEquals(1, itemsFromProducer.size());
    }

    @Test(expected = InvalidCriteriaException.class)
    public void testGetListPerItemPerProducerWithInvalidInput() throws Exception {

        List<Item> itemsFromProducer = shopRepository.getListPerItemPerProducer("");
    }

    @Test(expected = NoItemFoundForCriteriaException.class)
    public void testGetListPerItemPerProducerWithEmptyList() throws Exception {

        List<Item> itemsFromProducer = shopRepository.getListPerItemPerProducer("Producer10 Test10");
    }


}