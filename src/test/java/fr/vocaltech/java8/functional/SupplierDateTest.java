package fr.vocaltech.java8.functional;

import lombok.var;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SupplierDateTest {
    @Test
    void givenSupplierIsoDate_thenReturnIsoDate() {
        var supplierDate = SupplierDate.isoDate();
        var isoDate = supplierDate.get();

        System.out.println(isoDate);
    }
}