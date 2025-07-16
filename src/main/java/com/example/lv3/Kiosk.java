package com.example.lv3;

import java.util.ArrayList;
import java.util.List;

public class Kiosk {
    private final List<MenuItem> menuItems;

    public Kiosk(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void start() {

    }
}
