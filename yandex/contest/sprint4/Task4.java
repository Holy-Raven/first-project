package yandex.contest.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task4 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int a = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String s = reader.readLine();

            System.out.println(process(s, a, m));
        }
    }

    public static int process(String s, int a, int m) {

        char[] n = s.toCharArray();
        int result = 0;

        for (char symbol : n) {
            result = Math.floorMod((long) result * a + symbol, m);
        }

        return result;
    }
}
