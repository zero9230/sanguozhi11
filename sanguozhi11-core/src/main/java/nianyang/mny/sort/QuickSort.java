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

    @Override
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
        //标兵取在左侧，则右哨兵先行；标兵取在右侧，则左哨兵先行
        int pivot = nums[left];

        int i = left, j = right;
        while (i < j) {
            //哨兵行动
            while (nums[j] >= pivot && i<j) {
                j--;
            }
            while (nums[i] <= pivot && i<j) {
                i++;
            }
            //以标兵为基准划分两段
            swap(nums, i, j);
        }
        //标兵归位，用于分割上面已经分好的区间，后面的递归不要带上归位的标兵
        swap(nums,left,i);

        sort(nums, left, i - 1);
        sort(nums, i + 1, right);

    }



}
