package Leetcode.AmazonMock;

import java.util.ArrayDeque;
import java.util.Deque;

public class OnlineQ1 {


    static class MovingAverage {

        int[] buffer = null;
        int ptr;
        int sum = 0;
        int cnt = 0;

        public MovingAverage(int size) {
            buffer = new int[size];
            ptr = 0;
            sum = 0;
            cnt = 0;
        }

        public double next(int val) {
            //assume it is full
            sum -= buffer[ptr];
            buffer[ptr] = val;
            sum += val;
            ptr= (ptr+1)% buffer.length;
            cnt = cnt == buffer.length ? cnt: cnt+1;
            return sum/cnt;
        }
    }

    public static void main(String[] args){
        MovingAverage ma = new MovingAverage(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
    }
}
