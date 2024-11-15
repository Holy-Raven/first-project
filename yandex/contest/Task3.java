package yandex.contest;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Task3 {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        int[][] matrix = new int[a][b];

        for (int i = 0; i < a; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < b; ++j) {
                matrix[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int x = Integer.parseInt(reader.readLine());
        int y = Integer.parseInt(reader.readLine());

        List<Integer> neighbors = new ArrayList<>();

        if ((x < a && x >= 0) && (y < b && y >= 0)) {
            if (x - 1 >= 0) {
                neighbors.add(matrix[x - 1][y]);
            }
            if (y - 1 >= 0) {
                neighbors.add(matrix[x][y - 1]);
            }
            if (x + 1 < a) {
                neighbors.add(matrix[x + 1][y]);
            }
            if (y + 1 < b) {
                neighbors.add(matrix[x][y + 1]);
            }

            Collections.sort(neighbors);

            for (int neighbor : neighbors) {
                output_buffer.append(neighbor).append(" ");
            }
        }

        System.out.println(output_buffer.toString().trim());
    }
}
