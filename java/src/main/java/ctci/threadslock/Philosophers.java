package ctci.threadslock;

import Leetcode.Contest277.C;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Philosophers {

    public static void main(String[] args){
        int n = 10;
        Philosopher[] pList = new Philosopher[n];
        Chopstick[] chopstickList = new Chopstick[n];

        for(int i=0; i<n; ++i)
            chopstickList[i] = new Chopstick();
        for(int i=0; i<n; ++i)
            pList[i] = new Philosopher(i, chopstickList[i], chopstickList[(i+1)% n]);
        for(int i=0; i<n; ++i)
            new Thread(pList[i]).start();
    }
}

class Philosopher implements Runnable{
    int counter = 0;
    int idx;
    Chopstick leftChopstick;
    Chopstick rightChopstick;
    Random r = new Random();
    Philosopher(int idx, Chopstick leftChopstick, Chopstick rightChopstick){
        this.idx = idx;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    @Override
    public void run() {
        for(int i=0; i<10; ++i){
            while(true){
                if(leftChopstick.pickup()){
                    if(rightChopstick.pickup()){
                        eat();
                        rightChopstick.dropdown();
                        leftChopstick.dropdown();
                        break;
                    }
                    else {
                        leftChopstick.dropdown();
                        try {
                            Thread.sleep(r.nextInt(100));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }

        }
    }

    private void eat(){
        ++counter;
        System.out.println("Philosopher " + idx + ": eat(" + counter + ")" );
    }
}

class Chopstick{
    ReentrantLock lock;
    public Chopstick(){
        lock = new ReentrantLock();
    }

    public boolean pickup(){
        return lock.tryLock();
    }

    public void dropdown(){
        lock.unlock();
    }

}
