package ru.vasmirnov.interviewtask.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerValidationConstants {

    // номер телефона в формате 79xxxxxxxxx
    public static final String TELEPHONE_NUMBER_REGEX = "^79\\d{9}$";

    public static final String TWO_OR_MORE_WORDS_REGEX = "^\\p{L}+(\\s\\p{L}+)+$";

    public static final String NOT_CYRILLIC_REGEX = "^[^а-яА-Я]+$";


}
