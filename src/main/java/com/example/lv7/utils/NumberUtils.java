package com.example.lv7.utils;

import java.math.BigDecimal;

public class NumberUtils {
    public static BigDecimal convertToBigDecimal(Number number) {
        return new BigDecimal(number.toString());
    }
}
