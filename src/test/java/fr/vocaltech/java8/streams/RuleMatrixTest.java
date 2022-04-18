package fr.vocaltech.java8.streams;

import fr.vocaltech.java8.models.Cart;

import lombok.var;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


class RuleMatrixTest {
    private Cart cart;

    @Test
    void givenCartValueSix_thenReturn_Six_Multiply_1_5() {
        cart = new Cart();
        cart.setValue(6.0);

        var result = RuleMatrix.getRule(cart).apply(cart);
        assertThat(result).isEqualTo(9.0);
    }


    @Test
    void givenCartValueSeven_thenReturn_Seven_Multiply_2_5() {
        cart = new Cart();
        cart.setValue(7.0);

        var result = RuleMatrix.getRule(cart).apply(cart);
        assertThat(result).isEqualTo(17.5);
    }

    @Test
    void givenCartValueNine_thenReturn_1_5() {
        cart = new Cart();
        cart.setValue(9.0);

        var result = RuleMatrix.getRule(cart).apply(cart);
        assertThat(result).isEqualTo(1.5);
    }

}