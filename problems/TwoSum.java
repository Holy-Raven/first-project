package problems;

import java.util.Scanner;

public class TwoSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество фишек n
        int n = scanner.nextInt();
        int[] scores = new int[n];

        // Считываем очки на фишках
        for (int i = 0; i < n; i++) {
            scores[i] = scanner.nextInt();
        }

        // Считываем загаданное число k
        int k = scanner.nextInt();

        // Поиск пары фишек
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (scores[i] + scores[j] == k) {
                    // Если найдена пара, выводим результат
                    System.out.printf("%d %d%n", scores[i], scores[j]);
                    return; // Завершаем программу после нахождения пары
                }
            }
        }

        // Если пара не найдена, выводим "None"
        System.out.println("None");
    }
}