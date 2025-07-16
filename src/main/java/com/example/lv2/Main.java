package com.example.lv2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // List 선언 및 초기화
        List<MenuItem> menuItems = new ArrayList<>();

        // add 함수를 통해 new MenuItem(이름, 가격, 설명) List에 삽입
        menuItems.add(new MenuItem("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", new BigDecimal("18.992900"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", new BigDecimal("7.9231231"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", new BigDecimal("5.4430"), "비프패티를 기반으로 야채가 들어간 기본버거"));

        // Scanner 선언
        Scanner scanner = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");

        // 반복문을 활용해 List 안에 있는 MenuItem을 하나씩 출력
        for (MenuItem menuItem : menuItems) {
            menuItem.printInfo();
        }

        System.out.println("0. 종료");

        try {
            // 숫자를 입력 받기
            int selected = scanner.nextInt();

            // 입력된 숫자에 따른 처리
            if (selected == 0) {
                // 프로그램을 종료
                System.out.println("프로그램을 종료합니다.");
            } else {
                // 선택한 메뉴 : 이름, 가격, 설명
                MenuItem.fromNum(menuItems, selected).printInfo();
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력값입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
