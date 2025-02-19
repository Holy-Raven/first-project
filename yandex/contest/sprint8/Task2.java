package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine().toLowerCase();
            String s2 = reader.readLine().toLowerCase();

            if (calculate(s1, s2)) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL");
            }
        }
    }

    private static boolean calculate(String s1, String s2) {

        int l1 = s1.length();
        int l2 = s2.length();

        if (s1.equals(s2)) {
            return true;
        }
        if (Math.abs(l1 - l2) > 1) {
            return false;
        }

        int x = 0;
        int i = 0;
        int j = 0;

        while (i < l1 && j < l2) {

            if (s1.charAt(i) != s2.charAt(j)) {
                if (x++ >= 1) {
                    return false;
                }

                if (l1 > l2) {
                    i++;
                } else if (l1 < l2) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            } else {
                i++;
                j++;
            }
        }

        return true;
    }
}
