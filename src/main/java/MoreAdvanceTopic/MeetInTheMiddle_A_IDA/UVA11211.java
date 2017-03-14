package MoreAdvanceTopic.MeetInTheMiddle_A_IDA;


import java.util.*;

/**
 * Created by Tom on 12/3/2017.
 */
public class UVA11211 {
    public static void main(String[] args){
        int size;
        int[] order, order2;

        int cnt = 0;
        TreeMap<State, Integer> dist_s;
        TreeMap<State, Integer> dist_t;

        Scanner sc = new Scanner(System.in);

        while(true){
            size = sc.nextInt();
            if(size == 0) break;
            cnt++;

            order = new int[size];
            order2 = new int[size];

            for(int i=0; i<size; ++i) {
                order[i] = sc.nextInt();
                order2[i] = order[i];
            }

            Arrays.sort(order2);

            State src = new State(order);
            State dst = new State(order2);

            dist_s = new TreeMap<>();
            dist_t = new TreeMap<>();

            Queue<State> queue_src = new ArrayDeque<>();
            Queue<State> queue_dst = new ArrayDeque<>();

            queue_src.add(src);
            dist_s.put(src, 0);
            queue_dst.add(dst);
            dist_t.put(dst, 0);

            int ans = -1;

            while(!queue_src.isEmpty()){
                State u = queue_src.poll();
                int depth = dist_s.get(u);
                if(u.equals(dst)) {
                    ans = depth;
                    break;
                }

                if(depth >= 2)
                    continue;

                Set<State> nextStates = nextState(u);
                for(Iterator<State> itr = nextStates.iterator(); itr.hasNext(); ) {
                    State s = itr.next();
                    if(!dist_s.containsKey(s)){
                        queue_src.add(s);
                        dist_s.put(s, depth + 1);
                    }
                }
            }

            if(ans == -1){

                while(!queue_dst.isEmpty()){
                    State u = queue_dst.poll();
                    int depth = dist_t.get(u);

                    if(u.equals(src)) break; //no going to happens
                    if(dist_s.containsKey(u)){
                        ans = depth + dist_s.get(u);
                        break;
                    }

                    if(depth >= 2)
                        continue;

                    Set<State> nextStates = nextState(u);

                    for(Iterator<State> itr = nextStates.iterator(); itr.hasNext(); ){
                        State s = itr.next();
                        if(!dist_t.containsKey(s)) {
                            queue_dst.add(s);
                            dist_t.put(s, depth + 1);
                        }
                    }
                }
            }

            if(ans == -1)
                System.out.format("Case %d: %d\n", cnt, 5);
            else
                System.out.format("Case %d: %d\n", cnt, ans);
        }
    }

    static TreeSet<State> nextState(State state){
        TreeSet<State> set = new TreeSet<>();
        List<Integer> tmp = new LinkedList<>();

        for(int i=0; i<state.length; ++i)
            tmp.add(state.order[i]);

        for(int st=0; st<state.length; ++st){
            for(int ed=st+1; ed<=state.length; ++ed){
                LinkedList<Integer> left = new LinkedList<>();
                left.addAll(tmp.subList(0, st));
                left.addAll(tmp.subList(ed, state.length));
                List<Integer> cut = tmp.subList(st, ed);

                for(int ins=0; ins<=left.size(); ++ins){
                    List<Integer> leftClone = (List<Integer>)left.clone();
                    leftClone.addAll(ins, cut);

                    set.add(new State(leftClone, state.length));
                }
            }
        }

        return set;
    }

    static class State implements Comparable<State>{
        int[] order;
        int length;

        public State(int[] input){
            order = input;
            length = input.length;
        }

        public State(List<Integer> input, int len){
            order = new int[len];
            int i=0;
            for(Iterator<Integer> itr = input.iterator(); itr.hasNext();)
                order[i++] = itr.next();;
            length = len;
        }

        public int compareTo(State b){
            for(int i=0; i<order.length; ++i)
                if(order[i] < b.order[i])
                    return -1;
                else if(order[i] > b.order[i])
                    return 1;
            return 0;
        }

        public boolean equals(State b){
            for(int i=0; i<order.length; ++i)
                if(order[i] != b.order[i])
                    return false;
            return true;
        }

        public int hashCode(){
            int result = 0;
            for(int i=0; i<order.length; ++i) result += order.length * Math.pow(length, i);
            return result;
        }

    /*
    public int[] lehamCode(){
        int[] result = new int[length];
        int[] maxRight = new int[length];
        for(int i=0; i<length; ++i)
            result[i] = order[i]-1;
        maxRight[length-1] = -1;
        for(int i=length-2; i>=0; --i)
            maxRight[i] = Math.max(result[i+1], maxRight[i+1]);

        for(int i=0; i<length; ++i){
            if(result[i] < maxRight[i])
                for(int j=i+1; j<length; ++j)
                    if(result[j] == maxRight[i])
                        result[j]-=1;
        }
        return result;
    }

    public int toInt(){
        int[] leham =lehamCode();
        int fact = 1;
        int result = 0;
        for(int i=0; i<length; ++i){
            fact = fact * (i+1);
            result += leham[i] * fact;
        }

        return result;
    }
    */
    }

}

