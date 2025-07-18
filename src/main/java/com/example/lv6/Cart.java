package com.example.lv6;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart cart;
    private final List<MenuItem> cartList = new ArrayList<>();

    // 싱글턴 패턴 적용
    private Cart() {}

    public static Cart getInstance() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public List<MenuItem> getCartList() {
        return cartList;
    }

    // 메뉴 합계금액 구하기
    public BigDecimal getTotalPrice() {
        return cartList.stream()
                .map(MenuItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 장바구니 추가
    public void add(MenuItem menuItem) {
        cartList.add(menuItem);
    }

    // 장바구니 비우기
    public void clear() {
        cartList.clear();
    }
}
