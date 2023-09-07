package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class JoeBoughtGun {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Количество револьверов и сколько есть денег у Джо");
        String str1 = scanner.nextLine();
        String[] arrayStr1 = str1.split(" ");

        System.out.println("Количество револьверов в магазине");
        int countPistols = Integer.parseInt(arrayStr1[0]);
        System.out.println(countPistols);


        System.out.println("Вот столько есть денег у Джо");
        int money = Integer.parseInt(arrayStr1[1]);
        System.out.println(money);


        System.out.println("Список цен на пистолеты в магазине");
        String str2 = scanner.nextLine();

        List<Integer> priceList = new ArrayList<>();

        String[] arrayStr2 = str2.split(" ");

        for (int i = 0; i < countPistols; i++) {
            priceList.add(Integer.parseInt(arrayStr2[i]));
        }

        Collections.sort(priceList);

        if (money < priceList.get(0)) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i < priceList.size(); i++) {
            if (money < priceList.get(i)) {
                System.out.println(priceList.get(i-1));
                break;
            }
        }
    }
}
