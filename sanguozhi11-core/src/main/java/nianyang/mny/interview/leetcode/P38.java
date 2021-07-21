package nianyang.mny.interview.leetcode;

import java.util.*;

public class P38 {

    public static void main(String[] args) {
        P38 p=new P38();
//        System.out.println(p.parse("1131121"));
        String s = p.countAndSay(5);
        System.out.println(s);

    }

    public String countAndSay(int n) {
        String[] dp=new String[n+1];
        dp[0]="";
        dp[1]="1";
        for(int i=1;i<n;i++){
            dp[i+1]=parse(dp[i]);
        }
        return dp[n];
    }
    public String parse(String s){
        if(s==null || s.length()<=0){
            return "";
        }
        List<Character> list=new ArrayList<>();
        char[] cs=s.toCharArray();
        int index=0;
        int count=0;
        int curNum=cs[0]-'1'+1;
        while(index<cs.length){
            if(curNum==cs[index]-'1'+1){
                count++;
            }else{
                list.add((char)( count-1+'1'));
                list.add((char)(curNum -1 +'1'));
                curNum=cs[index]-'1'+1;
                count=1;
            }
            index++;
        }
        list.add((char)( count-1+'1'));
        list.add((char)(curNum -1 +'1'));
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<list.size();i++){
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
