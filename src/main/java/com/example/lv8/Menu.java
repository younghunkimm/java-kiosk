package com.example.lv8;

import java.util.ArrayList;
import java.util.List;

public class Menu<T extends Number> {
    // 카테고리
    private final String category;

    // MenuItem 클래스를 List로 관리
    private final List<MenuItem<T>> menuItems = new ArrayList<>();

    // 생성자
    public Menu(String category) {
        this.category = category;
    }

    // List에 들어있는 MenuItem을 순차적으로 보여주는 함수
    public void printMenuItemAll() {
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem<T> menuItem = menuItems.get(i);
            System.out.printf("%d. %s %n", i + 1, menuItem.toString());
        }
    }

    // List를 리턴하는 함수
    public List<MenuItem<T>> getMenuItems() {
        return menuItems;
    }

    // Category를 리턴하는 함수
    public String getCategory() {
        return category;
    }

    // MenuItem을 배열에 추가
    public void add(MenuItem<T> menuItem) {
        menuItems.add(menuItem);
    }
}
