package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint7Task2 {

    private static int[] scores;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine().trim());
            scores = new int[n];

            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(calculate() ? "True" : "False");
        }
    }

    private static boolean calculate() {

        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        // Если сумма нечётная, нельзя разбить на две равные части
        if (sum % 2 == 1) {
            return false;
        } else {
            int half = sum / 2;

            // Массив dp, где dp[s] показывает, можно ли набрать сумму s
            boolean[] dp = new boolean[half + 1];
            // Базовый случай, так как сумму 0 можно набрать без очков
            dp[0] = true;

            // Заполняем dp
            for (int score : scores) {
                // Для каждой суммы s проверяем, можно ли её набрать с учётом текущего значения score
                for (int s = half; s >= score; s--) {
                    if (dp[s - score]) {
                        dp[s] = true;
                    }
                }
            }
            // Если dp[half] = true, значит, можно набрать ровно половину суммы,
            // а оставшиеся очки автоматически образуют вторую половину.
            return dp[half];
        }
    }
}

/*
Используем динамическое программирование, чтобы проверить, можно ли разделить сумму очков на два равных подмножества.
Сначала вычисляем общую сумму - sum. Если она нечётная, разбиение невозможно — возвращаем False. Если сумма чётная, пытаемся
набрать её половину (half), так как вторая половина сформируется автоматически. Для этого создаём массив boolean[] dp размером
half + 1, где dp[s] показывает, можно ли набрать сумму s. Изначально dp[0] = true, так как сумму 0 можно набрать без очков.
Затем для каждого очка обновляем dp.

Если после обработки всех очков dp[half] = true, то разбиение возможно, иначе — False

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Перебираем n элементов, обновляя массив dp размером half. Алгоритмическая сложность O(n * half)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Храним одномерный массив dp размером O (half)
*/

//https://contest.yandex.ru/contest/25597/run-report/133400687/