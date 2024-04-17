package ru.khomyakov;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean isRomeNumber = false;

    public static void main(String[] args) throws Exception {
        String input;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
        }

        String result = calc(input);

        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        String[] expression = input.split(" ");
        int first, second, resultNum = 0;
        String result;

        inputStringValid(expression);

        if (isRomeNumber) {
            first = romanToArabic(expression[0]);
            second = romanToArabic(expression[2]);
        } else {
            first = Integer.parseInt(expression[0]);
            second = Integer.parseInt(expression[2]);
        }

        switch (expression[1]) {
            case "+" -> resultNum = first + second;
            case "-" -> resultNum = first - second;
            case "*" -> resultNum = first * second;
            case "/" -> resultNum = divideWithRound(first, second);
        }

        if (isRomeNumber) {
            romanOutputValid(resultNum);
            result = intToRoman(resultNum);
        } else {
            result = String.valueOf(resultNum);
        }
        return result;
    }

    private static void romanOutputValid(int resultNum) throws Exception {
        if (resultNum <= 0) {
            throw new WrongRomeExpressionException("В римской системе нет отрицательных чисел");
        }
    }

    private static String intToRoman(int num) {
        String[] keys = new String[] {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] vals = new int[] {100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder result = new StringBuilder();
        int index = 0;

        while(index < keys.length) {
            while(num >= vals[index]) {
                int d = num / vals[index];
                num = num % vals[index];
                result.append(keys[index].repeat(d));
            }

            index++;
        }

        return result.toString();
    }

    private static int divideWithRound(int first, int second) {
        if (second == 0) {
            throw new ArithmeticException("Деление на ноль невозможно.");
        }

        return first / second;
    }

    private static int romanToArabic(String s) {
        return RomeNumber.valueOf(s).getNumber();
    }

    private static void inputStringValid(String[] lines) throws Exception {

        if (lines.length > 3) {
            throw new WrongExpressionException("Формат математической операции не удовлетворяет заданию: два операнда и один оператор (+, -, /, *)");
        }

        if (lines.length < 3) {
            throw new WrongExpressionException("Строка не является математической операцией");
        }

        if (!inputOperationValid(lines[1])) {
            throw new WrongOperatorException("Оператор не соответствует заданию (+, -, /, *)");
        }

        if (!inputNumbersValid(lines[0], lines[2])) {
            throw new WrongOperandsException("Используются одновременно разные системы счисления");
        }
    }

    private static boolean inputOperationValid(String input) {
        for (Operation operate : Operation.values()) {
            if (operate.getOperate().equals(input)) {
                return true;
            }
        }
        return false;
    }

    private static boolean inputNumbersValid(String first, String second) {
        boolean isRomeFirst = false, isRomeSecond = false;
        for (RomeNumber num : RomeNumber.values()) {
            if (num.name().equals(first)) {
                isRomeFirst = true;
            }
            if (num.name().equals(second)) {
                isRomeSecond = true;
            }
        }
        if (isRomeFirst && isRomeSecond) {
            isRomeNumber = true;
            return true;
        }

        return "012345678910".contains(first) && "012345678910".contains(second) && inputArabicNumValid(first) && inputArabicNumValid(second);
    }

    private static boolean inputArabicNumValid(String input) {
        int number = Integer.parseInt(input);

        return number >= 1 && number < 11;
    }

}
