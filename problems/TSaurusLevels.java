package problems;

import java.util.*;

public class TSaurusLevels {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        // Граф зависимостей
        List<List<Integer>> dependencies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dependencies.add(new ArrayList<>());
        }

        // Входные степени вершин (процессов)
        int[] inDegree = new int[n];

        // Чтение зависимостей
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            for (int j = 0; j < a; j++) {
                int dep = scanner.nextInt() - 1;
                dependencies.get(dep).add(i);
                inDegree[i]++;
            }
        }
        scanner.close();

        // Очередь для топологической сортировки
        Queue<Integer> queue = new LinkedList<>();

        // Список уровней
        List<List<Integer>> levels = new ArrayList<>();

        // Инициализируем очередь начальными процессами без зависимостей
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Топологическая сортировка и разделение на уровни
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            // Обрабатываем все процессы на текущем уровне
            for (int i = 0; i < size; i++) {
                int process = queue.poll();
                level.add(process + 1); // Добавляем 1 для перевода в 1-based индекс

                // Уменьшаем степень входа зависимых процессов
                for (int dependent : dependencies.get(process)) {
                    inDegree[dependent]--;
                    if (inDegree[dependent] == 0) {
                        queue.add(dependent);
                    }
                }
            }

            // Сортируем процессы на текущем уровне
            Collections.sort(level);
            // Добавляем текущий уровень в список уровней
            levels.add(level);
        }

        // Выводим количество уровней
        System.out.println(levels.size());

        // Выводим процессы для каждого уровня
        for (List<Integer> level : levels) {
            System.out.print(level.size());
            for (int process : level) {
                System.out.print(" " + process);
            }
            System.out.println();
        }
    }
}
