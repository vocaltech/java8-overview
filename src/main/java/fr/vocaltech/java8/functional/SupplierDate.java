package fr.vocaltech.java8.functional;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class SupplierDate {
    public static Supplier<String> isoDate() {
        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        return  () -> DateTimeFormatter.ISO_INSTANT.format(now);
    }
}
