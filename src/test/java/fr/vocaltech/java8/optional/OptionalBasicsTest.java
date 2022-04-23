package fr.vocaltech.java8.optional;

import fr.vocaltech.java8.models.CartItem;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;

class OptionalBasicsTest {
    @Test
    void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> emptyOptional = Optional.empty();
        assertThat(emptyOptional.isPresent()).isFalse();
    }

    @Test
    void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        String str = "vocaltech";
        Optional<String> optionalString = Optional.of(str);
        assertThat(optionalString.isPresent()).isTrue();
    }

    @Test
    void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
        assertThatThrownBy(() -> Optional.of(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void givenNull_whenOrElseWorks_thenShouldReturnValue() {
        String str = null;
        String name = Optional.ofNullable(str)
                .orElse("vocaltech");

        assertThat(name).isEqualTo("vocaltech");
    }

    @Test
    void givenNull_whenOrElseGetWorks_thenShouldReturnValue() {
        String str = null;
        String name = Optional.ofNullable(str)
                .orElseGet(() -> "vocaltech");

        assertThat(name).isEqualTo("vocaltech");
    }

    @Test
    void whenOptionalFilterWorks_thenCorrect() {
        String password = "password_is_strong";
        Optional<String> passwordOptional = Optional.of(password);

        boolean isPasswordStrong = passwordOptional
                .filter(p -> p.length() > 5)
                .isPresent();

        assertThat(isPasswordStrong).isTrue();

        isPasswordStrong = passwordOptional
                .filter(p -> p.length() < 5)
                .isPresent();

        assertThat(isPasswordStrong).isFalse();
    }

    @Test
    void givenCartItemPrice_whenPriceIsInRange_thenShouldReturnTrue() {
        CartItem cartItem = new CartItem("TeckTech 100Gb HD", "peripherals", 55.0);

        boolean isPriceInRange = Optional.of(cartItem)
                .map(CartItem::getPrice)
                .filter(p -> p > 40.0)
                .filter(p -> p < 56.0)
                .isPresent();

        assertThat(isPriceInRange).isTrue();
    }
}