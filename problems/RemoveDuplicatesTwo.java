package problems;

public class RemoveDuplicatesTwo {

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3,};

        int uniqueValue = removeDuplicates(nums);

        for (int i = 0; i < uniqueValue ; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int removeDuplicates(int[] nums) {

        int k = 2;


        if (nums.length <= 2) {
            k = nums.length;
            return  k;
        }


        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[k-2]) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}
