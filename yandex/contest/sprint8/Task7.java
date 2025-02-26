package yandex.contest.sprint8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task7 {

    public static int n;
    public static int m;
    private static int[] arrayList1;
    private static int[] arrayList2;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            n = Integer.parseInt(reader.readLine().trim());
            arrayList1 = new int[n];
            StringTokenizer st1 = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                arrayList1[i] = Integer.parseInt(st1.nextToken());
            }

            m = Integer.parseInt(reader.readLine().trim());
            arrayList2 = new int[m];
            StringTokenizer st2 = new StringTokenizer(reader.readLine());
            for (int i = 0; i < m; i++) {
                arrayList2[i] = Integer.parseInt(st2.nextToken());
            }


            List<Integer> result = calculate();

            StringBuilder builder = new StringBuilder();
            for (int index : result) {
                builder.append(index).append(" ");
            }

            System.out.println(builder.toString().trim());
        }
    }

    private static List<Integer> calculate() {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i <= n - m; i++) {
            int diff = arrayList1[i] - arrayList2[0];
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (arrayList1[i + j] - arrayList2[j] != diff) {
                    match = false;
                    break;
                }
            }
            if (match) {
                positions.add(i + 1);
            }
        }
        return positions;
    }
}
