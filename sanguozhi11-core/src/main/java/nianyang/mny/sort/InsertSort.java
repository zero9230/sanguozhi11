package nianyang.mny.sort;

public class InsertSort extends AbstractSort {

    public static void main(String[] args) {
        InsertSort demo = new InsertSort();
        int[] nums = initChaosArray();
        for (int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();
        int[] sort = demo.sort(nums);
        for (int i : sort) {
            System.out.print(i + " ");
        }
    }


    public int[] sort(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j - 1, j);
                }
            }
        }


        return nums;
    }


}
