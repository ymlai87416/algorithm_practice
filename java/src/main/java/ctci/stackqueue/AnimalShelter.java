package ctci.stackqueue;

import java.util.*;

public class AnimalShelter{

    Queue<Slot<Cat>> cats;
    Queue<Slot<Dog>> dogs;
    int time = 0;

    public AnimalShelter(){
        time = 0;
        cats = new ArrayDeque<Slot<Cat>>();
        dogs = new ArrayDeque<Slot<Dog>>();
    }

    public void enqueue(Animal a){
        if(a instanceof Dog){
            Dog dog = (Dog)a;
            dogs.offer(new Slot<Dog>(dog, time++));
        }
        else{
            Cat cat = (Cat)a;
            cats.offer(new Slot<Cat>(cat, time++));
        }
    }
    public Animal dequeueAny() throws Exception{
        if(dogs.isEmpty() && cats.isEmpty())
            throw new Exception("there is no animals");
        else if(dogs.isEmpty())
            return dequeueCat();
        else if(cats.isEmpty())
            return dequeueDog();

        Slot<Dog> d = dogs.peek();
        Slot<Cat> c = cats.peek();
        if(d.timestamp < c.timestamp)
            return dogs.poll().animal;
        else
            return cats.poll().animal;
    }

    public Dog dequeueDog() throws Exception{
        if(dogs.isEmpty()) throw new Exception("there is no dogs");

        Slot<Dog> x = dogs.poll();
        return x.animal;
    }

    public Cat dequeueCat() throws Exception{
        if(cats.isEmpty()) throw new Exception("there is no cats");

        Slot<Cat> x = cats.poll();
        return x.animal;
    }

    public static void main(String[] args) throws Exception {
        AnimalShelter test = new AnimalShelter();
        Cat cat1 = new Cat("cat 1");
        Cat cat2 = new Cat("cat 2");
        Cat cat3 = new Cat("cat 3");
        Dog dog1 = new Dog("dog 1");
        Dog dog2 = new Dog("dog 2");
        Dog dog3 = new Dog("dog 3");

        test.enqueue(cat1);
        test.enqueue(dog1);
        test.enqueue(cat2);
        test.enqueue(cat3);
        test.enqueue(dog2);
        test.enqueue(dog3);

        System.out.println(test.dequeueAny());
        System.out.println(test.dequeueAny());
        System.out.println(test.dequeueAny());

        System.out.println(test.dequeueCat());
        System.out.println(test.dequeueDog());
    }
}

class Animal{
    String name;
    public Animal(String name){
        this.name = name;
    }

    public String toString(){
        return "Name: " + this.name;
    }
}
class Cat extends Animal{
    public Cat(String name){
        super(name);
    }
}

class Dog extends Animal{
    public Dog(String name){
        super(name);
    }
}

class Slot<K>{
    K animal;
    int timestamp;

    public Slot(K animal, int time){
        this.animal = animal;
        this.timestamp = time;
    }
}
