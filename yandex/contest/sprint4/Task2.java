package yandex.contest.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());

            List<Integer> results = new ArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                results.add(Integer.parseInt(tokenizer.nextToken()));
            }

            Map<Integer, Integer> storage = new HashMap<>();
            storage.put(0, -1);

            int balance = 0;
            int max = 0;

            for (int i = 0; i < results.size(); i++) {
                if (results.get(i) == 0) {
                    balance += 1;
                } else {
                    balance -= 1;
                }

                if (storage.containsKey(balance)) {
                    max = Math.max(max, i - storage.get(balance));
                } else {
                    storage.put(balance, i);
                }
            }

            System.out.println(max);
        }
    }
}
