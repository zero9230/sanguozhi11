package nianyang.mny.interview.leetcode;

public class P165 {

    public static void main(String[] args) {
        P165 p=new P165();
        String ver1="0.1";
        String ver2="1.1";
        int res = p.compareVersion(ver1, ver2);
        System.out.println(res);
    }

    public int compareVersion(String version1, String version2) {
        String[] v1=version1.split(".");
        String[] v2=version2.split(".");
        //长数
        String[] vs1 = v1.length >= v2.length ? v1:v2;
        //短数
        String[] vs2 = vs1 == v1 ?  v2:v1;
        int index=0;
        int res=0;
        for(int i=0;i<vs2.length;i++){
            int vi1=Integer.parseInt(vs1[i]);
            int vi2=Integer.parseInt(vs2[i]);
            if(vi1>vi2){
                res=1;
                break;
            }else if(vi1<vi2){
                res=-1;
                break;
            }
            index++;
        }
        for(int i=index;i<vs1.length;i++){
            int v=Integer.parseInt(vs1[i]);
            if(v!=0){
                res=1;
                break;
            }
        }
        if(res!=0 ){
            if(vs1==v1){
                return res;
            }else{
                return res*(-1);
            }
        }
        return 0;


    }
}
