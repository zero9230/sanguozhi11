package nianyang.mny.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author nmeng
 * @Date 2022/3/23 23:21
 **/
public class CharacterIntroSpliter {

    public static void main(String[] args) throws IOException {
        File file = new File("PersonalDoc/个人设计/三国志11资料收集.assets/msg-utf8.txt");

        BufferedReader br=new BufferedReader(new FileReader(file));
        String line;
        while((line= br.readLine())!=null){
            if(!line.matches("\\d*\\.")){
                System.out.println(line);
            }
        }
        


    }
}
