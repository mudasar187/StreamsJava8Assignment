package com.visma.lecture.service;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.domain.support.ItemType;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;
import com.visma.lecture.repository.ShopRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.visma.lecture.common.Validator.Validator.*;
import static java.util.stream.Collectors.toList;


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
    public List<ItemLocation> getListOfAllLocationsWithMoreThanXInStock(Integer stock) {

        validateOneIntegerInput(stock);

        List<ItemLocation> list = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getStock() > stock)
                .map(Item::getItemLocation)
                .distinct()
                .collect(Collectors.toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 8
     */
    public List<ItemLocation> getListOfAllLocationsWithLessThanXInStock(Integer stock) {

        validateOneIntegerInput(stock);

        List<ItemLocation> list = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getStock() < stock)
                .map(Item::getItemLocation)
                .distinct()
                .collect(Collectors.toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 9
     */
    public List<Item> getListOfLocationXWithMoreThanYInStock(ItemLocation location, Integer stock) {

        validateObjectAndIntegerInput(location, stock);

        List<Item> list = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getStock() > stock && item.getItemLocation().equals(location))
                .collect(toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 10
     */
    public List<Item> getListOfLocationXWithLessThanYInStock(ItemLocation location, Integer stock) {

        validateObjectAndIntegerInput(location, stock);

        List<Item> list = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getStock() < stock && item.getItemLocation().equals(location))
                .collect(toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 11
     */
    public List<Item> getListofAllItemsWithNameStartWithX(String letter) {

        validateStringOneLetterInput(letter);

        List<Item> list = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getItemName().substring(item.getItemName().indexOf(" ")+1).startsWith(letter))
                .collect(toList());

        validateOutPutList(list);

        return list;
    }

    /**
     *  Oppgave 12
     */
    public double getAverageItemStockInLocationX(ItemLocation itemLocation) {

        validateObjectInput(itemLocation);

        double result = shopRepository.getListPerItemPerLocation(itemLocation)
                .stream().mapToDouble(Item::getStock).average().getAsDouble();

        return result;
    }


    /**
     * Oppgave 13
     */
    public Item getItemWithMostInStock() {

        Item item = shopRepository.getListOfAllItems()
                .stream()
                .max(Comparator.comparing(Item::getStock))
                .orElseThrow(() -> new NoItemFoundForCriteriaException(
                        "No items were found for the given search criteria."));

        return item;
    }

    /**
     * Oppgave 14
     */
    public Item getItemWithMostLessInStock() {

        Item item = shopRepository.getListOfAllItems()
                .stream()
                .min(Comparator.comparing(Item::getStock))
                .orElseThrow(() -> new NoItemFoundForCriteriaException(
                        "No items were found for the given search criteria."));

        return item;
    }

    /**
     * Oppgave 15
     */
    public Map<ItemLocation, List<Item>> getAllItemsForALocationXHaveMoreThanYInStock(int stock) {

        validateOneIntegerInput(stock);

        Map<ItemLocation, List<Item>> map = shopRepository.getListOfAllItems()
                .stream()
                .filter(item -> item.getStock() > stock)
                .collect(Collectors.groupingBy(Item::getItemLocation));

        validateOutputMap(map);

        return map;
    }

    /**
     * Oppgave 16
     */
    public List<Item> getAllItemsSortedAlphabeticallyByProducer() {

        List<Item> list = shopRepository.getListOfAllItems()
                .stream()
                .sorted(Comparator.comparing(Item::getItemName))
                .collect(Collectors.toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 17
     */

    /**
     * Oppgave 18
     */
    public List<Item> getAllItemsSortedByStockFromHighToLow() {

        List<Item> list = shopRepository.getListOfAllItems()
                .stream()
                .sorted(Comparator.comparing(Item::getStock).reversed())
                .collect(Collectors.toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 19
     */
    public List<Item> getAllItemsWithoutDuplicates() {

        List<Item> distinctElements = shopRepository.getListOfAllItems()
                .stream().distinct().collect(Collectors.toList());

        validateOutPutList(distinctElements);

        return distinctElements;
    }

    /**
     * Oppgave 20
     */
    public List<Item> getASingleListFromToLists(int a, int b, int x, int y) {

        validateFourIntegerInput(a,b,x,y);

        List<Item> list = Stream.of(shopRepository.getListOfItemsByRange(a,b), shopRepository.getListOfItemsByRange(x,y))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        validateOutPutList(list);

        return list;
    }

    /**
     * Oppgave 21
     */

    /**
     * Oppgave 22
     */
    public int sumAllStockInList() {

        int result = shopRepository.getListOfAllItems()
                .stream().mapToInt(Item::getStock).sum();

        return result;
    }
}
