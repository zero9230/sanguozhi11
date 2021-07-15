package nianyang.mny.sort;

public abstract class AbstractSort {

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;

    }

    public static int[] initChaosArray() {
        return new int[]{5, 1, 2, 7, 3, 9, 4};
    }

}
