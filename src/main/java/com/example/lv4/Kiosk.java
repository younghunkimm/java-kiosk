package com.example.lv4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;
    private List<MenuItem> menuitems;

    public Kiosk(List<Menu> menus) {
        this.menus = new ArrayList<>(menus);
    }

    public void start() {
        // 스캐너 선언
        Scanner sc = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            System.out.println("[ MAIN MENU ]");

            // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
            for (int i = 0; i < menus.size(); i++) {
                System.out.println((i + 1) + ". " + menus.get(i).getCategory());
            }

            System.out.println("0. 종료");

            // 숫자 입력 받기
            // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
            // List<Menu>에 인덱스로 접근하면 Menu만 추출할 수 있겠죠?
            Menu selectedMainMenu = menus.get(sc.nextInt() - 1);

            // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력
            System.out.println("[ " + selectedMainMenu.getCategory().toUpperCase() + " MENU ]");
            selectedMainMenu.printMenuItemAll();
            System.out.println("0. 뒤로가기");

            // 숫자 입력 받기
            // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
            // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.

            // FIXME: 임시 break 제거!!
            break;
        }
    }
}
