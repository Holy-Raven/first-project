package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
            for (int i = 1; i < unpackedStrings.size(); i++) {
                prefix = getCommonPrefix(prefix, unpackedStrings.get(i));
                // Если префикс стал пустым — дальше нет смысла искать
                if (prefix.isEmpty()) {
                    break;
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

    // Возвращает общий префикс
    private static String getCommonPrefix(String s1, String s2) {
        int min = Math.min(s1.length(), s2.length());
        int i = 0;
        // Движемся вперёд, пока индексы не вышли за min
        while (i < min) {
            // Если встретили несовпадающие символы - прерываем
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
            i++;
        }
        return s1.substring(0, i);
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
Для реализации функционала задачи нам нужно выполнить несколько действий. Сперва необходимо распаковать строки и привести
их к обычному виду, после чего мы сможем искать шаблоны в тексте и выбрать наибольший префикс для всех строк. Для распаковки
строк мы используем рекурсивный метод unpack. На вход метода подается обрабатываемая строка, мы посимвольно считываем каждый
символ. Если символ имеет числовое значение, мы сохраняем это значение в временной переменной num. Затем, мы ищем символ
открывающихся скобок, находим индексы открывающихся и закрывающихся скобок. Важно учитывать вложенность скобок. Вызываем
функцию снова для подстроки между этими индексами, результат помещаем в StringBuilder столько раз, какое число, содержется
в переменной num. После обработки подстроки переменная num обнуляется. Продолжаем до конца строки. Распаковав строки мы
приступаем к поиску наибольшего общего префикса, ищем мы путём последовательного сравнения передавая текущее значение
префикса и следующую строку (getCommonPrefix)

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Распаковка каждой строки unpack работает за O(m), итого для всех строк O(n * m). Для нахождения общего префикса каждого
шага достаточно O(m) операций, а поскольку таких сравнений n−1, суммарная сложность составит O(n * m). Общую сложность
можно принять как O(n * m). Где n - количество строк, m - средняя длина распакованных строк. В худшем случае, когда у нас
строка вида: 9[9[9[...]]], длина распакованной строки сильно вырастет. Допустим в строку длиной 4 символа может быть 1
вхождение вида 9[a], а что бы было два вхождения нам нужно уже 7 символов. И сложность обработкт такой строки будет порядка
O(9^(l/4)), где l — длина упакованной строки. И если все строки будут такие, то общую сложность можно принять как O(n * 9^(l/4))

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храним входные данные, распакованные строки и текущий префикс. Общая пространственная сложность программы составляет O(n * m)
Где n - количество строк, m - их средняя длина. В худшем случае мы так же получим O(n * 9^(l/4)).
*/

//https://contest.yandex.ru/contest/26133/run-report/134485355/
