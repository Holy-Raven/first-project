package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task8 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            List<String> numbers = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                numbers.add(tokenizer.nextToken());
            }

            numbers.sort((a, b) -> (b + a).compareTo(a + b));

            if (numbers.get(0).equals("0")) {
                System.out.println("0");
                return;
            }

            StringBuilder result = new StringBuilder();
            for (String number : numbers) {
                result.append(number);
            }

            System.out.println(result);
        }
    }
}
