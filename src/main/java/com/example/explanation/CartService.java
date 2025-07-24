package com.example.explanation;

import java.util.Map;

public class CartService {
    private final Cart cart;

    public CartService(Cart cart) {
        this.cart = cart;
    }

    public void addItem(MenuItem item) {
        Map<MenuItem, Integer> cartItems = cart.getCartItems();
        cartItems.merge(item, 1, Integer::sum);
    }

    public void clearCart() {
        cart.getCartItems().clear();
    }

    public boolean isEmpty() {
        return cart.getCartItems().isEmpty();
    }

    public void displayCartItems() {
        Map<MenuItem, Integer> cartItems = cart.getCartItems();
        if (cartItems.isEmpty()) {
            System.out.println("장바구니에 담긴 메뉴가 없습니다.");
            return;
        }

        cartItems.forEach((item, count) -> {
            System.out.println(
                    item.getName() + " | W " + item.getPrice() + " | " + item.getDescription() + " | " + count + "개"
            );
        });
    }

    public double getTotalPrice() {
        return cart.getCartItems().entrySet()
                .stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public double getDiscountedTotalPrice(UserType userType) {
        double total = getTotalPrice();
        double discountRate = userType.getDiscountRate();
        return total - (total * discountRate);
    }

    public void removeMenuItemByName(String name) {
        cart.getCartItems().entrySet().removeIf(entry -> entry.getKey().getName().equals(name));
    }
}
