package ctci.threadslock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class ContextSwitch1 {
    /*
    Here is the plan

    1. P2 blocks awaiting data from P1
    2. P1 marks the start time.
    3. P1 sends token to P2
    4. P1 attempts to read a response token from P2 This induces a context switch.
    5. P2 is scheduled and receives the token.
    6. P2 sends a response token to P1
    7. P2 attempts read a response token from P1 This induces a context switch.
    8. P1 is scheduled and receives the token.
    9. P1 marks the end time.
     */

    final static String fp = "/Users/yiuminglai/GitProjects/algorithm_practice/java/src/main/java/ctci/threadslock/mapped.txt";

    public static void main(String[] args) throws IOException {
        //start channel
        RandomAccessFile rd = new RandomAccessFile(fp, "rw");
        FileChannel fc = rd.getChannel(); MappedByteBuffer mem =
                fc.map(FileChannel.MapMode.READ_WRITE, 0, 1000);


        String message = "hello";
        byte[] byteArray = message.getBytes();
        String message2 = "bye";
        byte[] byteArray2 = message2.getBytes();

        byte[] bytes = new byte[10];

        for(int i=0; i<10; ++i)
            if (i < byteArray.length )
                bytes[i] =  byteArray[i];
            else
                bytes[i] = 0;

        ByteBuffer bb = ByteBuffer.wrap(bytes, 0, bytes.length);

        long start = System.currentTimeMillis();

        fc.write(bb);
        int noOfBytesRead = fc.read(bb);

        long end = System.currentTimeMillis();

        for(int i=0; i<10; ++i)
            if (i < byteArray2.length)
                bytes[i] =  byteArray2[i];
            else
                bytes[i] = 0;

        System.out.println("Time taken: " + (end-start) );
    }

}
