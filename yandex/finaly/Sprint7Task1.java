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

        int[][] dp = new int[n + 1][m + 1];

        // Базовые случаи:
        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Если символы совпадают, операция не нужна
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Минимум из трех вариантов:
                    int delete = dp[i - 1][j] + 1;        // Удаление символа из s
                    int insert = dp[i][j - 1] + 1;        // Вставка символа в s
                    int replace = dp[i - 1][j - 1] + 1;   // Замена символа

                    dp[i][j] = Math.min(delete, Math.min(insert, replace));
                }
            }
        }

        return dp[n][m];
    }
}

/*
Для вычисления расстояния Левенштейна между двумя строками s и t мы используем массив dp размера (n + 1) x (m + 1),
где n — длина s, а m — длина t. Ячейка dp[i][j] будет означать минимальное число операций (вставка, удаление, замена),
чтобы преобразовать первые i символов s в первые j символов t.

Сначала инициализируются базовые случаи: dp[i][0] = i, так как чтобы из первых s.length() символов s получить пустую строку,
требуется удалить все s.length() символов, и dp[0][j] = j, так как чтобы из пустой строки получить первые t.length()
символов t, нужно вставить t.length() символов. Без таких начальных значений таблица не сможет корректно учесть операции,
когда удаляем все символы или вставляем все необходимые символы.. Далее, если символы s[i-1] и t[j-1] совпадают, никакой
дополнительной операции не нужно, и dp[i][j] = dp[i-1][j-1]. Иначе мы рассматриваем удаление, вставку или замену и берём
минимум из этих трёх вариантов.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Для каждой пары (i, j) мы делаем постоянное число сравнений и вычислений, и таких пар n * m. Следовательно, алгоритм
работает за O(n * m).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храним двумерный массив dp размером (n + 1) * (m + 1), то есть O(n * m) памяти.
*/

//https://contest.yandex.ru/contest/25597/run-report/133395085/
