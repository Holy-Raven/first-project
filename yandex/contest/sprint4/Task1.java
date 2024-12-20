package yandex.contest.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            Set<String> set = new LinkedHashSet<>();

            for (int i = 0; i < n; i++) {
                String club = reader.readLine();
                set.add(club);
            }

            set.forEach(System.out::println);
        }
    }
}
