package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task10 {

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(reader.readLine());

            int[] array = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(tokenizer.nextToken());
            }

            bubbleSort(array);
        }
    }

    private static void bubbleSort(int[] array) {

        int size = array.length;
        boolean swapped;
        boolean printed = false;


        for (int i = 0; i < size - 1; i++) {
            swapped = false;

            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int buffer = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = buffer;
                    swapped = true;
                }
            }

            if (swapped) {
                printArray(array);
                printed = true;
            } else {
                break;
            }
        }

        if (!printed) {
            printArray(array);
        }
    }

    private static void printArray(int[] array) {
        StringBuilder output = new StringBuilder();
        for (int value : array) {
            output.append(value).append(" ");
        }
        System.out.println(output.toString().trim());
    }
}
