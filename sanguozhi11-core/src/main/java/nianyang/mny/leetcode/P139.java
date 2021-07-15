package nianyang.mny.leetcode;

import java.util.*;
public class P139 {

    public static void main(String[] args) {
        P139 p=new P139();
        String s="leetcode";
        List<String> list=new ArrayList<>();
        list.add("leet");
        list.add("code");
        boolean b = p.wordBreak(s, list);
        System.out.println(b);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        //i>j
        //状态转移方程  dp[i]=dp[j] && check(s.substring(j,i));
        //字符串
        boolean dp[]=new boolean[s.length()+1];
        dp[0]=true;
        for(int i=1;i<s.length()+1;i++){
            for(int j=0;j<i;j++){
                String temp=s.substring(j,i);
                if(dp[j] && wordDict.contains( temp )){
                    dp[i]=true;
                    break;
                }
            }
        }


        return dp[s.length()];

    }

    public boolean check(String s,List<String> wordDict){
        return wordDict.contains(s);
    }
}
