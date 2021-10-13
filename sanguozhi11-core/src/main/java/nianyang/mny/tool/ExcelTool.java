package nianyang.mny.tool;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.*;
public class ExcelTool {

    /**
     *     解析从sitenotebook中直接复制下来的数据，准备导入到oracle的qa环境中，需要准备两点
     *     1.合并由于换行符（也可能是一行数据过多）而分割的单元格，原内容不丢失
     *     2.单元格的内容若为None，则清除单元格内容
     */
    public static void processSiteNotebooksData(XSSFWorkbook xssfWorkbook) throws Exception {
        String filePath="/Users/nmeng/Documents/";
        String filename="gg-cm-case-entity-info.xlsx";
        List<List<Object>> lists = parseExcel(filePath + filename);
        System.out.println(lists.size());
        System.out.println(lists.get(0));
    }

    /**
     * 解析Excel文件
     *  @param fileFullPath
     *
     */
    public static List<List<Object>> parseExcel(String fileFullPath) throws Exception {
        List list = null;
        Workbook work = null;
        list = new ArrayList<>();
        //创建Excel工作薄
//        work = getWorkbook(in, fileName);
        work= WorkbookFactory.create(new File(fileFullPath));
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }

            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }

                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(cell);
                }
                list.add(li);
            }
        }
        return list;

    }

    public static void main(String[] args) throws Exception {
        processSiteNotebooksData(null);
    }
//    private static Workbook getWorkbook(InputStream in, String fileName) {
//        byte[] bs=new byte[1024];
//
//        while()
//        Workbook workbook;
//        return workbook
//    }

}
