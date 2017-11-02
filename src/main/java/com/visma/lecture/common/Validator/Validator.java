package com.visma.lecture.common.Validator;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;

import java.util.List;
import java.util.Map;

public class Validator {


    public static void validateOutPutList(List<Item> list) {
        if(list == null || list.isEmpty()) {
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }


    public static void validateOutputMap(Map<?, List<Item>> items) {
        if(items == null || items.isEmpty() || items.entrySet().isEmpty() || items.keySet().isEmpty()) {
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateTwoIntegerInput(Integer x, Integer y) {
        if(x == null || y == null || x < 0 || y < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0");
        }
    }


    public static void validateObjectInput(Object o) {
        if(o == null) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0");
        }
    }

    public static void validateStringInput(String input) {
        if(input == null || input.isEmpty() || input.equals("")) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0");
        }
    }


    public static void validateOneIntegerInput(Integer id)  {
        if(id == null || id < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0");
        }
    }

}
