package problems;

import java.util.Scanner;

public class Pandemic {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        long infectedCount = calculateInfected(n);
        System.out.println(infectedCount);
    }

    public static long calculateInfected(int n) {

        if (n == 1) {
            return 1;
        } else {
            return 4L * (n - 1);
        }
    }
}