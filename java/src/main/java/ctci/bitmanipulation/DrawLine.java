package ctci.bitmanipulation;

import java.sql.SQLOutput;

public class DrawLine {
    public void drawLine(byte[] screen, int width, int x1, int x2, int y){
        int height = screen.length / (width / 8);
        int startLine = y * (width/8);

        int startbit = startLine + (x1/8);
        int endbit = startLine + (x2/8);

        if(startbit == endbit){
            int nBitSet = x2-x1;
            int bit = (1 << (nBitSet+1)) - 1;
            int offset = x1 % 8;
            screen[startbit] = (byte)(bit << offset);
        }
        else{

            screen[startbit] = leftBit(8 - x1 % 8);

            for(int i=startbit+1; i<endbit; ++i)
                screen[i] = (byte)0xff;

            screen[endbit] = rightBit(x2 % 8 +1);
        }

    }

    private byte rightBit(int N){
        if(N == 8) return (byte)0xff;
        else return (byte)((1 << N)-1);
    }

    private byte leftBit(int N){
        byte t = rightBit(N);
        while((t & 0x80) == 0)
            t = (byte)(t << 1);
        return t;
    }

    private void drawScreen(byte[] data, int width){
        int h = data.length*8 /width;
        int rw = width/8;

        for(int i=0; i<h; ++i){
            for(int j=i*rw; j<(i+1)*rw; ++j){
                for(int k=0; k<8; ++k){
                    int bit = data[j] & (1 << k);
                    System.out.print(bit > 0? '#': '.');
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DrawLine test = new DrawLine();
        //assume width is 32, and it is a 32x32 screen
        byte[] data = new byte[32*4];
        test.drawLine(data, 32, 6, 8, 1);
        test.drawLine(data, 32, 0, 1, 3);
        test.drawLine(data, 32, 15, 23, 5);
        test.drawLine(data, 32, 6, 27, 7);

        test.drawScreen(data, 32);
    }
}
