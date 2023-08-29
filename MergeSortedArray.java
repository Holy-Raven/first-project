public class MergeSortedArray {

    public static void main(String[] args) {

        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        int m = 3;
        int n = 3;

        merge(nums1,m,nums2,n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        for (int i = m+n-1; i > 0; i--) {
            if (nums1[m-1] >= nums2[n-1]) {
                nums1[i] = nums1[m-1];
                m--;
            } else if (nums1[m-1] < nums2[n-1]) {
                nums1[i] = nums2[n-1];
                n--;
            }
        }

        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
    }
}
