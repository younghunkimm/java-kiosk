package com.example.lv4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Menu 객체 생성을 통해 이름 설정
        List<MenuItem> burgerMenuItems = new ArrayList<>();
        burgerMenuItems.add(new MenuItem("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("SmokeShack", new BigDecimal("18.992900"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("Cheeseburger", new BigDecimal("7.9231231"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenuItems.add(new MenuItem("Hamburger", new BigDecimal("5.4430"), "비프패티를 기반으로 야채가 들어간 기본버거"));

        List<MenuItem> drinkMenuItems = new ArrayList<>();
        drinkMenuItems.add(new MenuItem("Coke", new BigDecimal("3.23"), "코카콜라"));
        drinkMenuItems.add(new MenuItem("Milk", new BigDecimal("2.5"), "우유"));
        drinkMenuItems.add(new MenuItem("Water", new BigDecimal("1.1"), "시원한 물"));

        List<MenuItem> dessertMenuItems = new ArrayList<>();
        dessertMenuItems.add(new MenuItem("Cookie", new BigDecimal("2.4"), "쿠키"));
        dessertMenuItems.add(new MenuItem("French Fries", new BigDecimal("1.812"), "감자튀김"));
        dessertMenuItems.add(new MenuItem("Fried Chicken", new BigDecimal("9.99"), "후라이드 치킨"));

        // Menu 클래스 내 있는 List<MenuItem> 에 MenuItem 객체 생성하면서 삽입
        Menu burgerMenu = new Menu("Burgers", burgerMenuItems);
        Menu drinkMenu = new Menu("Drinks", drinkMenuItems);
        Menu dessertMenu = new Menu("Desserts", dessertMenuItems);

        List<Menu> menus = new ArrayList<>(Arrays.asList(burgerMenu, drinkMenu, dessertMenu));

        // Kiosk 객체 생성
        Kiosk kiosk = new Kiosk(menus);

        // Kiosk 내 시작하는 함수 호출
        kiosk.start();
    }
}
