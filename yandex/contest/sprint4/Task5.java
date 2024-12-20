package yandex.contest.sprint4;

import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

public class Task5 {

    public static void main(String[] args) throws IOException {

        String s = "abalakhamandra";
        int a = 1000;
        int m = 123_987_123;

        String result = getEqualsHash(s, a, m, 10_000_000_000_000L); // Ограничение на количество попыток
        if (result != null) {
            System.out.println("Найдена строка: " + result);
        } else {
            System.out.println("Не удалось найти строку с таким же хешем за разумное время.");
        }
    }

    public static int process(String s, int a, int m) {

        char[] n = s.toCharArray();
        int result = 0;

        for (char symbol : n) {
            result = Math.floorMod((long) result * a + symbol, m);
        }

        return result;
    }

    public static String getEqualsHash(String str, int a, int m, long maxAttempts) {

        String candidate = "";
        int targetHash = process(str, a, m);
        int candidateHash = -1;
        int attempts = 0;

        while (targetHash != candidateHash) {
            if (attempts >= maxAttempts) {
                return null; // Превышено максимальное количество попыток
            }

            candidate = generateRandomString(20);
            candidateHash = process(candidate, a, m);

            if (attempts % 1000_000 == 0) { // Печатаем только каждые 1000 итераций
                System.out.println("Попытка #" + attempts);
            }

            attempts++;
        }

        return candidate;
    }

    public static String generateRandomString(int length) {
        if (length < 1 || length > 1000) {
            throw new IllegalArgumentException("Длина строки должна быть от 1 до 1000");
        }

        return new Random()
                .ints(length, 'a', 'z' + 1) // Генерируем поток случайных чисел в диапазоне от 'a' до 'z'
                .mapToObj(c -> String.valueOf((char) c)) // Преобразуем в символы
                .collect(Collectors.joining()); // Собираем символы в строку
    }
}
