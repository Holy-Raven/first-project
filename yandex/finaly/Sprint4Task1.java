package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

public class Sprint4Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int count = Integer.parseInt(reader.readLine());

            MyHashMap storage = new MyHashMap(300_007);

            for (int i = 0; i < count; i++) {

                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

                switch (tokenizer.nextToken()) {
                    case "put" ->
                            storage.put(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                    case "get" -> {
                        Integer result = storage.get(Integer.parseInt(tokenizer.nextToken()));
                        if (result == null) {
                            System.out.println("None");
                        } else {
                            System.out.println(result);
                        }
                    }
                    case "delete" -> {
                        Integer removed = storage.remove(Integer.parseInt(tokenizer.nextToken()));
                        if (removed == null) {
                            System.out.println("None");
                        } else {
                            System.out.println(removed);
                        }
                    }
                }
            }
        }
    }
}

class MyHashMap {

    private final int capacity;
    private final LinkedList<SimpleEntry<Integer, Integer>>[] buckets;

    public MyHashMap(int capacity) {

        this.capacity = capacity;
        this.buckets = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(int key, int value) {

        int index = hash(key);

        SimpleEntry<Integer, Integer> entry = findEntry(index, key);
        if (entry != null) {
            entry.setValue(value);
        } else {
            buckets[index].add(new SimpleEntry<>(key, value));
        }
    }

    public Integer get(int key) {

        int index = hash(key);

        SimpleEntry<Integer, Integer> entry = findEntry(index, key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public Integer remove(int key) {

        int index = hash(key);

        SimpleEntry<Integer, Integer> entry = findEntry(index, key);
        if (entry == null) {
            return null;
        }
        buckets[index].remove(entry);
        return entry.getValue();
    }

    private SimpleEntry<Integer, Integer> findEntry(int index, int key) {

        LinkedList<SimpleEntry<Integer, Integer>> bucket = buckets[index];

        for (SimpleEntry<Integer, Integer> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry;
            }
        }
        return null;
    }

    private int hash(int key) {
        return Math.abs(key) % capacity;
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
Для реализации класса MyHashMap, учитывая что нет необходимости в расширении,
за основу взят массив, состоящий из связных списков (LinkedList). Это позволяет
решать коллизии методом цепочки. Связный список будет хранить пару значений
(key, value), в качестве хранилища данных выбран SimpleEntry. Класс MyHashMap,
помимо базовых бизнес-методов get, put, remove, содержит также два дополнительных
метода. Метод hash вычисляет корзину по id сотрудника (наш hash) и выбранному
capacity (взял простое число на порядок большее, чем допустимое количесиво ключей ),
где будет храниться наше значение. Метод findEntry создан в соответствии с принципом
DRY, так как он вызывается во всех базовых бизнес-методах.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Поиск по корзине работает за O(1), а поиск значения внутри самой корзины — за O(n).
Если неудачно выбрать capacity, то класс может выродиться в связный список, и
тогда его временная сложность станет O(n). В среднем же временная сложность — O(1).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Пространственная сложность составляет O(n), где n — общее количество пар (key, value).
*/

//https://contest.yandex.ru/contest/24414/problems/B/


//enum Switcher {
//
//    GET("get"),
//
//    PUT("put"),
//
//    DEL("delete");
//
//    private Switcher(String value) {
//        this.value = value;
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    private final String value;
//}

