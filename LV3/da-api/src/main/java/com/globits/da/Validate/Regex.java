package com.globits.da.Validate;

public class Regex {
    public static final String REGEX_DATE = "\\d{4}-((1[0-2])|(0[1-9]))-((0[1-9])|([1-2][0-9])|(3[0-1]))$";
    public static final String REGEX_CODE = "^[0-9\\w-!@#$%^&*]{6,10}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z]+[a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@{1}[a-zA-Z]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$";
    public static final String REGEX_PHONE = "^[\\d]{11}$";
    public static final int MAX_SAME_TYPE_DIPLOMA = 3;
    public static final float AREA_MIN = 0f;
}
