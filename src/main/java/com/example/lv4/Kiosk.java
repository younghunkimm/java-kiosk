package com.example.lv4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<MenuItem> menuItems;

    /**
     * 버거메뉴들의 배열을 받아와 {@code menuItems} 필드에 할당한다.
     *
     * @param menuItems 버거메뉴들의 배열
     */
    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void start() {
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
