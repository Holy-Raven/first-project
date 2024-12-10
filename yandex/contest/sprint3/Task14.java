package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task14 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        // Чтение отрезков
        List<SimpleEntry<Integer, Integer>> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            intervals.add(new SimpleEntry<>(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        // Сортировка отрезков
        intervals.sort((a, b) -> {
            if (!a.getKey().equals(b.getKey())) {
                return Integer.compare(a.getKey(), b.getKey());
            }
            return Integer.compare(a.getValue(), b.getValue());
        });

        // Слияние отрезков
        List<SimpleEntry<Integer, Integer>> result = new ArrayList<>();

        SimpleEntry<Integer, Integer> current = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            SimpleEntry<Integer, Integer> next = intervals.get(i);

            // Проверка пересечения
            if (current.getValue() >= next.getKey()) {
                current.setValue(Math.max(current.getValue(), next.getValue()));
            } else {
                // Добавляем текущий отрезок в результат
                result.add(current);
                current = next; // Обновляем текущий отрезок
            }
        }
        // Добавляем последний отрезок
        result.add(current);

        // Вывод результата
        StringBuilder output = new StringBuilder();
        for (SimpleEntry<Integer, Integer> interval : result) {
            output.append(interval.getKey()).append(" ").append(interval.getValue()).append("\n");
        }
        System.out.print(output);
    }
}
