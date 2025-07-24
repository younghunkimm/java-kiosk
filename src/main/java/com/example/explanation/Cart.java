package com.example.explanation;

import java.util.HashMap;
import java.util.Map;

/**
 * <h2>💡 장바구니의 <b>데이터 저장소</b>를 담당하는 클래스</h2>
 */
public class Cart {
    private final Map<MenuItem, Integer> cartItems = new HashMap<>();

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }
}
