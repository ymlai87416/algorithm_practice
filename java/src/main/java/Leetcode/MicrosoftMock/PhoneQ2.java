package Leetcode.MicrosoftMock;

public class PhoneQ2 {

    class MyCircularQueue {

        int[] buffer = null;
        int frontPtr, rearPtr;
        int size = 0;
        public MyCircularQueue(int k) {
            buffer = new int[k];
            frontPtr = 0; //point to the next one
            rearPtr = 0; //point to unknown
            size = 0;
        }

        public boolean enQueue(int value) {
            if(isFull()) return false;
            ++size;
            buffer[rearPtr] = value;
            rearPtr = (rearPtr + 1)%buffer.length;
            return true;
        }

        public boolean deQueue() {
            if(isEmpty()) return false;
            --size;
            frontPtr = (frontPtr + 1)%buffer.length;
            return true;
        }

        public int Front() {
            if(isEmpty()) return -1;
            return buffer[frontPtr];
        }

        public int Rear() {
            if(isEmpty()) return -1;
            int r = rearPtr - 1;
            if(r < 0) r += buffer.length;
            return buffer[r];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == buffer.length;
        }
    }

    public static void main(String[] args){

    }
}
