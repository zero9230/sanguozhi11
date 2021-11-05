package nianyang.mny.study.netty;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception{
        File file=new File("/Users/nmeng/Documents/test.txt");
        FileInputStream is=new FileInputStream(file);
        FileChannel fileChannel = is.getChannel();
        ByteBuffer buffer=ByteBuffer.allocate((int) file.length());
        fileChannel.read(buffer);
        //byteBuffer中的字节数据转化为String
        System.out.println(new String(buffer.array()));

        is.close();



    }
}
