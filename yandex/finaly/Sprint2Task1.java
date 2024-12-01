package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint2Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            int m = Integer.parseInt(reader.readLine());

            Deque deque = new Deque(m);

            for (int i = 0; i < n; i++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                String command = tokenizer.nextToken();

                switch (command) {
                    case "push_back" -> {
                        int value = Integer.parseInt(tokenizer.nextToken());
                        deque.pushBack(value);
                    }
                    case "push_front" -> {
                        int value = Integer.parseInt(tokenizer.nextToken());
                        deque.pushFront(value);
                    }
                    case "pop_back" -> deque.popBack();
                    case "pop_front" -> deque.popFront();
                }
            }
        }
    }
}

class Deque {
    private final Integer[] queue;
    private final int max_n;
    private int head;
    private int tail;
    private int size;

    public Deque(int maxSize) {
        queue = new Integer[maxSize];
        max_n = maxSize;
        head = 0;
        tail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFulL() {
        return size == max_n;
    }

    public void pushBack(int value) {
        if (isFulL()) {
            System.out.println("error");
            return;
        }
        queue[tail] = value;
        tail = (tail + 1) % max_n;
        size++;
    }

    public void pushFront(int value) {
        if (isFulL()) {
            System.out.println("error");
            return;
        }
        head = (head - 1 + max_n) % max_n;
        queue[head] = value;
        size++;
    }

    public void popBack() {
        if (isEmpty()) {
            System.out.println("error");
            return;
        }
        tail = (tail - 1 + max_n) % max_n;
        System.out.println(queue[tail]);
        size--;
    }

    public void popFront() {
        if (isEmpty()) {
            System.out.println("error");
            return;
        }
        System.out.println(queue[head]);
        head = (head + 1) % max_n;
        size--;
    }
}

/*
За основу для решения этой задачи я взял пример из урока о реализации очереди на кольцевом буфере, по сути добавил два метода, которые позволяют работать с двумя сторонами очереди.
Особо нечего рассказать. Когда я выбирал решение, то изначально хотел сделать вариант на двух очередях, где каждая очередь на двух стеках, подумал была бы интересная матрешка, но
потом внимательнее прочитал задание и сделал как сделал.
 */

// https://contest.yandex.ru/contest/22781/problems/A/