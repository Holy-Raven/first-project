package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sprint1Task1 {

    public static void main(String[] args) throws IOException {

        List<Integer> street = new ArrayList<>();
        int numbers = 0;

        List<Integer> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            numbers = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < numbers; ++i) {
                street.add(Integer.parseInt(tokenizer.nextToken()));
            }
        }

        Integer minDistance = null;
        for (int i = 0; i < numbers; ++i) {

            if (street.get(i) == 0) {
                minDistance = 0;
            }

            if (minDistance != null) {
                result.add(minDistance);
                minDistance++;
            } else {
                result.add(null);
            }
        }

        minDistance = null;
        for (int i = numbers - 1; i >= 0; --i) {

            if (street.get(i) == 0) {
                minDistance = 0;
            }

            if (minDistance != null) {
                if (result.get(i) == null || minDistance < result.get(i)) {
                    result.set(i, minDistance);
                }
                minDistance++;
            }
        }

        StringBuilder output = new StringBuilder();
        for (int distance : result) {
            output.append(distance).append(" ");
        }
        System.out.println(output.toString().trim());
    }
}

// https://contest.yandex.ru/contest/22450/problems/A/