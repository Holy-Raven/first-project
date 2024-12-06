package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output_buffer = new StringBuilder();

        Map<Integer, List<String>> keyboard = Map.of(
                2, List.of("a", "b", "c"),
                3, List.of("d", "e", "f"),
                4, List.of("g", "h", "i"),
                5, List.of("j", "k", "l"),
                6, List.of("m", "n", "o"),
                7, List.of("p", "q", "r", "s"),
                8, List.of("t", "u", "v"),
                9, List.of("w", "x", "y", "z")
        );

        String line = reader.readLine();
        char[] charArray = line.toCharArray();

        List<Integer> call = new ArrayList<>();
        for (char c : charArray) {
            call.add(Integer.parseInt(c+""));
        }

        List<String> result = new ArrayList<>();

        generate(call, keyboard, 0, new StringBuilder(), result);

        Collections.sort(result);

        for (String combination : result) {
            output_buffer.append(combination).append(" ");
        }

        System.out.println(output_buffer.toString().trim());
    }

    private static void generate(List<Integer> call, Map<Integer, List<String>> keyboard,
                                 int index, StringBuilder builder, List<String> result) {

        if (index == call.size()) {
            result.add(builder.toString());
            return;
        }

        int digit = call.get(index);
        List<String> letters = keyboard.get(digit);

        for (String letter : letters) {
            builder.append(letter);
            generate(call, keyboard, index + 1, builder, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}