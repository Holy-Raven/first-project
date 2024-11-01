package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovingAverage {

    public static List<Double> slidingWindowAverage(List<Integer> requests, int n, int k) {
        List<Double> result = new ArrayList<>();

        // Инициализируем сумму первых K элементов
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += requests.get(i);
        }

        // Добавляем среднее для первого окна
        result.add(currentSum / k);

        // Вычисляем средние значения для остальных окон
        for (int i = 1; i <= n - k; i++) {
            // Обновляем текущую сумму, вычитая уходящий элемент и добавляя новый элемент
            currentSum = currentSum - requests.get(i - 1) + requests.get(i + k - 1);
            result.add(currentSum / k);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество секунд n
        int n = scanner.nextInt();
        List<Integer> requests = new ArrayList<>(n);

        // Считываем количество запросов в каждую секунду
        for (int i = 0; i < n; i++) {
            requests.add(scanner.nextInt());
        }

        // Считываем размер окна k
        int k = scanner.nextInt();

        // Получаем список скользящих средних
        List<Double> averages = slidingWindowAverage(requests, n, k);

        // Выводим результат
        for (double avg : averages) {
            System.out.printf("%.2f ", avg);
        }
    }
}