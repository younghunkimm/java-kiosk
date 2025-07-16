package com.example.lv3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 버거메뉴들의 배열을 생성
        List<MenuItem> menuItems = Arrays.asList(
            new MenuItem("ShackBurger", new BigDecimal("6.9"), "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
            new MenuItem("SmokeShack", new BigDecimal("18.992900"), "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
            new MenuItem("Cheeseburger", new BigDecimal("7.9231231"), "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
            new MenuItem("Hamburger", new BigDecimal("5.4430"), "비프패티를 기반으로 야채가 들어간 기본버거")
        );

        // Kiosk 객체 생성 시 버거메뉴의 배열을 할당
        Kiosk kiosk = new Kiosk(menuItems);

        // Kiosk 로직 실행
        kiosk.start();
    }
}
