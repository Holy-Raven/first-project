package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sprint7Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String s = reader.readLine().trim();
            String t = reader.readLine().trim();

            int result = levenshtein(s, t);
            System.out.println(result);
        }
    }

    private static int levenshtein(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[] dp = new int[m + 1];

        // Базовый случай:
        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            // Временно сохраняем dp[j] перед обновлением.
            int last = dp[0];
            // Базовый случай для текущей строки
            dp[0] = i;
            for (int j = 1; j <= m; j++) {
                // Сохраняем текущее значение для следующей итерации.
                int temp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Если символы совпадают, операция не нужна
                    dp[j] = last;
                } else {
                    // Минимум из трех вариантов:
                    int delete = dp[j] + 1 ;       // Удаление символа
                    int insert = dp[j - 1] + 1;    // Вставка символа
                    int replace = last + 1;        // Замена символа

                    dp[j] = Math.min(delete, Math.min(insert, replace));
                }
                last = temp; // Обновляем last для следующей итерации
            }
        }

        return dp[m];
    }
}

/*
Для вычисления расстояния Левенштейна между строками s и t мы используем одномерный массив dp размера (m + 1),
где m — длина строки t. Массив dp обновляется построчно, при этом значения предыдущей строки временно сохраняются
во переменной last, то есть «предыдущая строка» хранится не в отдельном массиве, а в тех значениях, которые ещё
не были перезаписаны в dp.

Сначала инициализируется базовый случай: dp[i] = i, так как что бы из пустой строки s получить первые i символов t, необходимо
сделать i вставок. Затем, для каждого символа строки s (i от 1 до n) запускается внутренний цикл по j от 1 до m: если
s.charAt(i - 1) и t.charAt(j - 1) совпадают, никакой дополнительной операции не нужно, и dp[j] = last; Иначе мы рассматриваем
удаление, вставку или замену и берём минимум из этих трёх вариантов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для каждой пары (i, j) мы делаем постоянное число сравнений и вычислений, и таких пар n * m. Следовательно, алгоритм
работает за O(n * m).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Используем одномерный массив dp размера (m + 1) и несколько переменных, то есть O(m) памяти.
*/

//https://contest.yandex.ru/contest/25597/run-report/133489095/
