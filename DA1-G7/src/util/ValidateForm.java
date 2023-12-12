/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.regex.Pattern;

/**
 *
 * @author cuongwf
 */
public class ValidateForm {

    public static boolean isCheckEmpty(String str) {
        return str.trim().isEmpty();
    }

    public static boolean isPositiveNumber(String str) {
        String regex = "^[1-9]\\d*$";
        return Pattern.matches(regex, str);
    }

    public static boolean isPositiveFloat(String str) {
        String regex = "^[1-9]\\d*(\\.\\d+)?$";
        return Pattern.matches(regex, str);
    }
}
