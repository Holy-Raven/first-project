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

                List<Map.Entry<Integer, Integer>> sortedResult = new ArrayList<>(relevantedMap.entrySet());
                sortedResult.sort(Comparator.comparingInt(Map.Entry::getKey));
                sortedResult.sort((entry1, entry2) -> entry2.getValue() - entry1.getValue());

                for (int i = 0; i < Math.min(5, sortedResult.size()); i++) {
                    System.out.print(sortedResult.get(i).getKey() + " ");
                }
                System.out.println();
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
    Собранные данны необходимо отсортировать. Создадим список пар ключ-значение из данных нашей карты релевантности.
Сперва отсортируем по возрастанию номера документа, а после по убыванию релевантности.
    Выведем в консоль 5 наиболее релевантных документов для каждого запроса, если документов меньше, выведем что есть,
исключая те документы релевантность которых = 0.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
    Заполнение карты входных данных происходит за O(q * n), где q это число слов в каждом документе, n - число документов
Обработка результатов просходи за O(k * m) где k это число слов в запросе, m - число запросов.
Временная сложноть по сохранению данных и изъятию из карт можно пренебречь. Время сортировки происходит за O(m * n * log n).
Общая временная сложность O(q * n) + O(k * m) + O(m * n * log n) или O(q * n + k * m + m * n * log n)

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
    Карта входных данных O(w + d * w), где w - уникальные слова, w * d - это наша вложенная карта где ключ номер документа,
а значениек количество вхождений слова в документ
    Карта для каждого запроса с n - элементами O(n). Общая временная сложность O(w + d * w) + O(n) или O(w + d * w + n)
*/

//https://contest.yandex.ru/contest/24414/problems/A/
