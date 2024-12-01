package yandex.contest.sprint2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task8 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int commandCount = Integer.parseInt(reader.readLine());
        int max_n = Integer.parseInt(reader.readLine());

        MyQueueSized queue = new MyQueueSized(max_n);

        for (int i = 0; i < commandCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();

            switch (command) {
                case "push" -> {
                    int value = Integer.parseInt(tokenizer.nextToken());
                    queue.push(value);
                }
                case "pop" -> queue.pop();
                case "peek" -> queue.peek();
                case "size" -> queue.size();
            }
        }
    }
}


class MyQueueSized {

    private Integer[] queue;
    private int head;
    private int tail;
    private int max_n;
    private int size;

    public MyQueueSized(int n) {
        queue = new Integer[n];
        head = 0;
        tail = 0;
        max_n = n;
        size = 0;
    }

    public void push(int x) {
        if (size == max_n) {
            System.out.println("error");
        } else {
            queue[tail] = x;
            tail = (tail + 1) % max_n;
            size++;
        }
    }

    public void pop() {
        if (size == 0) {
            System.out.println("None");
        } else {
            System.out.println(queue[head]);
            Integer x = queue[head];
            queue[head] = null;
            head = (head + 1) % max_n;
            size--;
        }
    }

    public void peek() {
        if (size == 0) {
            System.out.println("None");
        } else {
            System.out.println(queue[head]);
        }
    }

    public void size() {
        System.out.println(size);
    }
}
