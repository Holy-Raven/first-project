package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task12 {

    private static int n;
    private static int m;
    private static int[] weights;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            weights = new int[n];
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                weights[i] = Integer.parseInt(st.nextToken());
            }

            int result = calculate();
            System.out.println(result);
        }
    }

    private static int calculate() {
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            int w = weights[i];
            for (int j = m; j >= w; j--) {
                if (dp[j - w]) {
                    dp[j] = true;
                }
            }
        }

        for (int i = m; i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }

        return 0;
    }
}
