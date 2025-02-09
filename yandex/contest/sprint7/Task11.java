package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task11 {

    private static int n;
    private static int m;
    private static int maxSize;

    private static int[] nArrayList;
    private static int[] mArrayList;
    private static int[] nResultList;
    private static int[] mResultList;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            n = Integer.parseInt(reader.readLine().trim());
            nArrayList = readLine(reader, n);

            m = Integer.parseInt(reader.readLine().trim());
            mArrayList = readLine(reader, m);

            calculate();
            print();
        }
    }

    private static void calculate() {

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (nArrayList[i - 1] == mArrayList[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        maxSize = dp[n][m];
        nResultList = new int[maxSize];
        mResultList = new int[maxSize];

        int i = n, j = m;
        int pos = maxSize - 1;
        while (i > 0 && j > 0) {
            if (nArrayList[i - 1] == mArrayList[j - 1]) {
                nResultList[pos] = i;
                mResultList[pos] = j;
                i--;
                j--;
                pos--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
    }

    private static int[] readLine(BufferedReader reader, int size) throws IOException {

        int[] array = new int[size];

        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        return array;
    }


    private static void print() {

        System.out.println(maxSize);

        for (int idx : nResultList) {
            System.out.print(idx + " ");
        }

        System.out.println();
        for (int idx : mResultList) {
            System.out.print(idx + " ");
        }
    }
}
