package problems;

import java.util.Scanner;

public class TwoSum2 {

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

        // Используем два указателя для поиска пары
        int left = 0;
        int right = n - 1;

        boolean found = false; // Переменная для отслеживания, найдена ли пара
        while (left < right) {
            int currentSum = tokens[left] + tokens[right];

            // Если сумма равна k, выводим найденную пару
            if (currentSum == k) {
                System.out.println(tokens[left] + " " + tokens[right]);
                found = true; // Устанавливаем флаг, что пара найдена
                break; // Выходим из цикла после нахождения первой пары
            }

            // Если сумма меньше k, сдвигаем левый указатель вправо
            if (currentSum < k) {
                left++;
            } else { // Если сумма больше k, сдвигаем правый указатель влево
                right--;
            }
        }

        // Если подходящей пары не найдена
        if (!found) {
            System.out.println("None");
        }

        scanner.close();
    }
}
