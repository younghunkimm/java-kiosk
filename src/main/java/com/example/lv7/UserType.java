package com.example.lv7;

import java.math.BigDecimal;

public enum UserType {
    PATRIOT("국가유공자", "0.1"),
    SOLDIER("군인", "0.05"),
    STUDENT("학생", "0.03"),
    GENERAL("일반", "0");

    private final String name;
    private final BigDecimal discount;

    UserType(String name, String discount) {
        this.name = name;
        this.discount = new BigDecimal(discount);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public static UserType fromOrdinal(int i) throws IllegalArgumentException {
        for (UserType userType : UserType.values()) {
            if (userType.ordinal() + 1 == i) return userType;
        }
        throw new IllegalArgumentException("존재하지 않는 회원 분류입니다.");
    }

    public BigDecimal getDiscountPrice(BigDecimal price) {
        return price.multiply(BigDecimal.ONE.subtract(getDiscount())).stripTrailingZeros();
    }
}
