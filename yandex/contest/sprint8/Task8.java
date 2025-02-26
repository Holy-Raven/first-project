package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task8 {


    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String text = reader.readLine().trim();
            String before = reader.readLine().trim();
            String after = reader.readLine().trim();


            List<Integer> positions = search(text, before);

            String result = replace(text, before, after, positions);

            System.out.println(result);
        }
    }

    private static List<Integer> search(String text, String p) {

        // Функция возвращает все позиции вхождения шаблона в тексте.
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] π = new int[p.length()];  // Массив длины |p|.
        Arrays.fill(π, 0);
        int π_prev = 0;
        for (int i = 1; i < s.length(); i++) {
            int k = π_prev;
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = π[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            // Запоминаем только первые |p| значений π-функции.
            if (i < p.length()) {
                π[i] = k;
            }
            // Запоминаем последнее значение π-функции.
            π_prev = k;
            // Если значение π-функции равно длине шаблона, то вхождение найдено.
            if (k == p.length()) {
                // i - это позиция конца вхождения шаблона.
                // Дважды отнимаем от него длину шаблона, чтобы получить позицию начала:
                //  - чтобы «переместиться» на начало найденного шаблона,
                //  - чтобы не учитывать добавленное "pattern#".
                result.add(i - 2 * p.length());
            }
        }
        return result;
    }

    private static String replace(String text, String before, String after, List<Integer> positions) {
        char[] textArray = text.toCharArray();
        char[] afterArray = after.toCharArray();
        StringBuilder builder = new StringBuilder();

        int index = 0;
        int beforeLen = before.length();

        for (int pos : positions) {
            while (index < pos) {
                builder.append(textArray[index]);
                index++;
            }
            for (int i = 0; i < after.length(); i++) {
                builder.append(afterArray[i]);
            }
            index += beforeLen;
        }
        while (index < textArray.length) {
            builder.append(textArray[index]);
            index++;
        }

        return builder.toString();
    }
}