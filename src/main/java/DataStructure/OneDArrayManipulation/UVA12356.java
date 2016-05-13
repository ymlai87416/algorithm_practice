package DataStructure.OneDArrayManipulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Tom on 17/4/2016.
 */
public class UVA12356 {

    static class Pair{
        int a, b;
        public Pair(int a, int b){
            this.a = a; this.b = b;
        }
    }
    static class ArrayLinkedList{
        int head;
        int tail;
        int[] left;
        int[] right;

        public ArrayLinkedList(int size){
            left =new int[size+2];
            right= new int[size+2];
            head =0;
            tail = size+1;
        }

        public void init(int size){
            left[head] = -1;
            int pos = head;
            for(int i=1; i<=size; ++i){
                right[pos] = i;
                left[i] = pos;
                pos=i;
            }
            right[size] = tail;
            left[tail] = pos;
            right[tail] = -1;
        }

        public Pair removeRange(int a, int b){
            int leftpos = left[a];
            int rightpos = right[b];
            right[leftpos] = rightpos;
            left[rightpos] = leftpos;

            return new Pair(leftpos, rightpos);
        }
    }

    public static void main(String[] args){
        //Scanner sc = new Scanner(System.in);
        BufferedReader sc = new BufferedReader( new InputStreamReader(System.in) );
        while(true){

            String line = null;
            try {
                line = sc.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int s = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(s == 0 && b == 0) break;

                ArrayLinkedList sr = new ArrayLinkedList(s);
                sr.init(s);
                for(int i=0; i<b; ++i){
                    line = sc.readLine();
                    st = new StringTokenizer(line);
                    int l = Integer.parseInt(st.nextToken());
                    int r = Integer.parseInt(st.nextToken());
                    Pair rr = sr.removeRange(l, r);
                    System.out.format("%s %s\n", rr.a == 0 ? '*' : String.valueOf(rr.a), rr.b == s+1 ? '*' : String.valueOf(rr.b));
                }
                System.out.println("-");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
