package ctci.threadslock;

import java.util.concurrent.Semaphore;

public class CallInOrder {
    public static void main(String[] args) throws InterruptedException {
        Foo f = new Foo();
        new Thread(() -> f.third()).start();
        Thread.sleep(1000);
        new Thread(() -> f.second()).start();
        new Thread(() -> f.first()).start();
    }
}

class Foo{
    Semaphore s1;
    Semaphore s2;
    public Foo() {
        try{
            s1 = new Semaphore(1);
            s2 = new Semaphore(1);

            s1.acquire();
            s2.acquire();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public void first() {
        try{
            System.out.println("First");
            s1.release();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void second() {
        try{
            s1.acquire(); //wait until first() release it
            s1.release();
            System.out.println("Second");
            s2.release();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void third() {
        try{
            s2.acquire();
            s2.release();
            System.out.println("Third");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
