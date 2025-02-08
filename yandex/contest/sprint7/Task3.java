package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {

    private static int capacity;
    private static List<Pile> piles;

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            capacity = Integer.parseInt(reader.readLine().trim());
            int n = Integer.parseInt(reader.readLine().trim());

            piles = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                int cost = Integer.parseInt(st.nextToken());
                int mass = Integer.parseInt(st.nextToken());
                piles.add(new Pile(cost, mass));
            }

            long totalValue = calculate();
            System.out.println(totalValue);
        }
    }

    private static long calculate() {

        long total = 0;
        int remains = capacity;

        Collections.sort(piles);

        for (Pile pile : piles) {
            if (remains == 0) {
                break;
            }
            if (pile.mass <= remains) {
                total += (long) pile.cost * pile.mass;
                remains -= pile.mass;
            } else {
                total += (long) pile.cost * remains;
                remains = 0;
            }
        }
        return total;
    }

    static class Pile implements Comparable<Pile> {
        int cost;
        int mass;

        Pile(int cost, int mass) {
            this.cost = cost;
            this.mass = mass;
        }

        @Override
        public int compareTo(Pile other) {
            return Integer.compare(other.cost, this.cost);
        }
    }
}
