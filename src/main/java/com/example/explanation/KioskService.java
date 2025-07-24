package com.example.explanation;

import java.util.List;
import java.util.Scanner;

/**
 * <h2>💡 키오스크의 <b>비즈니스 로직</b>을 담당하는 클래스</h2>
 *
 * <ul>
 *     <li>{@code start()} 핵심 로직</li>
 *     <li>메인 로직은 {@code start()} 에서 관리하되 세부 로직들은 메소드로 분리</li>
 *     <li>다른 객체들의 비즈니스 로직들을 활용(정보 요청)</li>
 * </ul>
 */
public class KioskService {
    private final Scanner sc;
    private final Kiosk kiosk;
    private final CartService cartService;

    public KioskService(Scanner sc, Kiosk kiosk, CartService cartService) {
        this.sc = sc;
        this.kiosk = kiosk;
        this.cartService = cartService;
    }

    public void start() {
        while (true) {
            // 1) 메인 메뉴 출력
            List<Menu> menus = printMainMenu();

            // 2) 사용자 입력
            int menuChoice = sc.nextInt();

            // 3) 선택자별 처리
            switch (menuChoice) {
                case 0:
                    // 종료
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                case 4:
                    handleOrder();
                    break;
                case 5:
                    // [ ORDER MENU ] -> 주문
                    handleCancel();
                    break;
                default:
                    if (menuChoice < 0 || menuChoice > menus.size()) {
                        throw new IllegalArgumentException("잘못된 입력입니다.");
                    }

                    // 카테고리별 출력 ex) [ BURGER MENU ]
                    handleSelectedMenu(menuChoice, menus);
                    break;
            }
        }
    }

    // [MAIN MENU] 와 [ORDER MENU] 출력, 메뉴 목록 반환
    private List<Menu> printMainMenu() {
        System.out.println("[ MAIN MENU ]");
        List<Menu> menus = kiosk.getMenus();
        for (int i = 0; i < menus.size(); i++) {
            System.out.println((i + 1) + ". " + menus.get(i).getName());
        }
        System.out.println("0. 종료    | 종료");

        // 장바구니가 비어있지 않으면 [ ORDER MENU ] 출력
        if (!cartService.isEmpty()) {
            System.out.println();
            System.out.println("[ ORDER MENU ]");
            System.out.println("4. Orders        | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel        | 진행중인 주문을 취소합니다.");
        }

        return menus;
    }

    // 4번(주문) 처리 로직 + 할인 정보 적용
    private void handleOrder() {
        if (cartService.isEmpty()) {
            System.out.println("장바구니에 담긴 메뉴가 없습니다.");
            return;
        }

        System.out.println("아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        cartService.displayCartItems();
        System.out.println("\n[ Total ]");
        System.out.println("W " + cartService.getTotalPrice());

        System.out.println("1. 주문        2. 메뉴판        3. 부분취소");
        int orderChoice = sc.nextInt();
        switch (orderChoice) {
            case 1:
                UserType userType = getDiscountUserType();
                double finalPrice = cartService.getDiscountedTotalPrice(userType);

                System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.\n\n", finalPrice);
                cartService.clearCart();
                break;
            case 2:
                // 메뉴판으로 돌아감
                break;
            case 3:
                System.out.println("취소할 메뉴명을 입력하세요.");
                String name = sc.next();
                cartService.removeMenuItemByName(name);
                break;
            default:
                throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private UserType getDiscountUserType() {
        System.out.println("할인 정보를 입력해주세요.");
        System.out.println("1. 국가유공자 : 10%");
        System.out.println("2. 군인      : 5%");
        System.out.println("3. 학생      : 3%");
        System.out.println("4. 일반      : 0%");

        int discountChoice = sc.nextInt();
        return switch (discountChoice) {
            case 1 -> UserType.NATIONAL_MERIT;
            case 2 -> UserType.MILITARY;
            case 3 -> UserType.STUDENT;
            case 4 -> UserType.NORMAL;
            default -> throw new IllegalArgumentException("잘못된 입력입니다.");
        };
    }

    // 5번(취소) 처리 로직
    private void handleCancel() {
        if (cartService.isEmpty()) {
            System.out.println("장바구니에 담긴 메뉴가 없습니다.");
            return;
        }

        cartService.clearCart();
        System.out.println("진행중인 주문이 취소되었습니다.\n");
    }

    // 1~N 번 중 하나를 골랐을 때: 서브메뉴 목록 출력 후 아이템 선택 로직
    private void handleSelectedMenu(int menuChoice, List<Menu> menus) {
        Menu selectedMenu = menus.get(menuChoice - 1);
        List<MenuItem> menuItems = selectedMenu.getMenuItems();

        System.out.println("\n[ " + selectedMenu.getName() + " MENU ]");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println((i + 1) + ". "
                + menuItem.getName() + "    | W " + menuItem.getPrice()
                + " | " + menuItem.getDescription());
        }
        System.out.println("0. 뒤로가기\n");

        int menuItemChoice = sc.nextInt();
        if (menuItemChoice == 0) {
            return; // 뒤로가기
        }
        if (menuItemChoice <= 0 || menuItemChoice > menuItems.size()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        // 선택된 아이템 장바구니 추가 여부
        MenuItem selectedItem = menuItems.get(menuItemChoice - 1);
        System.out.printf("\"%s | W %.1f | %s\"\n위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인        2. 취소\n",
                selectedItem.getName(), selectedItem.getPrice(), selectedItem.getDescription());

        int addChoice = sc.nextInt();
        switch (addChoice) {
            case 1:
                cartService.addItem(selectedItem);
                System.out.printf("%s 이(가) 장바구니에 추가되었습니다.\n\n", selectedItem.getName());
                break;
            case 2:
                // 그냥 취소
                break;
            default:
                throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}