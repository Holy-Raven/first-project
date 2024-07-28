package problems;

import java.util.*;

public class TSaurus {

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

        // Время завершения для каждого процесса
        int[] finishTime = new int[n];
        Arrays.fill(finishTime, 1); // Изначально каждый процесс занимает 1 секунду

        // Начальные процессы без зависимостей
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Топологическая сортировка
        while (!queue.isEmpty()) {
            int process = queue.poll();
            for (int dependent : dependencies.get(process)) {
                inDegree[dependent]--;
                if (inDegree[dependent] == 0) {
                    queue.add(dependent);
                }
                // Обновляем время завершения зависимого процесса
                finishTime[dependent] = Math.max(finishTime[dependent], finishTime[process] + 1);
            }
        }

        // Процесс 1 (с номером 1) должен завершиться последним
        System.out.println(finishTime[0]);
    }
}
