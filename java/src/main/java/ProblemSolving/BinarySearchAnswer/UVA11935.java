package ProblemSolving.BinarySearchAnswer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 problem: https://onlinejudge.org/external/119/11935.pdf
 level:
 solution:

 #binarySearch #binarySearchRealNumber

 **/

public class UVA11935 {
    static
    class Event{
        public int loc;
        public EventType type;
        public int val;
    }
    static
    class Car{
        public int leak;
        public int consumption;
        public double tank;
        public double capacity;
        public int currentLoc;
    }

    static
    enum EventType{
        FuelConsumption,
        Leak,
        GasStation,
        Mechanic,
        Goal
    }

    private boolean can(ArrayList<Event> list, double f){
        Car c = new Car();
        c.tank = f;
        c.capacity = f;
        for(int i=0; i<list.size(); ++i){
            Event event = list.get(i);

            //can reach the loc?
            c.tank -= (event.loc - c.currentLoc) / 100.0 * (c.consumption + c.leak);
            if(c.tank < -1e-9)
                return false;
            c.currentLoc = event.loc;

            switch(event.type) {
                case FuelConsumption:
                    c.consumption = event.val;
                    break;
                case Leak:
                    c.leak += 100;
                    break;
                case GasStation:
                    c.tank = c.capacity;
                    break;
                case Mechanic:
                    c.leak = 0;
                    break;
                case Goal:
                    return true;
            }
        }

        return false;
    }

    private double solve(ArrayList<Event> list){
        double EPS = 1e-9; // this value is adjustable; 1e-9 is usually small enough

        double lo = 0.0, hi = 10000.0, mid = 0.0, ans = 0.0;
        while (Math.abs(hi - lo) > EPS) { // when the answer is not found yet
            mid = (lo + hi) / 2.0; // try the middle value
            if (can(list, mid)) { ans = mid; hi = mid; } // save the value, then continue
            else lo = mid;
        }
        return ans;
    }

    private void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            ArrayList<Event> list = new ArrayList<>();
            while(true){
                String line = br.readLine();
                String[] token = line.split(" ");
                Event event = new Event();
                event.loc = Integer.parseInt(token[0]);
                if(token[1].compareTo("Fuel") == 0){
                    event.type = EventType.FuelConsumption;
                    event.val = Integer.parseInt(token[3]);

                    if(event.val == 0) return;
                }
                else if(token[1].compareTo("Goal") == 0){
                    event.type = EventType.Goal;
                }
                else if(token[1].compareTo("Leak") == 0){
                    event.type = EventType.Leak;
                }
                else if(token[1].compareTo("Gas") == 0){
                    event.type = EventType.GasStation;
                }
                else if(token[1].compareTo("Mechanic") == 0){
                    event.type = EventType.Mechanic;
                }

                list.add(event);
                if(event.type == EventType.Goal) {
                    System.out.format("%.3f\n", solve(list));
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        UVA11935 sol = new UVA11935();
        sol.run();
    }
}
