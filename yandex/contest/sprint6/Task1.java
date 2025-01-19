package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(line1.nextToken());
            int m = Integer.parseInt(line1.nextToken());

            Map<Integer, List<Integer>> edges = new HashMap<>();
            for (int i = 1; i <= m; i++) {
                StringTokenizer line2 = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(line2.nextToken());
                List<Integer> list = edges.getOrDefault(a, new ArrayList<>());
                int b = Integer.parseInt(line2.nextToken());
                list.add(b);
                edges.put(a, list);
            }

            Map<Integer, List<Integer>> peaks = new LinkedHashMap<>();

            for (int i = 1; i <= n; i++) {
                List<Integer> peakSize = edges.getOrDefault(i, new ArrayList<>());
                List<Integer> result = new ArrayList<>();
                result.add(peakSize.size());
                result.addAll(peakSize);
                peaks.put(i, result);
            }

            for (List<Integer> value : peaks.values()) {
                for (Integer i : value) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
    }
}
