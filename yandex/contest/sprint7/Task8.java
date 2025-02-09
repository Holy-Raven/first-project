package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task8 {

    private static int n;
    private static int m;
    private static int[][] points;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer st = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            points = new int[n][m];
            for (int i = 0; i < n; i++) {
                String line = reader.readLine().trim();
                for (int j = 0; j < m; j++) {
                    points[i][j] = Character.getNumericValue(line.charAt(j));
                }
            }

            int result = calculate();
            System.out.println(result);
        }
    }

    private static int calculate() {
        int[][] dp = new int[n][m];

        dp[0][0] = points[n - 1][0];

        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + points[n - 1][i];
        }

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + points[n - 1 - i][0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + points[n - 1 - i][j];
            }
        }

        return dp[n - 1][m - 1];
    }
}
