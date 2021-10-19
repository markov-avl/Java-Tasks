package com.company;

import java.util.regex.Pattern;

/**
Обработка строк. Регулярные выражения

Разработать статическую функцию типа static bool isCorrect(String str), принимающую на вход строку и проверяющую,
корректно ли заданы:
- почтовый адрес проживания,

Предпочтительно использовать механизм регулярных выражений.
 */

public class Main {

    public static void main(String[] args) {
        System.out.println(isCorrectPostalAddress("Россия, Приморский край, г. Санкт-Петербург, " +
                "проспект 100 лет Владивостоку, 58а, квартира 6"));
        System.out.println(isCorrectPostalAddress("Москва, переулок Малый Николопесковский, 11/2, кв. 17"));
        System.out.println(isCorrectPostalAddress("Россия, Московская область, город Москва, " +
                "переулок Малый Николопесковский, 11/2, квартира 17"));
        System.out.println(isCorrectPostalAddress("Россия, Московская область, гор. Москва, площадь Пушкина " +
                "дом Калатушкина, 7, кв. 7"));
    }

    /**
     * @param string - postal address like "Россия, {область}, {город}, {улица}, {номер дома}, {квартира}"
     * @return is postal address correct
     */
    static boolean isCorrectPostalAddress(String string) {
        String regex = "Россия," + // страна
                "\\s[а-яёА-ЯЁ]+\\s(край|область|автономный округ)," + // область
                "\\s(г.|гор.|город)\\s[- а-яёА-ЯЁ]+," + // город
                "\\s(дом|улица|проспект|переулок|тупик|площадь|кольцо)\\s[ а-яёА-ЯЁ\\d]+," + // улица
                "\\s[1-9]\\d*/?[1-9]?\\d*[a-я]?," + // дом
                "\\s(кв.|квартира)\\s[1-9]\\d*"; // квартира
        return Pattern.matches(regex, string);
    }
}
