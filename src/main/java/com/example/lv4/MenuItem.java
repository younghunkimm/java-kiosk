package com.example.lv4;

import java.math.BigDecimal;
import java.util.List;

public class MenuItem {
    private static int count = 0;
    private final int num;
    private final String name;
    private final BigDecimal price;
    private final String description;

    public MenuItem(String name, BigDecimal price, String description) {
        this.num = ++count;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void printInfo() {
        System.out.printf("%d. %-15s | W %10s | %s %n", getNum(), getName(), getPrice(), getDescription());
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.stripTrailingZeros();
    }

    public String getDescription() {
        return description;
    }

    public static MenuItem fromNum(List<MenuItem> menuItems, int num) throws IllegalArgumentException {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getNum() == num)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("선택하신 메뉴는 존재하지 않습니다."));
    }
}
