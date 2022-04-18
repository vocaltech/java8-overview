package fr.vocaltech.java8.streams;

import fr.vocaltech.java8.models.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class RuleMatrix {
    private static final Map<Predicate<Cart>, Function<Cart, Double>> ruleMatrix = new HashMap<>();

    static {
        ruleMatrix.put(CartPredicates.WHEN_CART_VALUE_IS_SIX, CartFunctions.MULTIPLY_WITH_ONE_POINT_FIVE);
        ruleMatrix.put(CartPredicates.WHEN_CART_VALUE_IS_SEVEN, CartFunctions.MULTIPLY_WITH_TWO_POINT_FIVE);
    }

    public static Function<Cart, Double> getRule(Cart cart) {
        // select * from ruleMatrix
        return ruleMatrix
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().test(cart))
                .map(entry -> entry.getValue())
                .findFirst()
                .orElse(CartFunctions.DEFAULT);
    }
}
