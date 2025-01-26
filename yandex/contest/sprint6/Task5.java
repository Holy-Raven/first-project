package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task5 {

    private static int component_count;

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

            for (List<Integer> neighbors : edges.values()) {
                Collections.sort(neighbors);
            }

            initializeColor(n + 1);

            List<List<Integer>> components = new ArrayList<>();

            for (int v = 1; v <= n; v++) {
                if (color.get(v).equals("white")) {
                    List<Integer> currentComponent = new ArrayList<>();
                    DFS(v, currentComponent);
                    Collections.sort(currentComponent);
                    components.add(currentComponent);
                    component_count++;
                }
            }

            components.sort(Comparator.comparingInt(list -> list.get(0)));

            System.out.println(component_count);

            StringBuilder output = new StringBuilder();
            for (List<Integer> component : components) {
                for (int vertex : component) {
                    output.append(vertex).append(" ");
                }
                output.append("\n");
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

    static void DFS(int startVertex, List<Integer> currentComponent) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (color.get(v).equals("white")) {
                color.set(v, "gray");
                currentComponent.add(v);
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
            }
        }
    }
}
