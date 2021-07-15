package nianyang.mny.sort;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @author sikou
 * @date 2021/07/15
 */
public class QuickSort extends AbstractSort{

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();

        int[] nums=new int[]{5,1,2,7,3,9,4};

        for (int num : nums) {
            System.out.print( num +" ");
        }

        System.out.println();
        sort.sort(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }

    }

    public int[] sort(int[] nums) {

        if (nums == null || nums.length <= 1) {
            return nums;
        }

        sort(nums, 0, nums.length - 1);

        return nums;
    }

    private void sort(int[] nums, int left, int right) {

        if(left>right){
            return;
        }
        int pivot = nums[left];

        int i = left, j = right;
        while (i < j) {
            while (nums[j] >= pivot && i<j) {
                j--;
            }
            while (nums[i] <= pivot && i<j) {
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums,left,i);

        sort(nums, left, i - 1);
        sort(nums, i + 1, right);

    }



}
