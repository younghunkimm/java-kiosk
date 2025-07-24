package com.example.explanation;

import java.util.Map;

/**
 * <h2>💡 장바구니의 <b>비즈니스 로직</b>을 담당하는 클래스</h2>
 *
 * <p>메뉴 담기, 장바구니 비우기, 금액 계산 등...</p>
 */
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
