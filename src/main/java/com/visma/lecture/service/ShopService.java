package com.visma.lecture.service;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.visma.lecture.common.Validator.Validator.*;


/**
 * Service class for shop
 *
 * @author Leo-Andreas Ervik
 */
public class ShopService {

    private final ShopRepository shopRepository;

    public ShopService(ShopRepository shopRepository) {

        this.shopRepository = shopRepository;
    }


    /**
     * Oppgave 1
     */
    public Map<ItemLocation, List<Item>> getMapPerLocation() {

        Map<ItemLocation, List<Item>> collect = shopRepository
                .getListOfAllItems()
                .stream()
                .collect(Collectors.groupingBy(Item::getItemLocation));

        validateOutputMap(collect);

        return collect;
    }

    /**
     * Oppgave 2
     */
    public Map<ItemType, List<Item>> getMapPerType() {

        Map<ItemType, List<Item>> collect = shopRepository
                .getListOfAllItems()
                .stream()
                .collect(Collectors.groupingBy(Item::getItemType));

        validateOutputMap(collect);

        return collect;
    }

    /**
     * Oppgave 3
     */
    public Map<String, List<Item>> getMapPerProducer() {

        Map<String, List<Item>> collect = shopRepository
                .getListOfAllItems()
                .stream()
                .collect(Collectors.groupingBy(Item::getItemName));

        validateOutputMap(collect);

        return collect;
    }

    /**
     * Oppgave 4
     */
    public Map<Boolean, List<Item>> getMapOfItemsHasUnder1500InStock() {

        Map<Boolean, List<Item>> collect = shopRepository
                .getListOfAllItems()
                .stream()
                .collect(Collectors.partitioningBy(items -> items.getStock() < 1500));

        validateOutputMap(collect);

        return collect;
    }

    /**
     * Oppgave 5
     */
    public Item findItemByID(Integer id) {

        validateOneIntegerInput(id);

        Item item = shopRepository
                .getListOfAllItems()
                .stream()
                .filter(e -> e.getItemID().equals(id))
                .findAny()
                .orElseThrow(() -> new NoItemFoundForCriteriaException(
                        "No items were found for the given search criteria."));

        return item;
    }

    /**
     * Oppgave 6
     */
    public String getAllProducersSeperatedByX(String delimiter) {

        validateStringInput(delimiter);

        String items = shopRepository.getListOfAllItems()
                .stream()
                .map(Item::getItemName)
                .collect(Collectors.joining(delimiter));

        validateStringOutPut(items);

        return items;
    }

    /**
     * Oppgave 7
     */
    public List<Item> getListOfAllLocationsWthMoreThanXInStock(Integer stock) {

        validateOneIntegerInput(stock);

            List<Item> list = shopRepository.getListOfAllItems()
                    .stream()
                    .filter(item -> item.getStock() > stock)
                    .collect(Collectors.toList());

            validateOutPutList(list);

            return list;
    }

    /**
     * Oppgave 8
     */



    public void something() {

        Stream<List<Item>> listPerItemPerLocation = Stream.of(
                shopRepository.getListPerItemPerLocation(ItemLocation.OSLO),
                shopRepository.getListPerItemPerLocation(ItemLocation.DRAMMEN),
                shopRepository.getListPerItemPerLocation(ItemLocation.HAMAR));

        listPerItemPerLocation.flatMap(Collection::stream);


    }
}
