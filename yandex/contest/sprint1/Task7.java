package yandex.contest.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task7 {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int number = Integer.parseInt(tokenizer.nextToken());

        if (number == 0) {
            System.out.println(0);
        }

        while (number > 0) {
            output_buffer.insert(0, number % 2);
            number = number / 2;
        }

        System.out.println(output_buffer.toString());
    }
}
