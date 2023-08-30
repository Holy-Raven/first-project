package problems;

public class RemoveElement {

    public static void main(String[] args) {

        int[] nums = {3,2,2,3};
        int val = 2;

        removeElement(nums,val);
    }

    public static int removeElement(int[] nums, int val) {


        int [] nums2 = new int[nums.length];
        int k = 0;


        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums2[k] = nums[i];
                k++;
            }
        }

        nums = nums2;

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }

        return k;
    }
}