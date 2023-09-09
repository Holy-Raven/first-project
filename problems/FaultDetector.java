package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FaultDetector {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(scanner.nextInt());
            n--;
        }

        int m = scanner.nextInt();
        int[][] array = new int[m][2];
        for (int i = 0; i <= m - 1; i++) {
            array[i][0] = scanner.nextInt();
            array[i][1] = scanner.nextInt();
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            map.put(i, new ArrayList<>());
            for (int j = array[i][0] - 1; j < array[i][1]; j++) {
                map.get(i).add(list.get(j));
            }
        }

        printBase(list, map, array);

    }

    public static String app(List<Integer> list) {

        if (list.size() <= 1) {
            return "YES";
        }

        for (int j = 0; j < list.size() - 1; j++) {
            if (list.get(j) < list.get(j + 1)) {
            } else {
                for (int k = j; k < list.size() - 1; k++) {
                    if (list.get(k) >= list.get(k + 1) || j + 1 == list.size()) {
                    } else {
                        return "NO";
                    }
                }
            }
        }
        return "YES";
    }

    public static void printBase(List<Integer> list, HashMap<Integer, List<Integer>> map, int[][] array) {

        System.out.println(list);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i][0] + " " + array[i][1]);
        }
        for (Integer integer : map.keySet()) {
            System.out.print(integer + " ");
            System.out.println(map.get(integer));
        }

        for (int i = 0; i < map.size(); i++) {
            System.out.println(app(map.get(i)));
        }
    }
}
