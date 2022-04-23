package fr.vocaltech.java8.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CartItem {
    private String description;
    private String category;
    private double price;

    public CartItem(String description, String category, double price) {
        this.description = description;
        this.category = category;
        this.price = price;
    }
}
