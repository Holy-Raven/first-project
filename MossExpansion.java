import java.util.Scanner;

public class MossExpansion {
    public static void main(String[] args) {

        System.out.print("Введите целое число больше 0: ");
        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        int result;

        if (number <= 0) {
            throw new RuntimeException("Введите число больше 0");
        } else if (number == 1) {
            result = 1;
        } else {
            result = (number - 1) * 4;
        }
        System.out.printf("Через %s минут, мхом порастет %s клеток. \n ", number, result );
    }
}