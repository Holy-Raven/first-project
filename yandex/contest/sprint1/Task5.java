package yandex.contest.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task5 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int l = Integer.parseInt(reader.readLine());

        String line = reader.readLine();

        if (line.length()!=l) {
            throw new RuntimeException();
        }

        String[] words = line.trim().split(" ");

        String resultWord = "";
        int maxLength = 0;

        for (String word : words) {
            if (word.length() > maxLength) {
                resultWord = word;
                maxLength = word.length();
            }
        }

        System.out.println(resultWord);
        System.out.println(maxLength);
    }
}
