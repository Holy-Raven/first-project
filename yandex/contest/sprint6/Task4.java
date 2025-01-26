package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task4 {

    private static List<String> color;
    private static Map<Integer, List<Integer>> edges;
    private static List<Integer> result;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(line1.nextToken());
            int m = Integer.parseInt(line1.nextToken());

            edges = new HashMap<>();

            for (int i = 0; i < m; i++) {
                StringTokenizer edgeLine = new StringTokenizer(reader.readLine());
                int a = Integer.parseInt(edgeLine.nextToken());
                int b = Integer.parseInt(edgeLine.nextToken());

                edges.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
                edges.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
                }

            // Чтение стартовой вершины
            String lastLine = reader.readLine();
            while (lastLine == null || lastLine.trim().isEmpty()) {
                lastLine = reader.readLine();
            }
            int s = Integer.parseInt(lastLine.trim());

            result = new ArrayList<>();

            initializeColor(n + 1);

            bfs(s);

            // Вывод результатов
            StringBuilder output = new StringBuilder();
            for (int vertex : result) {
                output.append(vertex).append(" ");
            }
            System.out.println(output.toString().trim());
        }
    }

    private static void initializeColor(int numVertices) {
        color = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            color.add("white");
        }
    }

    public static void bfs(int s) {
        // Создадим очередь вершин и положим туда стартовую вершину.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(s);
        color.set(s, "gray");

        while (!planned.isEmpty()) {
            int u = planned.poll();  // Возьмём вершину из очереди.
            result.add(u);           // Добавляем её в результат при посещении.

            // Получаем соседей вершины u, сортируя их по возрастанию.
            List<Integer> neighbors = edges.getOrDefault(u, new ArrayList<>());
            Collections.sort(neighbors); // Убедимся, что соседи отсортированы.

            for (int v : neighbors) {
                if (color.get(v).equals("white")) {
                    // Серые и чёрные вершины уже
                    // либо в очереди, либо обработаны.
                    color.set(v, "gray");
                    planned.add(v);  // Запланируем посещение вершины.
                }
            }
            color.set(u, "black");  // Теперь вершина считается обработанной.+

        }
    }
}
