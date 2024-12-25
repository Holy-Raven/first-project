package yandex.finaly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

import java.util.*;

public class Sprint4Task1 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int documentCount = Integer.parseInt(reader.readLine());

            Map<String, Map<Integer, Integer>> inputData = new LinkedHashMap<>();

            for (int document = 1; document <= documentCount; document++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();

                    inputData.putIfAbsent(word, new HashMap<>());
                    Map<Integer, Integer> insideMap = inputData.get(word);

                    insideMap.put(document, insideMap.getOrDefault(document, 0) + 1);
                }
            }

            int queryCount = Integer.parseInt(reader.readLine());

            for (int query = 1; query <= queryCount; query++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

                Map<Integer, Integer> relevantedMap = new LinkedHashMap<>();
                Set<String> processed = new HashSet<>();

                while (tokenizer.hasMoreTokens()) {
                    String queryWord = tokenizer.nextToken();

                    if (!processed.contains(queryWord) && inputData.containsKey(queryWord)) {
                        processed.add(queryWord);

                        Map<Integer, Integer> insideMap = inputData.get(queryWord);

                        for (Map.Entry<Integer, Integer> entry : insideMap.entrySet()) {
                            int document = entry.getKey();
                            int frequency = entry.getValue();

                            relevantedMap.put(document, relevantedMap.getOrDefault(document, 0) + frequency);
                        }
                    }
                }

                PriorityQueue<Map.Entry<Integer, Integer>> relevantQueue = new PriorityQueue<>(
                        (a, b) -> {
                            if (!a.getValue().equals(b.getValue())) {
                                return a.getValue() - b.getValue();
                            }
                            return b.getKey() - a.getKey();
                        }
                );

                for (Map.Entry<Integer, Integer> entry : relevantedMap.entrySet()) {
                    relevantQueue.offer(entry);
                    if (relevantQueue.size() > 5) {
                        relevantQueue.poll();
                    }
                }

                List<Integer> result = new LinkedList<>();
                while (!relevantQueue.isEmpty()) {
                    result.addFirst(relevantQueue.poll().getKey());
                }

                StringBuilder output = new StringBuilder();
                for (int doc : result) {
                    output.append(doc).append(" ");
                }
                System.out.println(output.toString().trim());
            }
        }
    }
}

/*
-- ПРИНЦИП РАБОТЫ --
    Для загрузки данных используется вложенная структура Map. В первой карте ключом будет уникальное слово, а значением по
этому ключу - еще одна карта, где ключ - номер входящего документа, а значение - количество раз, которое слово встретилось
в этом документе.
    Создадим хранилище, куда будем записывать данные о релевантности документов. В ходе обработки каждого запроса будем
отдельно рассматривать каждое слово. Создаем множество уникальных слов, чтобы каждое слово запроса было обработано только
один раз. Если слово не содержится в карте входных данных, то ничего не делаем. Если содержится, то проверяем, встречается
ли слово в i-ом документе. Если встречается, то заносим информацию об этом в нашу карту релевантности, где ключом будет номер
документа i, а значением - число вхождений слова в документ. Если запись в карте релевантности для данного документа уже
существует, то добавляем к значению частоту текущего слова. Если записи еще нет, то устанавливаем значение, равное частоте
текущего слова. Если слово встречалось сразу в нескольких документах, то в карту релевантности добавятся несколько записей
с ключами, равными номерам этих документов.
    Проходя по всем словам запроса, в карте релевантности будут собраны записи с ключами, равными номерам документов, и
значениями - натуральными числами, показывающими суммарное число вхождений всех слов запроса в каждый из документов.
    Из собранных данных отобираються 5 наиболее релевантных. Для этого используеся PriorityQueue, которая хранит элементы
в порядке от наименее релевантного к наиболее релевантному. Чтобы сформировать список номеров документов в нужном порядке
данные из PriorityQueue переводим в список используя LinkedList и добавляем элементы в начало.
    Выведем в консоль 5 наиболее релевантных документов для каждого запроса. Если документов меньше, выведем только те,
которые есть, исключая документы с релевантностью 0.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Заполнение карты входных данных происходит за O(q * n), где q это число слов в каждом документе, n - число документов
Обработка результатов просходи за O(k * m) где k это число слов в запросе, m - число запросов. Временная сложность по
сохранению данных и изъятию из карт можно пренебречь. Время выбора 5 релевантых документов - это время заполнения очереди O(n)
для каждого запроса m. Общая временная сложность O(q * n + k * m + n * m)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Карта входных данных O(w + d * w), где w - уникальные слова,  d * w - вложенная карта с номерами документов и количеством
вхождений. Карта для каждого запроса с n - элементами O(n). PriorityQueue имеет фиксированный размер 5, и не влияет на итог.
Общая пространственная сложность O(w + d * w) + O(n) или O(w + d * w + n)
*/

//https://contest.yandex.ru/contest/24414/problems/A/
