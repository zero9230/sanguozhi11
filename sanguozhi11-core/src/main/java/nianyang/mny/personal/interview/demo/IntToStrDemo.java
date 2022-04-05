package nianyang.mny.personal.interview.demo;

import java.util.Random;

public class IntToStrDemo {
    public static void main(String[] args) {
        int[][] nums=new int[1280][800];
        Random random=new Random(100);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j]=random.nextInt();
            }
        }

        String[][] strings=new String[1280][800];

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                strings[i][j] = String.valueOf( nums[i][j]);
            }
        }
        long time2 = System.currentTimeMillis();
        System.out.println("conversion takes " +(time2-time1) +" ms");


    }
}
