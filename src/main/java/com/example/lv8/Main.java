package com.example.lv8;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu<Double> burgerMenu = new Menu<>("Burger Menu");
        burgerMenu.add(new MenuItem<>("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.add(new MenuItem<>("SmokeShack", 18.992900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.add(new MenuItem<>("Cheeseburger", 7.9231231, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.add(new MenuItem<>("Hamburger", 5.4430, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu<Double> drinkMenu = new Menu<>("Drinks Menu");
        drinkMenu.add(new MenuItem<>("Coke", 3.23, "코카콜라"));
        drinkMenu.add(new MenuItem<>("Milk", 2.5, "우유"));
        drinkMenu.add(new MenuItem<>("Water", 1.1, "시원한 물"));

        Menu<Double> dessertMenu = new Menu<>("Dessert Menu");
        dessertMenu.add(new MenuItem<>("Cookie", 2.4, "쿠키"));
        dessertMenu.add(new MenuItem<>("French Fries", 1.812, "감자튀김"));
        dessertMenu.add(new MenuItem<>("Fried Chicken", 9.99, "후라이드 치킨"));

        List<Menu<Double>> menus = new ArrayList<>(List.of(burgerMenu, drinkMenu, dessertMenu));

        // Kiosk 객체 생성
        Kiosk<Double> kiosk = new Kiosk<>(menus);

        // Kiosk 내 시작하는 함수 호출
        kiosk.start();
    }
}
