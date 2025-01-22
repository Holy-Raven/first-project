package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task9 {

    private static List<String> color;

    private static Map<Integer, List<Integer>> edges;

    private static Stack<Integer> result;

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

            for (List<Integer> neighbors : edges.values()) {
                Collections.sort(neighbors);
            }

            initializeColor(n + 1);

            result = new Stack<>();

            for (int v = n; v >= 1; v--) {
                if (color.get(v).equals("white")) {
                    DFS(v);
                }
            }

            StringBuilder output = new StringBuilder();

            while (!result.isEmpty()) {
                output.append(result.pop()).append(" ");
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

    static void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (color.get(v).equals("white")) {
                color.set(v, "gray");
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
                result.push(v);
            }
        }
    }
}
