package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sprint3Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            int k = Integer.parseInt(reader.readLine());

            int[] array = new int[n];

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int result = brokenSearch(array, k);
            System.out.println(result);
        }
    }

    public static int brokenSearch(int[] arr, int k) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = (left + right) / 2;

            if (arr[mid] == k) {
                return mid;
            }

            if (arr[left] <= arr[mid]) {
                if (arr[left] <= k && k < arr[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (arr[mid] < k && k <= arr[right - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
        }

        return -1;
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
Для обееспечения скорости работы в O(log n) необходимо использовать метод бинарной сортировки, однако для работы метода
необходимо, чтобы массив был отсортирован, Поскольку сортировка массива занимает O(n log n), а наша задача — выполнить поиск
за O(log n), сортировать массив мы не можем.

Наш массив состоит из двух отсортированных половинок, просто мы не знаем в каком месте они соединены. Доработаем бинарный
поиск следующим образом: будем по прежнему делить массив на двое, и тогда мы будем получать два массива, один отсортированный,
другой нет. Поиск будем проводить только в отсортированной половине. Проверяем отсортирована ли половина, если да, то проверяем
содержит ли она наше число, если да продолжаем поиск, если нет, то делим попалам неотсортированную половину и повторяем.
Продолжаем, пока размер области поиска не будемт 0 или пока элемент не будет найден.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
сложность получилась O(log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(n) где n - длина входящего массива. Дополнительно мы храним только константы, они
занимают O(1) и не могут повлиять на итог.
*/

//https://contest.yandex.ru/contest/23815/problems/A/