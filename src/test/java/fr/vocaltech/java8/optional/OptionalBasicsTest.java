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

}