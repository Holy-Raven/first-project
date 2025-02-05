package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task1 {

    private static Integer n;

    private static List<Integer> priceLine;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(line1.nextToken());

            priceLine = new ArrayList<>();
            StringTokenizer line = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                priceLine.add(Integer.parseInt(line.nextToken()));
            }
        }

        System.out.println(calculate());

    }

    private static int calculate() {

        boolean hold = false;
        int currentBye = 0;
        int currentSale = 0;
        int cash = 0;

        for (int i = 0; i < n - 1; i++) {
            if (!hold && priceLine.get(i) < priceLine.get(i + 1)) {
                currentBye = priceLine.get(i);
                hold = true;
            }

            if (hold && (priceLine.get(i) > priceLine.get(i + 1))) {
                currentSale = priceLine.get(i);
                cash = cash + currentSale - currentBye;
                hold = false;
            }

            if (hold && priceLine.get(i) <= priceLine.get(i + 1)) {
                currentSale = priceLine.get(i + 1);
                cash = cash + currentSale - currentBye;
                hold = false;
            }
        }

        return cash;
    }
}
