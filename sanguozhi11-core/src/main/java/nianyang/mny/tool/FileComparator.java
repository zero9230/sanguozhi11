package nianyang.mny.tool;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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
        File infoFile = new File(baseDir+"scm_case_ref_comm_add_data_20211121.txt");
        File raptFile = new File(baseDir+"CS_COMMENT_CASE_GI_SAR_DD_20211121.txt");

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

        String baseOutputDir="/Users/nmeng/Documents/cmp_text/1121cmp/output/";
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
