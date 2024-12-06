package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task12 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output_buffer = new StringBuilder();

        int a = Integer.parseInt(reader.readLine());

        int[] array = new int[a];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < a; ++i) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int b = Integer.parseInt(reader.readLine());

        int first = binarySearch(array, b, 0, array.length);
        output_buffer.append(first);

        int seconds = binarySearch(array, b * 2, 0, array.length);
        output_buffer.append(" ").append(seconds);

        System.out.print(output_buffer.toString());
    }


    public static int binarySearch(int[] arr, int x, int left, int right) {

        if (right <= left) {
            return -1;
        }

        int mid = (left + right) / 2;
        if (arr[mid] >= x) {
            if (mid == 0 || arr[mid - 1] < x) {
                return mid + 1;
            }
            return binarySearch(arr, x, left, mid);
        } else {
            return binarySearch(arr, x, mid + 1, right);
        }
    }
}
