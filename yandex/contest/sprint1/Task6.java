package yandex.contest.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task6 {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();

        String cleaned = str.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        char[] chars = cleaned.toCharArray();

        for (int i = chars.length-1; i >= 0; i--) {
            output_buffer.append(chars[i]);
        }

        String result = output_buffer.toString();

        if (cleaned.equals(result)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}