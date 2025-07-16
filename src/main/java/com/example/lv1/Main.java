package com.example.lv1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String[]> menuList = new ArrayList<>(
                Arrays.asList(
                        new String[] { "ShackBurger", "6.9", "토마토, 양상추, 쉑소스가 토핑된 치즈버거" },
                        new String[] { "SmokeShack", "8.9", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거" },
                        new String[] { "CheeseBurger", "6.9", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거" },
                        new String[] { "Hamburger", "5.4", "비프패티를 기반으로 야채가 들어간 기본버거" }
                )
        );

        System.out.println("[ SHAKESHACK MENU ]");

        for (int i = 0; i < menuList.size(); i++) {
            String[] menu = menuList.get(i);
            String menuName = menu[0];
            Double menuPrice = Double.valueOf(menu[1]);
            String menuDescription = menu[2];

            System.out.printf("%d. %-15s | W %.1f | %s %n", (i + 1), menuName, menuPrice, menuDescription);
        }

        System.out.println("0. 종료");

        try {
            int selected = scanner.nextInt();

            if (selected == 0) {
                System.out.println("프로그램을 종료합니다.");
            } else {
                String[] selectedMenu = menuList.get(selected - 1);
                String selectedMenuName = selectedMenu[0];
                Double selectedMenuPrice = Double.valueOf(selectedMenu[1]);
                String selectedMenuDescription = selectedMenu[2];

                System.out.printf(">> %-15s | W %.1f | %s %n", selectedMenuName, selectedMenuPrice, selectedMenuDescription);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("메뉴가 없습니다.");
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력값입니다.");
        }

        scanner.close();
    }
}