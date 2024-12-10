package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task7 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        int[] array = new int[a];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < a; ++i) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] buffer = new int[3];
        int[] sortArray = new int[a];

        for (int i : array) {
            switch (i) {
                case 0:
                    buffer[0] = buffer[0] + 1;
                    break;
                case 1:
                    buffer[1] = buffer[1] + 1;
                    break;
                case 2:
                    buffer[2] = buffer[2] + 1;
                    break;
            }
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < buffer[i]; j++) {
                sortArray[j] = i;
                builder.append(sortArray[j] + " ");
            }
        }

        System.out.println(builder.toString());
    }
}