package nianyang.mny.study.netty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream=new FileInputStream("/Users/nmeng/Documents/test.txt");
        FileOutputStream outputStream=new FileOutputStream("/Users/nmeng/Documents/test4.txt");

        FileChannel fileChannel1 = inputStream.getChannel();
        FileChannel fileChannel2 = outputStream.getChannel();

        fileChannel2.transferFrom(fileChannel1,0,fileChannel1.size());



        fileChannel1.close();
        fileChannel2.close();

        inputStream.close();
        outputStream.close();
    }
}
