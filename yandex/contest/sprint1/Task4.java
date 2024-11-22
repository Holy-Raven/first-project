package yandex.contest.sprint1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task4 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        List<Integer> array = new ArrayList<>();

        // Чтение температур и запись модулей температур в список
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < a; ++i) {
            array.add((Integer.parseInt(tokenizer.nextToken())));
        }

        int count = 0;

        // Обработка случая с одним днём
        if (a == 1) {
            count = 1;

        } else {
            // Проверка первого дня
            if (array.get(0) > array.get(1)) {
                count++;
            }

            // Проверка промежуточных дней
            for (int i = 1; i < a - 1; ++i) {
                if (array.get(i) > array.get(i - 1) && array.get(i) > array.get(i + 1)) {
                    count++;
                }
            }

            // Проверка последнего дня
            if (array.get(a - 1) > array.get(a - 2)) {
                count++;
            }
        }

        // Вывод количества хаотичных дней
        System.out.println(count);
    }
}