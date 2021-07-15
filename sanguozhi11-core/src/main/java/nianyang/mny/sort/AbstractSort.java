package nianyang.mny.sort;

public abstract class AbstractSort {

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

    }

    public static int[] initArrayUnsorted() {
        return new int[]{5, 1, 8, 2, 7, 6, 3, 9, 4};
    }

    public abstract int[] sort(int[] nums);

}
