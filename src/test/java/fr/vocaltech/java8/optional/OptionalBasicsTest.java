package fr.vocaltech.java8.optional;

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
}