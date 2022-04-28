package Leetcode.GoogleMock;



import java.util.*;

/**
 problem: https://leetcode.com/problems/my-calendar-ii/
 level: medium
 solution: panic and give a hard to implement solution, no way.

 #segment_tree #tree_map
 **/

//failed, interview score = 2.63/10
//if it is not that efficient a list should be ok.

class MyCalendarTwo {

    TreeMap<Integer, Integer> intervals = new TreeMap<>();
    public MyCalendarTwo() {
    }

    public boolean book(int start, int end) {
        var entries = intervals.subMap(start, end);
        boolean istriple = false;
        if(!intervals.containsKey(start)){
            Integer prevKey = intervals.lowerKey(start);
            int newVal = prevKey == null ? 0: intervals.get(prevKey);
            intervals.put(start, newVal);
        }

        if(!intervals.containsKey(end)){
            Integer prevKey = intervals.lowerKey(end);
            int newVal = prevKey == null ? 0: intervals.get(prevKey);
            intervals.put(end, newVal);
        }

        //another way to read it wrong, if triple book, no update
        for(var entry: entries.entrySet()){
            int newVal = entry.getValue()+1;
            istriple = istriple || newVal >= 3;
        }

        if(!istriple)
            for(var entry: entries.entrySet()){
                int newVal = entry.getValue()+1;
                entry.setValue(newVal);
            }

        return !istriple;
    }

}

public class PhoneQ3 {
    public static void main(String[] args){
        MyCalendarTwo obj = new MyCalendarTwo();
        /*
        System.out.println(obj.book(10, 20));
        System.out.println(obj.book(50, 60));
        System.out.println(obj.book(10, 40));
        System.out.println(obj.book(5, 15));
        System.out.println(obj.book(5, 10));
        System.out.println(obj.book(25, 55));
        */
        System.out.println(obj.book(26,35));
        System.out.println(obj.book(26,32));
        System.out.println(obj.book(25,32));
        System.out.println(obj.book(18,26));
        System.out.println(obj.book(40,45));
        System.out.println(obj.book(19,26)); //T
        System.out.println(obj.book(48,50));
        System.out.println(obj.book(1,6));
        System.out.println(obj.book(46,50));
        System.out.println(obj.book(11,18));


    }
}
