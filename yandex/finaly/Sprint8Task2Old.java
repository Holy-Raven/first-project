package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Sprint8Task2Old {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String baseLine = reader.readLine().trim();
            int n = Integer.parseInt(reader.readLine().trim());

            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = reader.readLine().trim();
            }

            System.out.println(isSplits(baseLine, words) ? "YES" : "NO");
        }
    }

    private static boolean isSplits(String baseLine, String[] words) {
        // СОздание массива dp, с начальным условием для пустой строки.
        boolean[] dp = new boolean[baseLine.length() + 1];
        dp[0] = true;

        // Проверки возможности разбиения.
        for (int i = 0; i < baseLine.length(); i++) {
            if (dp[i]) {
                // Попытка разбиения с использованием каждого слова.
                for (String word : words) {
                    if (i + word.length() <= baseLine.length() && search(baseLine.substring(i, i + word.length()), word).contains(0)) {
                        // Обновление dp если слово подходит.
                        dp[i + word.length()] = true;
                    }
                }
            }
        }

        // Возвращение результата для последнего символа строки.
        return dp[baseLine.length()];
    }


    // Метод эффективного поиска шаблона в тексте
    private static List<Integer> search(String text, String p) {
        List<Integer> result = new ArrayList<>();
        String s = p + "#" + text;
        int[] pi = new int[s.length()];
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
            if (i >= p.length() + 1) {
                pi[i] = k;
                if (k == p.length()) {
                    result.add(i - 2 * p.length());
                }
            }
            pi_prev = k;
        }
        return result;
    }
}

/*
Для решения задачи определения возможности разделения строки baseLine на слова из словаря применяем метод динамического
программирования. Создаём массив dp размером baseLine.length() + 1, где каждый элемент dp[i] равен true, если подстроку
baseLine от начала до i можно разбить на слова из словаря. Инициализируем dp[0] как true, поскольку пустую строку можно
считать разбитой. Поочередно проходим по каждой позиции i в строке baseLine. Если dp[i] равно true, проверяем каждое слово
из словаря на возможность его включения начиная с этой позиции. Для этого используем метод search, который проверяет,
можно ли вставить слово начиная с позиции i. Если search возвращает список индексов с первым вхождением равным 0, то
слово может быть использовано, и мы устанавливаем dp[i + word.length()] в true.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Временная сложность алгоритма составляет O(n * m * l), где n - длина строки baseLine, m - количество слов в словаре, и
l - максимальная длина слова. На каждой позиции строки потенциально проверяем каждое слово, а каждая проверка может
требовать времени до O(l).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храним входные данные. Создаём массив dp и используепм вспомогательные данные в методе search. Общая пространственная
сложность алгоритма составляет O(n + l), где n - размер массива dp, а l - максимальный размер вспомогательных данных.
*/

