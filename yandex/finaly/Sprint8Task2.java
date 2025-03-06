package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

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
                    char currentChar = baseLine.charAt(j);
                    if (!currentNode.hasChild(currentChar)) {
                        break;
                    }
                    currentNode = currentNode.getChild(currentChar);
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

    // Вставка слова в дерево
    private static void addString(TrieNode root, String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            // Используем addChild, который сам создает узел
            currentNode = currentNode.addChild(ch);
        }
        // Помечаем currentNode как терминальный
        currentNode.isEndOfWord = true;
    }
}

class TrieNode {
    private HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;

    // Возвращает узел
    public TrieNode getChild(char ch) {
        return children.get(ch);
    }
    // Добавляет или возвращает существующий узел
    public TrieNode addChild(char ch) {
        return children.computeIfAbsent(ch, k -> new TrieNode());
    }
    // Проверяет наличие узла
    public boolean hasChild(char ch) {
        return children.containsKey(ch);
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
Использование Trie с HashMap дает нам O(l) для каждой подстроки, где l - длина текущей подстроки, так как операции поиска
и добавления в HashMap выполняются за O(1) в среднем. Общая временная сложность составляет O(n * m), где n - длина строки
baseLine, и m - максимальная длина слова в словаре.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Мы храним Trie, используя HashMap для каждого узла. Пространственная сложность Trie составляет O(m * l), где m - количество
слов в словаре, и l - максимальная длина слова. Дополнительно используем массив dp размером O(n), где n - длина строки
baseLine. Таким образом, общая пространственная сложность составляет O(m * l + n).
*/

//https://contest.yandex.ru/contest/26133/run-report/134538772/