package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task7 {

    private static List<Integer> distance;
    private static List<Integer> previous;
    private static List<String> color;
    private static Map<Integer, List<Integer>> edges;

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

            initializeColor(n + 1);
            initializeDistance(n + 1);
            initializePrevious(n + 1);

            bfs(s);

            // Вывод результатов
            System.out.println(maxDistancePath());
        }
    }

    private static void initializeColor(int numVertices) {
        color = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            color.add("white");
        }
    }

    private static void initializeDistance(int numVertices) {
        distance = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            distance.add(i);
        }
    }

    private static void initializePrevious(int numVertices) {
        previous = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            previous.add(i);
        }
    }

    public static void bfs(int s) {
        // Создадим очередь вершин и положим туда стартовую вершину.
        Queue<Integer> planned = new LinkedList<>();
        planned.add(s);
        color.set(s, "gray");
        distance.set(s, 0); // Устанавливаем расстояние до стартовой вершины как 0

        while (!planned.isEmpty()) {
            int u = planned.poll();  // Возьмём вершину из очереди.

            // Получаем соседей вершины u, сортируя их по возрастанию.
            List<Integer> neighbors = edges.getOrDefault(u, new ArrayList<>());
            Collections.sort(neighbors); // Убедимся, что соседи отсортированы.

            for (int v : neighbors) {
                if (color.get(v).equals("white")) {
                    distance.set(v, distance.get(u) + 1);
                    previous.set(v, u);
                    color.set(v, "gray");
                    planned.add(v);  // Запланируем посещение вершины.
                }
            }
            color.set(u, "black");  // Теперь вершина считается обработанной.+

        }
    }

    public static int maxDistancePath() {
        int maxDist = 0;
        for(int i = 1; i < distance.size(); i++) {
            if(distance.get(i) > maxDist){
                maxDist = distance.get(i);
            }
        }
        return maxDist;
    }
}
