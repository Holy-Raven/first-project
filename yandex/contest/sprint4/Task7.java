package yandex.contest.sprint4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task7 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());
            int a = Integer.parseInt(reader.readLine());

            int[] numbers = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(tokenizer.nextToken());
            }

            Set<List<Integer>> res = effectiveSolution(a, numbers);

            System.out.println(res.size());
            for (List<Integer> quad : res) {
                System.out.println(quad.get(0) + " " + quad.get(1) + " " + quad.get(2) + " " + quad.get(3));
            }
        }
    }

    public static Set<List<Integer>> effectiveSolution(int A, int[] numbers) {
        Set<List<Integer>> result = new TreeSet<>((o1, o2) -> {
            for (int i = 0; i < o1.size(); i++) {
                if (!o1.get(i).equals(o2.get(i))) {
                    return o1.get(i) - o2.get(i);
                }
            }
            return 0;
        });

        int n = numbers.length;
        Arrays.sort(numbers);

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long currentSum = (long) numbers[i] + numbers[j] + numbers[left] + numbers[right];

                    if (currentSum == A) {
                        result.add(List.of(numbers[i], numbers[j], numbers[left], numbers[right]));
                        left++;
                        right--;

                    } else if (currentSum < A) {
                        left++;

                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
