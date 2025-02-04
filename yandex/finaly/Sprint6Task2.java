package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Sprint6Task2 {

    // Матрица океана
    //   getKey() = true, если клетка земля, false - вода
    //   getValue() = посещена ли клетка
    private static SimpleEntry<Boolean, Boolean>[][] field;

    // Размеры поля
    private static int n;
    private static int m;

    // Список направлений
    private static final List<Point> movements = Arrays.asList(
            new Point(0, 1),
            new Point(0, -1),
            new Point(1, 0),
            new Point(-1, 0)
    );

    public static void main(String[] args) throws IOException {

        // Считываем размеры карты
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(tokenizer.nextToken());
            m = Integer.parseInt(tokenizer.nextToken());

            // Инициализируем поле
            field = new SimpleEntry[n][m];

            // Считываем карту и сразу заполняем поле
            for (int i = 0; i < n; i++) {
                String row = reader.readLine();
                for (int j = 0; j < m; j++) {
                    char c = row.charAt(j);
                    field[i][j] = new SimpleEntry<>(c == '#', false);
                }
            }

            int numberOfIslands = 0;
            int maxSize = 0;

            // Перебираем все клетки поля
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    Point current = new Point(i, j);
                    // Если клетка — земля и ещё не посещена
                    if (isLand(current) && !isVisited(current)) {
                        numberOfIslands++;
                        // Запускаем BFS
                        int size = bfs(current);
                        if (size > maxSize) {
                            maxSize = size;
                        }
                    }
                }
            }

            // Выводим: количество островов и максимальный размер
            System.out.println(numberOfIslands + " " + maxSize);
        }
    }

    // BFS из клетки start, ищет все клетки «острова»
    private static int bfs(Point start) {
        // Создадим очередь клеток для посещения, положим туда стартовую клетку
        Queue<Point> planned = new LinkedList<>();
        setVisited(start); // помечаем как посещённую
        planned.add(start);

        int size = 0;

        while (!planned.isEmpty()) {
            Point current = planned.poll();
            size++;

            // Перебираем соседей, добавляем не посещённые в очередь
            for (Point move : movements) {
                Point neighbor = current.add(move);
                if (inBounds(neighbor) && isLand(neighbor) && !isVisited(neighbor)) {
                    setVisited(neighbor);
                    planned.add(neighbor);
                }
            }
        }

        return size;
    }


    // Проверяет, что клетка в пределах поля
    private static boolean inBounds(Point point) {
        int x = point.getX();
        int y = point.getY();
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    // Является ли клетка землёй
    private static boolean isLand(Point point) {
        return field[point.getX()][point.getY()].getKey();
    }

    // Проверяем, посещена ли клетка
    private static boolean isVisited(Point point) {
        return field[point.getX()][point.getY()].getValue();
    }

    // Помечаем клетку как посещённую
    private static void setVisited(Point point) {
        field[point.getX()][point.getY()].setValue(true);
    }

    // Класс для хранения координат клетки (x, y).
    static class Point {
        private final int x;
        private final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point add(Point other) {
            return new Point(this.x + other.x, this.y + other.y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

/*
Для реализации функционала задачи мы использовали следующие структуры данных и методы. Основным хранилищем является двумерный
массив field, в каждой ячейке которого хранится пара значений Boolean. Первый элемент указывает, является ли клетка землёй,
второй — была ли эта клетка уже посещена. Также мы вводим класс Point с координатами (x, y) и список movements для четырёх
направлений обхода.

Запускаем цикл по всем клеткам. Если клетка земля и еще не посещена, то вызываем метод bfs, который реализует обход в ширину.
Мы помещаем стартовую клетку в очередь, отмечаем её как посещённую, а затем в цикле извлекаем из очереди следующую клетку,
увеличиваем счётчик размера острова и рассматриваем всех соседей клетки. Для каждой соседней клетки проверяем, находится
ли она в границах поля, является ли землёй и не была ли посещена ранее. Подходящие клетки отмечаем как посещённые и
добавляем в очередь, пока не обработаем все клетки острова. По итогу bfs возвращает число клеток, которые занимает
текущий остров. Продолжаем цикл — если обнаруживаем новую не посещённую клетку-землю, снова запускаем bfs, обрабатываем
следующий остров. Общее количество таких запусков даёт число островов, а максимальный из возвращённых размеров становится
ответом на вопрос о самом большом острове.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Несмотря на то, что BFS вызывается столько раз, сколько обнаруживается новых островов, каждая клетка поля посещается не
более одного раза. Поэтому общее время работы алгоритма составляет O(n * m), где n и m — размеры поля.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Храним поле из n * m элементов SimpleEntry<Boolean, Boolean> и очередь, которая в худшем случае может содержать все клетки
(если всё поле — земля). Общая пространственная сложность тоже O(n * m).
*/

//https://contest.yandex.ru/contest/25070/problems/B/