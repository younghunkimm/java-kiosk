package com.example.explanation;

import java.util.List;

/**
 * <h2>💡 키오스크의 <b>데이터 저장소</b>를 담당하는 클래스</h2>
 *
 * <p>외부에서 메뉴들에 대한 데이터를 주입받아 관리한다.</p>
 */
public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
