package com.example.lv6;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;
    private final Cart cart = Cart.getInstance(); // 장바구니

    public Kiosk(List<Menu> menus) {
        this.menus = new ArrayList<>(menus);
    }

    public void start() {
        // 스캐너 선언
        Scanner sc = new Scanner(System.in);

        // 반복문 시작
        while (true) {
            try {
                // 메인메뉴 선택 안내
                printMainMenuList();

                // 숫자 입력 받기
                int choiceCategory = sc.nextInt();
                if (choiceCategory == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                // 장바구니에 담긴 메뉴가 있다면
                if (!cart.getCartList().isEmpty()) {
                    // 조회
                    // 입력값 == [메뉴종류의 개수 + 1]
                    if (choiceCategory == menus.size() + 1) {
                        System.out.printf("%n[ Orders ]%n");

                        // 장바구니에 있는 메뉴들을 출력한다.
                        for (MenuItem menuItem : cart.getCartList()) {
                            System.out.printf("🥄 %s %n", menuItem.toString());
                        }

                        // 장바구니에 있는 메뉴들의 가격 합계를 출력한다.
                        System.out.printf("%n[ Total ]%n");
                        System.out.printf("💰 W %s %n%n", cart.getTotalPrice());

                        System.out.println("1. 주문       2. 메뉴판");
                        if ("1".equals(sc.next())) {
                            System.out.printf("%n주문이 완료되었습니다. 금액은 W %s 입니다.%n", cart.getTotalPrice());
                        }

                        continue;
                    }

                    // 주문 취소
                    // 입력값 == [메뉴종류의 개수 + 2]
                    if (choiceCategory == menus.size() + 2) {

                    }
                }

                // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                // List<Menu>에 인덱스로 접근하면 Menu만 추출할 수 있겠죠?
                Menu selectedMainMenu = menus.get(choiceCategory - 1);

                // 메뉴 아이템 선택 안내
                printMenuItemList(selectedMainMenu);

                // 숫자 입력 받기
                int choiceMenuItem = sc.nextInt();
                if (choiceMenuItem == 0) {
                    System.out.println("뒤로가기를 선택하셨습니다.");
                    continue;
                }

                // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
                // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.
                MenuItem selectedMenuItem = selectedMainMenu.getMenuItems().get(choiceMenuItem - 1);
                System.out.printf("✅ %s %n", selectedMenuItem.toString());

                // 장바구니 추가 여부 안내
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인       2. 취소");

                if ("1".equals(sc.next())) {
                    // 장바구니에 추가
                    cart.add(selectedMenuItem);

                    System.out.printf("%n🛒 %s 이 장바구니에 추가되었습니다.%n", selectedMenuItem.getName());
                    System.out.printf("%n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.%n");
                }
            } catch (IndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("해당 메뉴는 존재하지 않습니다.");
            } finally {
                sc.nextLine(); // 버퍼 비우기
            }
        }

        sc.close();
    }

    // 메인메뉴 선택 안내
    private void printMainMenuList() {
        System.out.printf("%n[ MAIN MENU ]%n");

        // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }

        System.out.printf("0. 종료%n");

        if (!cart.getCartList().isEmpty()) {
            System.out.printf("%n[ ORDER MENU ]%n");
            System.out.printf("%d. %-10s | %s %n", menus.size() + 1, "Orders", "장바구니를 확인 후 주문합니다.");
            System.out.printf("%d. %-10s | %s %n", menus.size() + 2, "Cancel", "진행중인 주문을 취소합니다.");
        }
    }

    // 메뉴 아이템 선택 안내
    private void printMenuItemList(Menu selectedMainMenu) {
        // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력
        System.out.printf("%n[ %S MENU ]%n", selectedMainMenu.getCategory());
        selectedMainMenu.printMenuItemAll();
        System.out.printf("0. 뒤로가기%n");
    }
}
