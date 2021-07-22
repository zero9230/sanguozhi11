package nianyang.mny.leetcode;

import java.util.ArrayList;
import java.util.List;

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

        double res=1;
        //2^37  2^32 , 2^4,2^1
        //把n拆分成2的多项式表达
        //用一个list装载2的多项式的次数，从2^0,2^1,...,2^x

        //list中的元素指数从低到高
        List<Double> list=new ArrayList<>();

        String nBin=Integer.toBinaryString(n);
        //求得n的二项式的和
        // x^27 = x^(16+8+2+1)

        double base=x;
        for(int i=nBin.length()-1;i>=0;i--){
            double element = base * base;
            int bitFlag=nBin.charAt(i)-'0';
            if(bitFlag==1){
                list.add(element);
            }
        }
        for(Double ele:list){
            res*=ele;
        }

        return res;

    }

}
