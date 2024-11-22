package yandex.contest.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task1 {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        int b = Integer.parseInt(reader.readLine());

        int[][] matrixA = new int[a][b];
        int[][] matrixB = new int[b][a];

        for (int i = 0; i < a; ++i) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < b; ++j) {
                matrixA[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < b; ++j) {
                matrixB[j][i] = matrixA[i][j];
            }
        }

        for (int i = 0; i < b; ++i) {
            for (int j = 0; j < a; ++j) {
                output_buffer.append(matrixB[i][j]).append(" ");
            }
            output_buffer.append("\n");
        }

        // Вывод результата
        System.out.print(output_buffer.toString());
    }
}
