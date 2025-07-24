package com.example.explanation;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<MenuItem, Integer> cartItems = new HashMap<>();

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }
}
