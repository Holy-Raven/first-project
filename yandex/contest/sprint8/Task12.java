package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task12 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine().trim();
            int[] result = calculate(str);

            StringBuilder builder = new StringBuilder();
            for (int index : result) {
                builder.append(index).append(" ");
            }

            System.out.println(builder.toString().trim());
        }
    }

    private static int[] calculate(String s) {

        int n = s.length();
        int[] pi = new int[n];
        pi[0] = 0;

        for (int i = 1; i < n; i++) {
            int k = pi[i-1];
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = pi[k-1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }
}
