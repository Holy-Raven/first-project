package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint1Task2 {

    private static final int SIDE_SIZE = 4;

    public static void main(String[] args) throws IOException {

        int[] storage = new int[10];
        int k = 0;
        int resultCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            k = Integer.parseInt(reader.readLine());

            for (int i = 0; i < SIDE_SIZE; ++i) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                char[] chars = tokenizer.nextToken().toCharArray();

                for (int j = 0; j < SIDE_SIZE; ++j) {
                    if (chars[j] == '.') {
                        storage[0]++;
                    } else {
                        int value = Character.getNumericValue(chars[j]);
                        storage[value]++;
                    }
                }
            }

        }

        for (int i = 1; i < storage.length; ++i) {
            if (storage[i] > 0 && storage[i] <= k * 2) {
                resultCount++;
            }
        }

        System.out.println(resultCount);
    }
}

// https://contest.yandex.ru/contest/22450/problems/B/