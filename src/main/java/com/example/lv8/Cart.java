package com.example.lv8;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.example.lv7.utils.NumberUtils.convertToBigDecimal;

public class Cart<T extends Number> {
    private final Map<MenuItem<T>, Integer> cartList = new HashMap<>();

    public Map<MenuItem<T>, Integer> getCartList() {
        return cartList;
    }

    // 메뉴 합계금액 구하기
    public BigDecimal getTotalPrice() {
        return cartList.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(convertToBigDecimal(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // 장바구니 추가
    public void put(MenuItem<T> menuItem) {
        cartList.put(menuItem, cartList.getOrDefault(menuItem, 0) + 1);
    }

    // 장바구니 제거 (1개씩)
    public void remove(MenuItem<T> menuItem) throws IllegalArgumentException {
        if (!cartList.containsKey(menuItem)) {
            throw new IllegalArgumentException("메뉴가 존재하지 않아 삭제하실 수 없습니다.");
        }

        int qty = cartList.get(menuItem);
        if (qty > 1) {
            cartList.replace(menuItem, qty - 1);
        } else {
            cartList.remove(menuItem);
        }
    }

    // 장바구니 비우기
    public void clear() {
        cartList.clear();
    }
}
