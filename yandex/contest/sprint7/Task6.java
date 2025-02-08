package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task6 {

    private static int n;
    private static int k;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            int result = calculate();
            System.out.println(result);
        }
    }

    private static int calculate() {
        final int MOD = 1_000_000_007;

        List<Integer> dp = new ArrayList<>();
        dp.add(0);      // dp[0] не используется
        dp.add(1);      // dp[1] только один способ оказаться на первой ступеньки

        // Вычисляем dp[i] для i от 2 до n
        for (int i = 2; i <= n; i++) {
            int ways = 0;
            // Перебираем все возможные прыжки от 1 до k
            for (int j = 1; j <= k; j++) {
                int prevStep = i - j - 1;
                if (prevStep < 1) {
                    // Если предыдущей ступеньки нет, прекращаем перебор
                    break;
                }
                ways = (ways + dp.get(prevStep)) % MOD;
            }
            // Добавляем вычисленное значение dp[i] в список
            dp.add(ways);
        }

        return dp.get(n);
    }
}
