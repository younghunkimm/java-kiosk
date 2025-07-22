package com.example.lv8;

import java.math.BigDecimal;

import static com.example.lv7.utils.NumberUtils.convertToBigDecimal;

public class MenuItem<T extends Number> {
    // 이름, 가격, 설명 필드 선언하여 관리
    private final String name;
    private final T price;
    private final String description;

    // 생성자
    public MenuItem(String name, T price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // 기능
    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return convertToBigDecimal(price).stripTrailingZeros();
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "%-15s | W %10s | %s".formatted(getName(), getPrice(), getDescription());
    }
}
