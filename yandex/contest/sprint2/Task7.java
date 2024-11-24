package yandex.contest.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task7 {

    public static void main(String[] args) throws IOException {

        BufferedReader output_buffer = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(output_buffer.readLine());
        StackMax stack = new StackMax();

        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(output_buffer.readLine());
            String command = tokenizer.nextToken();

            switch (command) {
                case "push" -> {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    stack.push(value);
                }
                case "pop" -> stack.pop();
                case "get_max" -> stack.get_max();
                case "top" -> stack.top();
            }
        }
    }
}

class StackMax {

    private final List<Integer> items;
    private final List<Integer> maxStack;

    public StackMax() {
        items = new ArrayList<>();
        maxStack = new ArrayList<>();
    }

    public void push(int item) {
        items.add(item);
        if (maxStack.isEmpty() || item >= maxStack.get(maxStack.size() - 1)) {
            maxStack.add(item);
        }
    }

    public void get_max() {
        if (maxStack.isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println(maxStack.get(maxStack.size() - 1));
        }
    }

    public void pop() {
        if (items.isEmpty()) {
            System.out.println("error");
            return;
        }

        int removed = items.remove(items.size() - 1);

        if (removed == maxStack.get(maxStack.size() - 1)) {
            maxStack.remove(maxStack.size() - 1);
        }
    }

    public void top() {
        if (items.isEmpty()) {
            System.out.println("error");
        } else {
            System.out.println(items.get(items.size() - 1));
        }
    }

    public int size() {
        return items.size();
    }
}
