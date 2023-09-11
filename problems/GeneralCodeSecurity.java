package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GeneralCodeSecurity {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int c = scanner.nextInt();
        int d = scanner.nextInt();

        List<Integer> input = new ArrayList<>();
        while (n > 0) {
            input.add(scanner.nextInt());
            n--;
        }

        List<Integer> result = new ArrayList<>(input);

        List<Integer> inputSort = new ArrayList<>(input);
        Collections.sort(inputSort);

        int value;
        int index;

        for (Integer countHazards : inputSort) {
            if (c > 0) {
                value = countHazards;
                while (value >= 0 && c > 0) {
                    value -= d;
                    c--;
                }
                index = input.indexOf(countHazards);
                result.set(index, value);
            }
        }

        for (int i = 0; i < result.size()-1; i++) {
            System.out.print(result.get(i) + " ");
        }
        System.out.println(result.get(result.size()-1));
    }
}