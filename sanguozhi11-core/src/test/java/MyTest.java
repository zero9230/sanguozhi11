import org.junit.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MyTest {
    @Test
    public void whenNotUseMockAnnotation_thenCorrect() {
        List mockList = Mockito.mock(ArrayList.class);

        mockList.add("one");
        mockList.add("one1");
        assertEquals(0, mockList.size());
        Mockito.verify(mockList).add("one");


        Mockito.when(mockList.size()).thenReturn(100);
        assertEquals(100, mockList.size());
    }

    @Test
    public void test2(){
        System.out.println("EXTERNAL_AGENCY".length());

    }

    @Test
    public void splitGiEiFile() throws IOException {
        String filePath="/Users/nmeng/Documents/personal doc/工作/笔记/job/cmp/GI-EI-diff.txt";
        BufferedReader br=new BufferedReader(new FileReader(filePath));
        String queryString="select * from cm_case_entity_info where (case_tid,entity_id) in (%s) ";
        String tuple ="(%s,'%s')";
        String line=null;
        String out="";
        StringBuilder sb=new StringBuilder();
        int count=0;
        while((line= br.readLine())!=null){
            if(line.startsWith(">")){
                String[] strs = line.substring(1).split("_ACCOUNT_");
                String caseTid=strs[0];
                String entityId=strs[1];
                String tupleString = String.format(tuple, caseTid, entityId);
                sb.append(tupleString).append(",");
                count++;
            }
        }

        System.out.println();

        System.out.println("count="+count);
        System.out.println();
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
            sb.append(" and time_created<1645372800");
            out = String.format(queryString, sb);
        }
        System.out.println(out );

    }

    @Test
    public void splitGiIfFile() throws IOException {
        String filePath="/Users/nmeng/Documents/personal doc/工作/笔记/job/cmp/GI-IF-diff.txt";
        BufferedReader br=new BufferedReader(new FileReader(filePath));
        String queryString="select * from cm_case_entity_info where (case_tid,entity_id) in (%s) ";
        String tuple ="(%s,'%s')";
        String line=null;
        String out="";
        StringBuilder sb=new StringBuilder();
        int count=0;
        while((line= br.readLine())!=null){
            if (line.startsWith(">") && !line.contains("EMPLOYEE")) {
                String[] strs = line.substring(1).split("_ACCOUNT_");
                String caseTid=strs[0];
                String entityId=strs[1];
                String tupleString = String.format(tuple, caseTid, entityId);
                sb.append(tupleString).append(",");
                count++;
            }
        }

        System.out.println();

        System.out.println("count="+count);
        System.out.println();
        if(sb.length()>0){
            sb.deleteCharAt(sb.length()-1);
            sb.append(" and time_created<1645372800");
            out = String.format(queryString, sb);
        }
        System.out.println(out );

    }
}
