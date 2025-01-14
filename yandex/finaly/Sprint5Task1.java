package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint5Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            Intern[] array = new Intern[n + 1];

            for (int i = 1; i <= n; i++) {

                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                array[i] = new Intern(
                        tokenizer.nextToken(),
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken()));
            }

            heapSort(array);

            for (int i = 1; i <= n; i++) {
                System.out.println(array[i].login);
            }
        }
    }

    private static void heapSort(Intern[] array) {

        int heapSize = array.length - 1;

        for (int i = 1; i <= heapSize; i++) {
            heapAdd(array, i);
        }

        for (int i = heapSize; i > 0; i--) {
            Intern max = popMax(array, i);
            array[i] = max;
        }
    }

    public static void siftUp(Intern[] heap, int idx) {

        if (idx == 1) {
            return;
        }

        int parentIdx = idx / 2;

        if (heap[parentIdx].compareTo(heap[idx]) < 0) {
            Intern temp = heap[parentIdx];
            heap[parentIdx] = heap[idx];
            heap[idx] = temp;

            siftUp(heap, parentIdx);
        }
    }

    public static void siftDown(Intern[] heap, int idx, int heapSize) {

        int left = 2 * idx;
        int right = 2 * idx + 1;

        if (left > heapSize) {
            return;
        }

        int indexLargest = left;
        if (right <= heapSize && heap[right].compareTo(heap[left]) > 0) {
            indexLargest = right;
        }

        if (heap[indexLargest].compareTo(heap[idx]) > 0) {
            Intern temp = heap[idx];
            heap[idx] = heap[indexLargest];
            heap[indexLargest] = temp;

            siftDown(heap, indexLargest, heapSize);
        }
    }

    public static void heapAdd(Intern[] array, int idx) {
        siftUp(array, idx);
    }

    public static Intern popMax(Intern[] heap, int heapSize) {

        Intern result = heap[1];
        heap[1] = heap[heapSize];
        siftDown(heap, 1, heapSize - 1);

        return result;
    }

    static class Intern implements Comparable<Intern> {
        private final String login;
        private final int count;
        private final int penalty;

        Intern(String login, int count, int penalty) {
            this.login = login;
            this.count = count;
            this.penalty = penalty;
        }

        @Override
        public int compareTo(Intern other) {

            if (this.count != other.count) {
                return Integer.compare(other.count, this.count);
            }

            if (this.penalty != other.penalty) {
                return Integer.compare(this.penalty, other.penalty);
            }

            return this.login.compareTo(other.login);
        }
    }
}

/*
Для реализации функционала задачи мы использовали построенные ранее методы siftUp и siftDown, правда в методе siftDown,
чтобы работать с фактическим размером кучи, добавлен ещё один параметр. Для добавления и удаления элементов из кучи мы
также использовали методы из учебного материала. heapAdd в данном случае служил обёрткой для siftUp, так как массив заранее
известного размера, но решил добавить его для более полной картины. Учитывая, что в куче индексация начинается с 1, а не
с 0, как в массиве, то на уровне ввода данных первый элемент у нас фиктивный. Ввод данных, класс Intern и метод compare
мы использовали из задачи третьего спринта.

Сортировка: сперва мы восстанавливаем кучу при помощи метода heapAdd — элементы добавляются последовательно, каждый новый
поднимается наверх, пока куча не станет корректной. После этого самый меньший элемент (корень кучи) перемещается в конец
массива и исключается из обрабатываемой кучи, уменьшая «размер кучи» (для этого вводим новый аргумент в siftDown и popMax).
Свойства кучи восстанавливаются с помощью siftDown.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Восстановление кучи занимает время O(n log n). Извлечение элементов из кучи так же O(n log n). Итого получаем общую временную
сложность O(n log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Помимо самих данных которые занимают O(n), нам так же приходиться хранить временные данные, занимающие O(1) дополнительной
памяти. siftUp и siftDown используют рекурсию, глубина такой рекурсии не должнав превышать высоту кучи - O(log n).
Итого получаем общую пространственную сложность O(n) + O(log n) = O(n)
*/

//https://contest.yandex.ru/contest/24810/problems/A/