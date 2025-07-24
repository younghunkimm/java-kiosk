package com.example.explanation;

import java.util.List;

public class Kiosk {
    private final List<Menu> menus;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
