package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {
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

            int[][] matrix = new int[n][n];

            for (int i = 1; i <= n; i++) {
                List<Integer> peakSize = edges.getOrDefault(i, new ArrayList<>());
                List<Integer> result = new ArrayList<>();
                result.add(peakSize.size());
                result.addAll(peakSize);

                for (int j = 1; j < result.size(); j++) {
                    matrix[i-1][result.get(j) - 1] = 1;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
