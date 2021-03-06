package nianyang.mny.study.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String str="hello world";
        FileOutputStream fileOutputStream=new FileOutputStream("/Users/nmeng/Documents/test.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        fileChannel.write(buffer);
        fileOutputStream.close();

    }
}
