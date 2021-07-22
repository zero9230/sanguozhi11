package nianyang.mny.leetcode;

/**
 * @author sikou
 * @date 2021/07/21
 */
public class P50 {

    public static void main(String[] args) {
        P50 p=new P50();
        double x=5.2;
        int n=14;
        double res = p.myPow(x, n);
        System.out.println(res);

    }

    public double myPow(double x, int n) {
        if(x==0 || x ==1){
            return x;
        }
        //类似二分法
        double res=0.0;
        //2^37  2^32 , 2^4,2^1
        //把n拆分成2的多项式表达

        return 0;
    }
}
