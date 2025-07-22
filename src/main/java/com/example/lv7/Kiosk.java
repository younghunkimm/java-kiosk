package com.example.lv7;

import java.math.BigDecimal;
import java.util.*;

public class Kiosk<T extends Number> {
    private final List<Menu<T>> menus;
    private final Scanner scanner;
    private final Cart<T> cart; // 장바구니

    public Kiosk(
            List<Menu<T>> menus,
            Scanner scanner,
            Cart<T> cart
    ) {
        this.menus = new ArrayList<>(menus);
        this.scanner = scanner;
        this.cart = cart;
    }

    public void start() {
        // 반복문 시작
        while (true) {
            try {
                // 메인메뉴 선택 안내
                printMainMenuList();

                // 숫자 입력 받기
                int choiceCategory = scanner.nextInt();
                if (choiceCategory == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                // 장바구니에 담긴 메뉴가 있다면
                if (!cart.getCartList().isEmpty()) {
                    // 조회
                    // 입력값 == [메뉴종류의 개수 + 1]
                    if (choiceCategory == menus.size() + 1) {
                        System.out.printf("%n아래와 같이 주문 하시겠습니까?%n");

                        System.out.printf("%n[ Orders ]%n");

                        // 장바구니에 있는 메뉴들을 출력한다.
                        for (Map.Entry<MenuItem<T>, Integer> entry : cart.getCartList().entrySet()) {
                            System.out.printf("🥄 %s (%d개) %n", entry.getKey().toString(), entry.getValue());
                        }

                        // 장바구니에 있는 메뉴들의 가격 합계를 출력한다.
                        System.out.printf("%n[ Total ]%n");
                        System.out.printf("💰 W %s %n%n", cart.getTotalPrice());

                        System.out.println("1. 주문       2. 메뉴판       3. 제거");
                        String choiceOrder = scanner.next();
                        if ("1".equals(choiceOrder)) {
                            // 할인 정보 출력
                            System.out.println("\n할인 정보를 입력해주세요.\n");
                            for (UserType userType : UserType.values()) {
                                System.out.printf("%d. %-15s : %s%%%n", userType.ordinal() + 1, userType.getName(), userType.getDiscount().multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toPlainString());
                            }

                            UserType selectedUserType = UserType.fromOrdinal(scanner.nextInt());
                            BigDecimal calcPrice = selectedUserType.getDiscountPrice(cart.getTotalPrice());

                            System.out.printf("%n👏 주문이 완료되었습니다. 금액은 W %s 입니다.%n", calcPrice.toPlainString());
                            cart.clear();
                        }
                        else if ("3".equals(choiceOrder)) {
                            // 장바구니 메뉴 제거
                            // Map은 index를 지원하지 않는다.
                            Set<MenuItem<T>> cartListKeys = cart.getCartList().keySet();
                            // 배열의 크기를 0으로 선언하면 자동으로 배열길이만큼 설정된다.
                            List<MenuItem<T>> keyList = new ArrayList<>(cartListKeys);

                            System.out.println("\n제거할 메뉴를 선택해주세요.\n");
                            for (int i = 0; i < keyList.size(); i++) {
                                MenuItem<T> key = keyList.get(i);
                                Integer value = cart.getCartList().get(key);
                                System.out.printf("%d. %s (%d개) %n", i + 1, key.toString(), value);
                            }
                            System.out.println("0. 취소");

                            int choiceRemove = scanner.nextInt();
                            MenuItem<T> removeItem = keyList.get(choiceRemove - 1);
                            String removeItemName = removeItem.getName();
                            cart.remove(removeItem);

                            System.out.printf("%n❌ %s 메뉴가 장바구니에서 삭제 되었습니다.%n", removeItemName);
                        }

                        continue;
                    }

                    // 주문 취소
                    // 입력값 == [메뉴종류의 개수 + 2]
                    if (choiceCategory == menus.size() + 2) {
                        System.out.printf("%n진행중인 주문을 취소하였습니다.%n");
                        cart.clear();

                        continue;
                    }
                }

                // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
                Menu<T> selectedMainMenu = menus.get(choiceCategory - 1);

                // 메뉴 아이템 선택 안내
                printMenuItemList(selectedMainMenu);

                // 숫자 입력 받기
                int choiceMenuItem = scanner.nextInt();
                if (choiceMenuItem == 0) {
                    System.out.println("뒤로가기를 선택하셨습니다.");
                    continue;
                }

                // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
                MenuItem<T> selectedMenuItem = selectedMainMenu.getMenuItems().get(choiceMenuItem - 1);
                System.out.printf("✅ %s %n", selectedMenuItem.toString());

                // 장바구니 추가 여부 안내
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.println("1. 확인       2. 취소");

                if ("1".equals(scanner.next())) {
                    // 장바구니에 추가
                    cart.put(selectedMenuItem);

                    System.out.printf("%n🛒 %s 이 장바구니에 추가되었습니다.%n", selectedMenuItem.getName());
                    System.out.printf("%n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.%n");
                }
            } catch (IndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("해당 메뉴는 존재하지 않습니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } finally {
                scanner.nextLine(); // 버퍼 비우기
            }
        }
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
    private void printMenuItemList(Menu<T> selectedMainMenu) {
        // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력
        System.out.printf("%n[ %S MENU ]%n", selectedMainMenu.getCategory());
        selectedMainMenu.printMenuItemAll();
        System.out.printf("0. 뒤로가기%n");
    }
}
