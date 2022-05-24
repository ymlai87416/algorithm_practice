package ctci.threadslock;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadFizzBuzz {

    public static void main(String[] args){
        Thread t3 = new Thread(new FizzBuzzRunnable(true, false, 100, "Fizz"));
        Thread t5 = new Thread(new FizzBuzzRunnable(false, true, 100, "Buzz"));
        Thread t15 = new Thread(new FizzBuzzRunnable(true, true, 100, "FizzBuzz"));
        Thread tN = new Thread(new FizzBuzzRunnable(false, false, 100, null));

        t3.start();
        t5.start();
        t15.start();
        tN.start();
    }
}

class FizzBuzzRunnable implements Runnable{
    private static AtomicInteger current = new AtomicInteger(1);
    private int max;
    private boolean div3, div5;
    private String toPrint;

    public FizzBuzzRunnable(boolean div3, boolean div5, int max, String toPrint) {
        this.div3 = div3;
        this.div5 = div5;
        this.max = max;
        this.toPrint = toPrint;
    }
    @Override
    public void run() {
        while(true){
            synchronized (current) {
                int c = current.get();
                if(c > max){
                    return;
                }

                if(((c %3 == 0) == div3) && ((c %5 == 0) == div5)) {
                    System.out.println(toPrint == null ? c : toPrint);

                    current.incrementAndGet();
                }
            }
        }
    }
}