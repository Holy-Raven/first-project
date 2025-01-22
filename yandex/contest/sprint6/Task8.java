package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task8 {

    private static List<String> color;

    private static int time = 0;
    private static List<Integer> entry;
    private static List<Integer> leave;

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
            }

            int s = 1;

            for (List<Integer> neighbors : edges.values()) {
                Collections.sort(neighbors);
            }

            initializeColor(n + 1);
            entry = initializeTime(n);
            leave = initializeTime(n);

            DFS(s);

            // Выводим результат
            StringBuilder output = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                output.append(entry.get(i)).append(" ").append(leave.get(i)).append("\n");
            }
            System.out.print(output.toString().trim());
        }
    }

    private static void initializeColor(int numVertices) {
        color = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            color.add("white");
        }
    }

    private static List<Integer> initializeTime(int n) {
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            array.add(0);
        }
        return array;
    }

    static void DFS(int startVertex) {

        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {

            int v = stack.pop();

            if (color.get(v).equals("white")) {

                color.set(v, "gray");
                entry.set(v, time);  // Запишем время входа.
                time += 1;  // При входе в вершину время (номер шага) увеличивается.

                stack.push(v);

                List<Integer> outgoingEdges = edges.getOrDefault(v, new ArrayList<>());
                for (int i = outgoingEdges.size() - 1; i >= 0; i--) {
                    int w = outgoingEdges.get(i);
                    if (color.get(w).equals("white")) {
                        stack.push(w);
                    }
                }
            } else if (color.get(v).equals("gray")) {

                color.set(v, "black");
                leave.set(v, time);  // Запишем время входа.
                time += 1;  // При входе в вершину время (номер шага) увеличивается.
            }
        }
    }
}
