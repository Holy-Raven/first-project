package problems;

import java.util.HashSet;
import java.util.Scanner;

public class TwoSum3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Читаем количество фишек
        int n = scanner.nextInt();
        int[] tokens = new int[n];

        // Читаем очки на фишках
        for (int i = 0; i < n; i++) {
            tokens[i] = scanner.nextInt();
        }

        // Читаем загаданное число k
        int k = scanner.nextInt();

        // Создаем множество для хранения уже просмотренных значений
        HashSet<Integer> previous = new HashSet<>();

        // Ищем пару, сумма которой равна k
        for (int A : tokens) {
            int Y = k - A; // Вычисляем необходимое значение

            // Если Y уже есть в множестве, значит, нашли пару
            if (previous.contains(Y)) {
                System.out.println(A + " " + Y);
                scanner.close();
                return; // Завершаем выполнение после нахождения первой пары
            }

            // Добавляем текущее значение A в множество
            previous.add(A);
        }

        // Если подходящей пары не найдена
        System.out.println("None");
        scanner.close();
    }
}
