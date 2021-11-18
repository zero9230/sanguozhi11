package nianyang.mny.study.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream=new FileInputStream("/Users/nmeng/Documents/test.txt");
        FileOutputStream outputStream=new FileOutputStream("/Users/nmeng/Documents/test2.txt");

        FileChannel fileChannel1 = inputStream.getChannel();
        FileChannel fileChannel2 = outputStream.getChannel();

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        while(true){
            byteBuffer.clear();
            int read = fileChannel1.read(byteBuffer);
            if(read==-1){
                break;
            }
            byteBuffer.flip();
            fileChannel2.write(byteBuffer);
        }
        inputStream.close();
        outputStream.close();

    }
}
