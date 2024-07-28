package problems;

import java.util.Scanner;

public class SumOfNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        long sum = calculateSum(n);
        System.out.println(sum);
    }

    public static long calculateSum(int n) {

        int start = 100;
        int end = n;

        long count = end - start + 1;
        return count * (start + end) / 2;
    }
}