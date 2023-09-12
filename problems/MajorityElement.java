package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MajorityElement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String line= scanner.nextLine().trim();
        String[] lIneArray = line.split(" ");

        int n = lIneArray.length;

        List<Integer> intArray = new ArrayList<>();

        for (String str : lIneArray) {
            intArray.add(Integer.parseInt(str));
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer digit : intArray) {
            if (map.containsKey(digit)) {
                map.put(digit, map.get(digit) + 1 );
            } else {
                map.put(digit,1);
            }
        }

        for (Integer digit : map.keySet()) {
            if (map.get(digit) > n / 2) {
                System.out.println(digit);
                return;
            } else {
                System.out.println(0);
            }
        }
    }
}
