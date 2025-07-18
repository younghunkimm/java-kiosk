package com.example.lv6;

import java.math.BigDecimal;

public class MenuItem {
    // 이름, 가격, 설명 필드 선언하여 관리
    private final String name;
    private final BigDecimal price;
    private final String description;

    // 생성자
    public MenuItem(String name, String price, String description) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.description = description;
    }

    // 구조에 맞게 함수를 선언해놓고 가져다 사용하세요.
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.stripTrailingZeros();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "%-15s | W %10s | %s".formatted(name, price, description);
    }
}
