package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String str = reader.readLine();

            String[] array = str.split(" ");

            StringBuilder builder = new StringBuilder();

            for (int i = array.length - 1; i >= 0; i--) {
                builder.append(array[i]).append(" ");
            }

            String res = builder.toString().trim();

            System.out.println(res.trim());

        }
    }
}