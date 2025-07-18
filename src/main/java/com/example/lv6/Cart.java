package com.example.lv6;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart cart;
    private final Map<MenuItem, Integer> cartList = new HashMap<>();

    // 싱글턴 패턴 적용
    private Cart() {}

    public static Cart getInstance() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public Map<MenuItem, Integer> getCartList() {
        return cartList;
    }

    // 메뉴 합계금액 구하기
    public BigDecimal getTotalPrice() {
        return cartList.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 장바구니 추가
    public void put(MenuItem menuItem) {
        cartList.put(menuItem, cartList.getOrDefault(menuItem, 0) + 1);
    }

    // 장바구니 비우기
    public void clear() {
        cartList.clear();
    }
}
