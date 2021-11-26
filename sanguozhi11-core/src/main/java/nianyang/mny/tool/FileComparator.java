package nianyang.mny.tool;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用于比对生产机器上的文件
 * 将两个文件中的内容切割后存放在set中，比较两个set中的内容
 */
public class FileComparator {

    public static void main(String[] args) throws IOException {

//        Date date=new Date();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmiss");

        String baseDir="/Users/nmeng/Documents/cmp_text/1121cmp/";

        File infoFile = new File(baseDir+"scm_case_non_customer_20211124.txt");
        File raptFile = new File(baseDir+"CS_NON_CUSTOMER_SCM_0_20211124.txt");
        String baseOutputDir="/Users/nmeng/Documents/cmp_text/1121cmp/output/";

//        fileDiff(infoFile,raptFile,baseOutputDir);
//
        File infoFile2 = new File(baseDir + "scm_case_non_customer_20211125.txt");
        File raptDiffFile = new File(baseDir + "output/rapt_file_CS_NON_CUSTOMER_SCM_0_20211124.txt_2021-11-26.txt");
        checkin(infoFile2, raptDiffFile);

    }

    /**
     * 检查raptDiffFile中的文件内容，在infoFile中是否存在
     * @param infoFile
     * @param raptDiffFile
     */
    public static void checkin(File infoFile,File raptDiffFile)throws IOException{
        //把infoFile的数据读进来
        BufferedReader infoReader=new BufferedReader(new FileReader(infoFile));
        Set<String> set=Sets.newHashSet();
        String line=null;
        while((line=infoReader.readLine())!=null){
            set.add(line);
        }
        //逐步确认raptFile中的文件，在infoFile的set中是否存在
        BufferedReader raptReader=new BufferedReader(new FileReader(raptDiffFile));
        line=null;
        //list用于存放没包含在内的id
        List<String> list = Lists.newArrayList();
        int count=0;
        while((line=raptReader.readLine())!=null){
            count++;
            String data=line.replace(",","");
            //把不包含的显示出来
            if(!set.contains(data)){
                list.add(data);
            }
        }
        //期望为0，说明全都包含
        System.out.println("next day set size="+set.size());
        System.out.println("diff data size="+count);
        System.out.println("uncovered size="+list.size());
        for (String s : list) {
            System.out.println(s);
        }

    }


    /**
     * 计算得出infoFile和raptFile的diff部分，分别输出到文件中
     * @param infoFile
     * @param raptFile
     * @param baseOutputDir
     * @throws IOException
     */
    public static void fileDiff(File infoFile,File raptFile ,String baseOutputDir) throws IOException{
        BufferedReader infoReader = new BufferedReader(new FileReader(infoFile));
        BufferedReader raptReader = new BufferedReader(new FileReader(raptFile));

        Set<String> infoSet = Sets.newHashSet();
        Set<String> raptSet = Sets.newHashSet();

        String line = null;
        while (StringUtils.isNotEmpty(line = infoReader.readLine())) {
            infoSet.add(line);
        }
        while (StringUtils.isNotEmpty(line = raptReader.readLine())) {
            raptSet.add(line);
        }

        LocalDate localDateTime = LocalDate.now();

        File infoFile2 = new File(baseOutputDir+"info_file_" + infoFile.getName() + "_" + localDateTime + ".txt");
        File raptFile2 = new File(baseOutputDir+"rapt_file_" + raptFile.getName() + "_" + localDateTime + ".txt");

        BufferedWriter infoWriter = new BufferedWriter(new FileWriter(infoFile2));
        BufferedWriter raptWriter = new BufferedWriter(new FileWriter(raptFile2));

        Set<String> infoSetDiff = infoSet.stream()
                .filter(s -> !raptSet.contains(s))
                .collect(Collectors.toSet());

        Set<String> raptSetDiff = raptSet.stream()
                .filter(s -> !infoSet.contains(s))
                .collect(Collectors.toSet());

        System.out.println("=====infoSet start<<<<<<");
        for (String s : infoSetDiff) {
            infoWriter.write(s.replace("_",",_,"));
            infoWriter.newLine();
            infoWriter.flush();
//            System.out.println(s);
        }

        System.out.println("=====infoSet start<<<<<");

        System.out.println();
        System.out.println("=====raptSet start>>>>>");

        for (String s : raptSetDiff) {
            raptWriter.write(s.replace("_",",_,"));
            raptWriter.newLine();
            raptWriter.flush();
//            System.out.println(s);
        }

        System.out.println("=====raptSet start>>>>>");

        infoReader.close();
        raptReader.close();
        infoWriter.close();
        raptWriter.close();

    }

}
