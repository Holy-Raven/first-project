package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task5 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());

            int n = Integer.parseInt(line1.nextToken());
            Integer k = Integer.parseInt(line1.nextToken());
            int count = 0;

            List<Integer> prices = new ArrayList<>();

            StringTokenizer line2 = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                prices.add(Integer.parseInt(line2.nextToken()));
            }

            prices.sort(Integer::compareTo);

            for (Integer price : prices) {
                if (price <= k) {
                    k = k - price;
                    count++;
                }

            }

            System.out.println(count);
        }
    }
}
