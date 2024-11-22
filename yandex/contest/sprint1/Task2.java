package yandex.contest.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task2 {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int[] numbers = new int[3];

        numbers[0] = Integer.parseInt(tokenizer.nextToken());
        numbers[1] = Integer.parseInt(tokenizer.nextToken());
        numbers[2] = Integer.parseInt(tokenizer.nextToken());

        boolean even = false;
        boolean notEven = false;

        for (int num : numbers) {
            if (num % 2 == 0) {
                even = true;
            } else {
                notEven = true;
            }
        }

        if (even && notEven) {
            output_buffer.append("FAIL");
        } else {
            output_buffer.append("WIN");
        }

        System.out.println(output_buffer);
    }
}