package yandex.contest.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task6 {

    public static void main(String[] args) throws IOException {

        BufferedReader output_buffer = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(output_buffer.readLine());
        StackMax6 stack = new StackMax6();

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(output_buffer.readLine());
            String command = tokenizer.nextToken();

            switch (command) {
                case "push" -> {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    stack.push(value);
                }
                case "pop" -> stack.pop();
                case "get_max" -> {
                    Integer max = stack.get_max();
                    if (max != null) {
                        System.out.println(max);
                    }
                }
            }
        }
    }
}

class StackMax6 {
    private List<Integer> items;


    public StackMax6() {
        items = new ArrayList<Integer>();
    }

    public void push(int item) {
        items.add(item);
    }

    public Integer get_max() {
        if (items.isEmpty()) {
            System.out.println("None");
            return null;
        }

        List<Integer> sorted = new ArrayList<>(items);
        Collections.sort(sorted);
        return sorted.get(sorted.size() - 1);
    }

    public Integer pop() {
        if (items.isEmpty()) {
            System.out.println("error");
            return null;
        }
        return items.remove(items.size() - 1);
    }

    public int size() {
        return items.size();
    }
}
