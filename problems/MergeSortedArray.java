package problems;

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

//        int i=m-1;
//        int j=n-1;
//        int k=nums1.length-1;
//
//        while(j>=0) {
//            if(i>=0 && nums1[i]>nums2[j]) {
//                nums1[k]=nums1[i];
//                k--;
//                i--;
//            } else {
//                nums1[k] = nums2[j];
//                k--;
//                j--;
//            }
//        }
//
//        for (int u = 0; u < nums1.length; u++) {
//            System.out.print(nums1[u] + " ");
//        }
//    }
//}