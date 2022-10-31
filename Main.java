package com.main;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String result = calc(input); // возвращаю строку из calc
        System.out.println("Результат операции: " + result);
    }
//        while (true) {
//            try {
//                String input = scanner.nextLine();
//                String result = calc(input); // возвращаю строку из calc
//                System.out.println("Результат операции: " + result);
//            } catch (Exception e) {
//                System.err.println("Ошибка! Повторите заново." + e.getMessage());
//            }
//        }
//    }
// 3+7
    public static String calc(String input) throws Exception { // метод с параметром строка
        char operator = getOperator(input); // создали оператора присвоили метод с параметром строки
        String[] split = input.split("[+*/-]"); // 1+1 на выход массив 1     1
        String numberOne = split[0].trim(); // trim чистит пробелы первая строка
        String numberTwo = split[1].trim(); // вторая строка
        boolean arabic = isArabic(numberOne) && isArabic(numberTwo); // да или нет(арабское или не арабское)

        if (arabic) {  // проверяется арабское число
            int numberOneArabic = Integer.parseInt(numberOne); // преобразование строки в число
            int numberTwoArabic = Integer.parseInt(numberTwo); // работа с арабскими числами
            validateRang (numberOneArabic); //  проверка границ числа
            validateRang (numberTwoArabic);
            String res = calc(numberOneArabic, numberTwoArabic, operator).toString(); // строка, которая получает значение двух чисел и оператора
            return res;
        } // проверяются римские числа
        int numberOneArabic = getArabicFromRoman(numberOne); // присваивание ключика из мапы чисел
        int numberTwoArabic = getArabicFromRoman(numberTwo); // работа с римскими числами
        int resArabic = calc(numberOneArabic, numberTwoArabic, operator); // присваивание переменной значения калька
        if (resArabic < 1) { // проверяется результат римских чисел, е сли меньше 1 то исключение
            throw new Exception("Результат меньше 1, не могу вывести римское число");
        }
        String res = getRomanFromArabic(resArabic); // строка принимает значения чисел из таблицы рим.чисел

        return res;
    }

    private static char getOperator(String input) throws Exception {  // 1+1 -> +
        for (char ch : input.toCharArray()) { // ищет знак + и возвращает значение
            if (ch == '+') {
                return ch;
            }
            if (ch == '-') {
                return ch;
            }
            if (ch == '*') {
                return ch;
            }
            if (ch == '/') {
                return ch;
            }
        }
        throw new Exception("Неподдерживаемая операция.");
    }

    private static boolean isArabic(String strNum) { // поверяет строку, что она арабская
        try { //прочитать про конструкцию
            Integer.parseInt(strNum); // из строки извлекает число Int
        } catch (NumberFormatException nfe) { // если происходит исключение, то это не араб
            return false;
        }
        return true;
    }

    private static void validateRang(int number) throws Exception {
        if(number< 1 || number > 10) { // если число параметр больше или меньше , то исключение
                throw new Exception("Введите значение от 1 до 10");
        }
    }

    public static Integer calc(int num1, int num2, char operation) throws Exception { // выполняет операцию с двумя числами и оператором
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new Exception("Операция не распознана. Повторите ввод.");

        }
    }

    private static int getArabicFromRoman(String number) throws Exception {
        HashMap<String, Integer> map = new HashMap<>(); // хешмап с параметрами и созлание новой мапы
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
        Integer value = map.get(number); // значение =  ключу
        if (value == null) { // если введено иное значение не из мапы, то исключение
            throw new Exception("Введете римское число от I до X");
        }
        return value;
    }

    private static String getRomanFromArabic(int num) { // метод с параметром инт, создается массив чисел
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",

                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",

                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",

                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",

                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",

                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",

                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"

        };
        return roman [num]; // возвращается параметр
    }
}

