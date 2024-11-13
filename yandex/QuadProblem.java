package yandex;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

public class QuadProblem {

    public static void main(String[] args) throws IOException {

        StringBuilder output_buffer = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        int x = Integer.parseInt(tokenizer.nextToken());

        int y = a * x * x + b * x + c;

        output_buffer.append(y).append("\n");

        System.out.println(output_buffer.toString());
    }
}