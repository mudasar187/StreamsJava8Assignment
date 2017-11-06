package com.visma.lecture.common.Validator;

import com.visma.lecture.common.domain.Item;
import com.visma.lecture.common.domain.support.ItemLocation;
import com.visma.lecture.common.exception.InvalidCriteriaException;
import com.visma.lecture.common.exception.NoItemFoundForCriteriaException;

import java.util.List;
import java.util.Map;

public class Validator {


    public static void validateOutPutList(List<?> list) {
        if(list == null || list.isEmpty()) {
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }


    public static void validateOutputMap(Map<?, List<Item>> items) {
        if(items == null || items.isEmpty() || items.entrySet().isEmpty() || items.keySet().isEmpty()) {
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateIntegerAndListInput(Integer stock, List<?> list) {
        if(stock == null || stock < 0 || list.isEmpty() || list == null) {
            throw new InvalidCriteriaException("Input was null, empty og lower than 0");
        }
    }

    public static void validateTwoIntegerInput(Integer x, Integer y) {
        if(x == null || y == null || x < 0 || y < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }


    public static void validateObjectInput(Object o) {
        if(o == null) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateStringInput(String input) {
        if(input == null || input.isEmpty() || input.equals("")) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }


    public static void validateOneIntegerInput(Integer id)  {
        if(id == null || id < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateStringOutPut(String value) {
        if(value == null || value.isEmpty() || value.equals("")) {
            throw new NoItemFoundForCriteriaException("No items were found for the given search criteria.");
        }
    }

    public static void validateObjectAndIntegerInput(Object o, Integer stock) {
        if(o == null || stock == null || stock < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateStringOneLetterInput(String letter) {
        if(letter.equals("") || letter.isEmpty() || letter == null || letter.length() == 2) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

    public static void validateFourIntegerInput(Integer a, Integer b, Integer c, Integer d) {
        if(a == null || a < 0 || b == null || b < 0 || c == null || c < 0 || d == null || d < 0) {
            throw new InvalidCriteriaException("Input was null, empty or lower than 0.");
        }
    }

}
