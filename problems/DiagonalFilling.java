package problems;

import java.util.Scanner;

public class DiagonalFilling {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int height = scanner.nextInt();
        int width = scanner.nextInt();

        int[][] nums = new int[height][width];


        nums[0][0] = 1;

        for (int i = 0; i < height; i++) {

            if (i != 0) {
                nums[i][0] = nums[i-1][1] + 1;
            }

            for (int j = 1; j < width; j++) {
                if (i == 0) {
                    nums[i][j] = nums[i][j-1] + j;
                } else {
                    if (i == height - 1 && j == width - 1){
                        nums[i][j] = nums[i][j-1] + 1;
                        break;
                    }
                    nums[i][j] = ((j+1) != (height)) ? nums[i-1][j+1] + 1: nums[i][j-1] + 2;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(" " + nums[i][j] + " ");
            }
            System.out.println();
        }
    }
}
