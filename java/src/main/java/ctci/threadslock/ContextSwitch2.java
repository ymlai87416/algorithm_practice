package ctci.threadslock;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ContextSwitch2 {

    final static String fp = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/ctci/threadslock/mapped.txt";

    public static void main(String[] args) throws IOException {
        //start channel
        RandomAccessFile rd = new RandomAccessFile(fp, "rw");
        FileChannel fc = rd.getChannel(); MappedByteBuffer mem =
                fc.map(FileChannel.MapMode.READ_WRITE, 0, 1000);

        String message = "whatsup";
        byte[] byteArray = message.getBytes();
        byte[] bytes = new byte[10];

        for(int i=0; i<10; ++i)
            if (i <  byteArray.length)
                bytes[i] =  byteArray[i];
            else
                bytes[i] = 0;

        ByteBuffer bb = ByteBuffer.wrap(bytes, 0, bytes.length);

        fc.write(bb);
        fc.read(bb);
    }
}
