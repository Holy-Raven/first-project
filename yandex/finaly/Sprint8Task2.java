package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sprint8Task2 {

    public static void main(String[] args) throws IOException {
        // Инициализация корня Trie
        TrieNode root = new TrieNode();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String baseLine = reader.readLine().trim();
            int n = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < n; i++) {
                addString(root, reader.readLine().trim());
            }

            System.out.println(isSplits(root, baseLine) ? "YES" : "NO");
        }
    }

    private static boolean isSplits(TrieNode root, String baseLine) {
        // Создание массива dp, с начальным условием для пустой строки.
        boolean[] dp = new boolean[baseLine.length() + 1];
        dp[0] = true;

        // Проверки возможности разбиения.
        for (int i = 0; i < baseLine.length(); i++) {
            if (dp[i]) {
                TrieNode currentNode = root;
                for (int j = i; j < baseLine.length(); j++) {
                    int index = baseLine.charAt(j) - 'a';
                    if (currentNode.children[index] == null) {
                        // Перехода нет – прерываем
                        break;
                    }
                    currentNode = currentNode.children[index];
                    // Если это конец слова, обновляем dp
                    if (currentNode.isEndOfWord) {
                        dp[j + 1] = true;
                    }
                }
            }
        }
        // Возвращение результата для последнего символа строки.
        return dp[baseLine.length()];
    }

    //Вставка слова в дерево
    private static void addString(TrieNode root, String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (currentNode.children[index] == null) {
                // Создаем новый узел, если такого пути еще нет
                currentNode.children[index] = new TrieNode();
            }
            // Переходим к следующему узлу
            currentNode = currentNode.children[index];
        }
        // Помечаем currentNode как терминальный
        currentNode.isEndOfWord = true;
    }
}

class TrieNode {

    // Слова состоят только из строчных английских букв
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord = false;

    public TrieNode() {
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
Для решения задачи применяем метод динамического программирования в сочетании с префиксным деревом Trie. Создаём массив
dp размером baseLine.length() + 1. Инициализируем dp[0] как true, поскольку пустую строку можно считать разбитой. Поочередно
проходим по каждой позиции i в строке baseLine. Если dp[i] равно true, начинаем проверять подстроки. Для каждой подстроки
проверяем, соответствует ли она какому-либо слову в Trie, продвигаясь по символам подстроки в дереве. Если достигнут
терминальный узел, то обновляем dp[j + 1] в true, где j - индекс последнего символа в текущей подстроке.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Использование Trie дает нам O(l) для каждой подстроки, где l - длина текущей подстроки. Общая временная сложность
составляет O(n * m), где n - длина строки baseLine, и m - максимальная длина слова в словаре.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храненим наше дерево Trie, которое требует O(m * l), где m - количество слов в словаре, и l - максимальная длина слова.
Дополнительно используем массив dp размером O(n), где n - длина строки baseLine. Таким образом, общая пространственная
сложность составляет O(m * l + n).
*/

//https://contest.yandex.ru/contest/26133/run-report/134338985/