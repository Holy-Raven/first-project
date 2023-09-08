package problems;

import java.util.HashMap;
import java.util.Scanner;

public class MadeSheriff {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String text = scanner.next();
        String sheriff = "sheriff";
        int result = 0;


        char[] array1 = sheriff.toCharArray();
        HashMap<Character, Integer> map1 = new HashMap<>();

        for (char letter : array1) {
            if (map1.containsKey(letter)) {
                map1.put(letter, map1.get(letter) + 1);
            } else {
                map1.put(letter, 1);
            }
        }

        char[] array2 = text.toCharArray();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for (char letter : array2) {
            if (map2.containsKey(letter)) {
                map2.put(letter, map2.get(letter) + 1);
            } else {
                map2.put(letter, 1);
            }
        }

        for (Character character : map1.keySet()) {
            if (!map2.containsKey(character)) {
                result = 0;
                break;
            }
            if (map2.get(character) / map1.get(character) > 0) {
                result = map2.get(character) / map1.get(character);
            }
        }
        System.out.println(result);
    }
}
