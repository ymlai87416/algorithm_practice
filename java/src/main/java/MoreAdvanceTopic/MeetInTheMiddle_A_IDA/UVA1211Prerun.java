package MoreAdvanceTopic.MeetInTheMiddle_A_IDA;

import java.util.*;

import static MoreAdvanceTopic.MeetInTheMiddle_A_IDA.UVA11212.nextState;

/**
 * Created by Tom on 12/3/2017.
 *
 * problem: xxx
 * #UVA #Lv1
 */
public class UVA1211Prerun {
    public static void main(String[] args){
        int[] dst = new int[] {1,2,3,4,5,6,7,8,9};
        //int[] dst = new int[] {1, 2,3};
        TreeMap<UVA11212.State, Integer> visited = new TreeMap<UVA11212.State, Integer>();

        UVA11212.State state = new UVA11212.State(dst);

        Queue<UVA11212.State> queue = new ArrayDeque<>();
        queue.add(state);
        visited.put(state, 0);

        int maxDepth = 0;

        while(!queue.isEmpty()){
            //first move from src
            UVA11212.State u = queue.poll();
            int depth = visited.get(u);
            maxDepth = Math.max(maxDepth, depth);

            Set<UVA11212.State> nextStates = nextState(u);

            for(Iterator<UVA11212.State> itr = nextStates.iterator(); itr.hasNext(); ) {
                UVA11212.State s = itr.next();
                if(!visited.containsKey(s)){
                    //System.out.println("Add: " + s.order[0] + " " + s.order[1] + " " +s.order[2] );
                    queue.add(s);
                    int newDepth= depth+1;
                    visited.put(s, newDepth);
                }
            }
        }

        System.out.println("visited: " + visited.size());

        System.out.println("Maximum depth of the graph is: " + maxDepth);
    }

}

