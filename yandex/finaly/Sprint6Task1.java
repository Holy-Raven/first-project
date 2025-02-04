package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sprint6Task1 {

    // Рёбра, составляющие MST
    private static final List<Edge> maximumSpanningTree = new ArrayList<>();

    // Множества вершин
    private static final Set<Integer> notAdded = new HashSet<>();    // Ещё не добавленных в остов.

    // Приоритетная очередь для доступных рёбер (максимальная очередь по весу)
    private static final PriorityQueue<Edge> maxQueueEdges = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        // Чтение графа из входных данных
        Graph graph = readGraph();

        // Нахождение MST
        try {
            findMST(graph);
            // Вычисление общей суммы весов MST
            int result = 0;
            for (Edge edge : maximumSpanningTree) {
                result += edge.getW();
            }
            System.out.println(result);
        } catch (IllegalStateException e) {
            System.out.println("Oops! I did it again");
        }
    }

    // Функция для чтения графа из входных данных
    private static Graph readGraph() throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(line1.nextToken());    // Количество вершин
            int m = Integer.parseInt(line1.nextToken());    // Количество рёбер

            List<Edge> edgeList = new ArrayList<>();

            for (int i = 0; i < m; i++) {

                StringTokenizer edgeLine = new StringTokenizer(reader.readLine());
                int u = Integer.parseInt(edgeLine.nextToken());
                int v = Integer.parseInt(edgeLine.nextToken());
                int w = Integer.parseInt(edgeLine.nextToken());

                edgeList.add(new Edge(u, v, w));
            }

            return new Graph(n, edgeList);
        }
    }

    // Функция для заполнения приоритетной очереди
    private static void addVertex(int vertex, Graph graph) {
        notAdded.remove(vertex);

        // Добавляем все рёбра, которые инцидентны v, но их конец ещё не в остове.
        for (Edge edge : graph.getAdjacencyMap().get(vertex)) {

            int otherVertex;
            if (edge.getU() == vertex) {
                otherVertex = edge.getV();
            } else {
                otherVertex = edge.getU();
            }

            if (notAdded.contains(otherVertex)) {
                maxQueueEdges.add(edge);
            }
        }
    }

    // Функция для нахождения MST
    private static void findMST(Graph graph) {
        // Инициализация множеств вершин
        notAdded.addAll(graph.getVertices());

        // Берём первую попавшуюся вершину
        int startVertex = graph.getVertices().iterator().next();
        addVertex(startVertex, graph);

        // Основной цикл алгоритма Прима
        while (!maxQueueEdges.isEmpty() && !notAdded.isEmpty()) {
            // Извлекаем ребро с наибольшим весом
            Edge currentEdge = maxQueueEdges.poll();

            // Определяем, какая из вершин ещё не добавлена
            int newVertex;

            if (notAdded.contains(currentEdge.getU())) {
                newVertex = currentEdge.getU();
            } else if (notAdded.contains(currentEdge.getV())) {
                newVertex = currentEdge.getV();
            } else {
                // Если обе вершины уже добавлены, пропускаем ребро
                continue;
            }

            // Добавляем ребро в MST
            maximumSpanningTree.add(currentEdge);

            // Добавляем новую вершину и обновляем доступные рёбра
            addVertex(newVertex, graph);
        }

        // Проверка связности графа
        if (!notAdded.isEmpty()) {
            throw new IllegalStateException("Исходный граф несвязный");
        }
    }

    // Класс для представления графа
    static class Graph {

        private final int vertexCount;
        private final List<Edge> edges;
        private final Map<Integer, List<Edge>> adjacencyMap;

        Graph(int vertexCount, List<Edge> edges) {
            this.vertexCount = vertexCount;
            this.edges = edges;
            this.adjacencyMap = new HashMap<>();

            for (int i = 1; i <= vertexCount; i++) {
                adjacencyMap.put(i, new ArrayList<>());
            }
            for (Edge edge : edges) {
                adjacencyMap.get(edge.getU()).add(edge);
                adjacencyMap.get(edge.getV()).add(edge);
            }
        }

        public Map<Integer, List<Edge>> getAdjacencyMap() {
            return adjacencyMap;
        }

        public Set<Integer> getVertices() {
            Set<Integer> vertices = new HashSet<>();
            for (Edge edge : edges) {
                vertices.add(edge.getU());
                vertices.add(edge.getV());
            }
            // Добавляем все вершины, даже без рёбер
            for (int i = 1; i <= vertexCount; i++) {
                vertices.add(i);
            }
            return vertices;
        }
    }

    // Класс для представления рёбер
    static class Edge implements Comparable<Edge> {
        private final int u;
        private final int v;
        private final int w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(other.w, this.w);
        }

        public int getU() {
            return u;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }
    }
}

/*
Для реализации функционала задачи мы использовали следующие структуры данных и методы. Основными компонентами являются
список рёбер, составляющих максимальное остовное дерево - maximumSpanningTree, множества не добавленных вершин notAdded,
а также приоритетная очередь рёбер - maxQueueEdges.

В методе addVertex вершина удаляется из множества notAdded. Далее метод проходит по всем рёбрам, инцидентным этой вершине,
и добавляет в приоритетную очередь maxQueueEdges те рёбра, которые соединяют её с вершинами из множества notAdded. Это
обеспечивает, что в очереди будут только те рёбра, которые могут привести к добавлению новых рёбер в maximumSpanningTree.

Метод findMST инициализирует множество notAdded, помещая туда все вершины графа. Далее выбирается произвольная стартовая
вершина и для неё вызывается addVertex. В основном цикле, пока в notAdded есть вершины и очередь maxQueueEdges не пуста,
из очереди извлекается ребро с наибольшим весом. Если это ребро ведёт к вершине, которая содержится notAdded, то ребро
добавляется в maximumSpanningTree, а новая вершина удаляется из notAdded (через вызов addVertex). Так цикл продолжается,
пока есть доступные рёбра и не удалены все вершины. При этом не все рёбра графа обязательно попадут в maximumSpanningTree
— только те, которые действительно «протягивают» новые вершины в остов.

В конце, если какие-то вершины остаются в notAdded, значит граф несвязен, и мы выводим «Oops! I did it again».
Иначе вычисляем сумму весов рёбер в maximumSpanningTree и выводим её как результат.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Благодаря приоритетной очереди сложность алгоритма Прима O(m log n), где m — количество рёбер, a n - количество вершин.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность алгоритма составляет O(n + m), где n — количество вершин, а m — количество рёбер. Дополнительно,
множества added и notAdded требуют O(n) памяти. Таким образом, общая пространственная сложность равна O(n + m).
*/

//https://contest.yandex.ru/contest/25070/problems/A/