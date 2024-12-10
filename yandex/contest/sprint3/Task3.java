package yandex.contest.sprint3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Task3 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String lineS = reader.readLine();
        String lineT = reader.readLine();

        char[] arrS = lineS.toCharArray();
        char[] arrT = lineT.toCharArray();

        print(process(arrS, arrT));
    }

    public static boolean process(char[] arrS, char[] arrT) {

        int s = 0;
        int t = 0;

        while (s < arrS.length && t < arrT.length) {
            if (arrS[s] == arrT[t]) {
                s++;
            }
            t++;
        }

        return s == arrS.length;
    }

    public static void print(boolean flag) {

        if (flag) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}