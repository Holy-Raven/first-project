package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint3Task2 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            Intern[] array = new Intern[n];

            for (int i = 0; i < n; i++) {

                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                array[i] = new Intern(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            }

            quicksort(array, 0, array.length - 1);

            for (Intern intern : array) {
                System.out.println(intern.login);
            }
        }
    }

    private static void quicksort(Intern[] array, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivotIndex = partition(array, left, right);
        quicksort(array, left, pivotIndex - 1);
        quicksort(array, pivotIndex + 1, right);
    }

    private static int partition(Intern[] array, int left, int right) {

        Intern pivot = array[right]; // Используем последний элемент как опорный

        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, right);
        return i + 1;
    }

    private static int compare(Intern a, Intern b) {

        if (a.count != b.count) {
            return Integer.compare(b.count, a.count);
        }

        if (a.penalty != b.penalty) {
            return Integer.compare(a.penalty, b.penalty);
        }

        return a.login.compareTo(b.login);
    }

    private static void swap(Intern[] array, int i, int j) {
        Intern temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    static class Intern {
        String login;
        int count;
        int penalty;

        Intern(String login, int count, int penalty) {
            this.login = login;
            this.count = count;
            this.penalty = penalty;
        }
    }
}


/*
-- ПРИНЦИП РАБОТЫ --
Для реализации нашего функционала будем использовать тот же набор основных методов: partition для разделения массива,
quicksort для сортировки. Также нам понадобится метод для сравнения объектов участников (для них мы заведём отдельный класс)
и метод для смены позиций. pivot будем брать крайний элемент в массиве. Метод partition работает таким образом, что мы сперва
в цикле группируем слева объекты меньше, чем pivot, а сразу за ними те, что больше, чем pivot, и сам pivot. После завершения
цикла мы перемещаем pivot в место соединения этих подмассивов. В quicksort рекурсивно запускаем метод на обе половинки
исходного массива. Рекурсия продолжается до тех пор, пока индекс левой границы подмассива не станет равен правой его границе,
то есть подмассив не будет состоять из одного элемента и, следовательно, будет считаться уже отсортированным.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность остаётся O(n log n), в худшем случае может достигать O(n^2), если массив неудачно разделяется.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(n) где n - длина входящего массива. Дополнительно мы храним только константы, они
занимают O(1) и не могут повлиять на итог.
 */

//https://contest.yandex.ru/contest/23815/problems/B/