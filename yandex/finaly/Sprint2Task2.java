package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sprint2Task2 {

    public static void main(String[] args) throws IOException {

        Stack<Integer> stack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            while (tokenizer.hasMoreTokens()) {

                String token = tokenizer.nextToken();

                switch (token) {
                    case "+" -> {
                        SimpleEntry<Integer, Integer> pair = getPairNumbers(stack);
                        stack.push(pair.getKey() + pair.getValue());
                    }
                    case "-" -> {
                        SimpleEntry<Integer, Integer> pair = getPairNumbers(stack);
                        stack.push(pair.getKey() - pair.getValue());
                    }
                    case "*" -> {
                        SimpleEntry<Integer, Integer> pair = getPairNumbers(stack);
                        stack.push(pair.getKey() * pair.getValue());
                    }
                    case "/" -> {
                        SimpleEntry<Integer, Integer> pair = getPairNumbers(stack);
                        stack.push(Math.floorDiv(pair.getKey(), pair.getValue()));
                    }
                    default -> stack.push(Integer.parseInt(token));
                }
            }

            System.out.print(stack.pop());
        }
    }

    private static SimpleEntry<Integer, Integer> getPairNumbers(Stack<Integer> stack) {
        int first = stack.pop();
        int second = stack.pop();
        return new SimpleEntry<>(second, first);
    }
}


/*
-- ПРИНЦИП РАБОТЫ --
Читаем строку приходящую на вход из консоли. Через StringTokenizer делим ее через пробелы, после чего читаем каждый элемент
вцелом можно было использовать split и потом проходить по массиву. В цикле пока есть следующий элемент смотрим, что к нам пришло,
если арифметическая операция, то вынимаем из стека два числа, проводим операцию и кладем число назад, если же символ не совпадает не
с одной из арифместиской операцией, то значит это число, парсим строку в int и помещаем на вершину стека.

про деление
необходимо выполнение двух условий:
 - есть ли остаток от деления
 - разного ли у нас знаки делимого и делителя
если условия совпадают, то корректирыем результат деления уменьшая его на 1.

результатом работы будет значение располгаемое в вершине стека.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
сложность получилась O(n), где n это число обработанных token

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
получаается O(n) на строку и O(n) на стек - получается O(n)
 */

// https://contest.yandex.ru/contest/22781/problems/B/
