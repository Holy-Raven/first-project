package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task10 {

    private static int n;
    private static int maxSize;

    private static int[] arrayList;
    private static int[] resultList;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            n = Integer.parseInt(reader.readLine().trim());
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arrayList[i] = Integer.parseInt(st.nextToken());
            }

            calculate();

            System.out.println(maxSize);
            for (int idx : resultList) {
                System.out.print(idx + " ");
            }
        }
    }


    private static void calculate() {
        int[][] dp = new int[n + 1][n + 1];
        //тут код
    }
}
