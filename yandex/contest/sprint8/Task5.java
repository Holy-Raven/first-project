package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Task5 {

    public static int n;
    private static String s;
    private static String[] insertions;
    private static int[] positions;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            s = reader.readLine().trim();
            n = Integer.parseInt(reader.readLine().trim());

            insertions = new String[n];
            positions = new int[n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                insertions[i] = st.nextToken();
                positions[i] = Integer.parseInt(st.nextToken());
            }

            String result = calculate();
            System.out.println(result);
        }
    }

    private static String calculate() {
        TreeMap<Integer, String> insertionMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            insertionMap.put(positions[i], insertions[i]);
        }

        StringBuilder builder = new StringBuilder();
        int index = 0;

        for (Map.Entry<Integer, String> entry : insertionMap.entrySet()) {
            while (index < entry.getKey()) {
                builder.append(s.charAt(index));
                index++;
            }
            builder.append(entry.getValue());
        }

        while (index < s.length()) {
            builder.append(s.charAt(index));
            index++;
        }

        return builder.toString();
    }
}
