package yandex.contest.sprint6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task3 {

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

            String lastLine = reader.readLine();
            while (lastLine == null || lastLine.trim().isEmpty()) {
                lastLine = reader.readLine();
            }
            int s = Integer.parseInt(lastLine.trim());

            for (List<Integer> neighbors : edges.values()) {
                Collections.sort(neighbors);
            }

            initializeColor(n + 1);

            result = new ArrayList<>();

            DFS(s);

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

    static void DFS(int startVertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (color.get(v).equals("white")) {
                color.set(v, "gray");
                result.add(v);
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
