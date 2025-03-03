package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sprint8Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());

            String[] packedStrings = new String[n];
            for (int i = 0; i < n; i++) {
                packedStrings[i] = reader.readLine().trim();
            }

            System.out.println(maxPrefix(packedStrings));
        }
    }

    // Метод для поиска наибольшего общего префиса
    private static String maxPrefix(String[] packedStrings) {
        List<String> unpackedStrings = new ArrayList<>();
        // Распаковка строк
        for (String packed : packedStrings) {
            unpackedStrings.add(unpack(packed));
        }

        String prefix = "";
        if (!unpackedStrings.isEmpty()) {
            // Используем первую строку как начальный префикс
            prefix = unpackedStrings.get(0);
            for (String current : unpackedStrings) {
                while (search(current, prefix).isEmpty()) {
                    // Сокращение префикса
                    prefix = prefix.substring(0, prefix.length() - 1);
                    if (prefix.isEmpty()) {
                        break;
                    }
                }
            }
        }

        return prefix;
    }

    // Рекурсивный метод распаковки строк
    private static String unpack(String string) {
        StringBuilder result = new StringBuilder();
        int num = 0;
        int index = 0;

        while (index < string.length()) {
            char symbol = string.charAt(index);

            // Обработка числа
            if (Character.isDigit(symbol)) {
                num = Integer.parseInt(Character.toString(symbol));
            } else if (symbol == '[') {
                int start = index + 1;
                int brackets = 1;

                while (brackets > 0) {
                    index++;
                    if (string.charAt(index) == '[') brackets++;
                    if (string.charAt(index) == ']') brackets--;
                }
                // Рекурсивный вызов подстроки
                String process = unpack(string.substring(start, index));
                for (int i = 0; i < num; i++) {
                    // Добавление распакованной подстроки
                    result.append(process);
                }
                num = 0;
            } else {
                // Добавление обычного символа
                result.append(symbol);
            }
            index++;
        }

        return result.toString();
    }

    // Метод эффективного поиска шаблона в тексте
    private static List<Integer> search(String text, String p) {
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] pi = new int[p.length()];
        Arrays.fill(pi, 0);
        int pi_prev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = pi_prev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = pi[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            if (i < p.length()) {
                pi[i] = k;
            }
            pi_prev = k;
            if (k == p.length()) {
                result.add(i - 2 * p.length());
            }
        }
        return result;
    }
}

/*
Для реализации функционала задачи нам нужно выполнить несколько действий. Сперва необходимо распаковать строки и привести
их к обычному виду, после чего мы сможем искать шаблоны в тексте и выбрать наибольший префикс для всех строк. Для распаковки
строк мы используем рекурсивный метод unpack. На вход метода подается обрабатываемая строка, мы посимвольно считываем каждый
символ. Если символ имеет числовое значение, мы сохраняем это значение в временной переменной num. Затем, мы ищем символ
открывающихся скобок, находим индексы открывающихся и закрывающихся скобок. Важно учитывать вложенность скобок. Вызываем
функцию снова для подстроки между этими индексами, результат помещаем в StringBuilder столько раз, сколько число, содержащееся
в переменной num. После обработки подстроки переменная num обнуляется, чтобы корректно обработать последующие числа или
символы. Продолжаем до конца строки. Распаковав строки мы приступаем к поиску наибольшего общего префикса, для этого используем
метод эффективного поиска шаблона в тексте search. Сравниваем первую распакованную строку с остальными, постепенно
сокращая предполагаемый префикс, пока он не станет подходящим для всех строк.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Распаковка каждой строки unpack работает за O(m), итого для всех строк O(n * m). Время работы метода search занимает O(n + m).
Поиск наибольшего префикса (метод maxPrefix) включает последовательное сравнение строк и уменьшение префикса, что в худшем
случае может занять O(nm^2). Общая сложность O(n * m^2). Где n - количество строк, m - их средняя длина.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храним входные данные, распакованные строки и текущий префикс. Общая пространственная сложность программы составляет O(n * m)
Где n - количество строк, m - их средняя длина.
*/

//https://contest.yandex.ru/contest/26133/run-report/134268036/
