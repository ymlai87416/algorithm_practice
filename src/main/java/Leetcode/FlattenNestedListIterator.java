package Leetcode;

import java.util.*;

public class FlattenNestedListIterator {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};

        //Solution s = new Solution();
        //System.out.println(s.findMin(nums));
    }

    static
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    static
    public class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> t;
        Iterator<NestedInteger> curIterator;
        Integer stage;

        public NestedIterator(List<NestedInteger> nestedList) {
            t = new Stack<>();
            curIterator = nestedList.iterator();
        }

        @Override
        public Integer next() {
            if(stage != null) {
                Integer r = stage;
                stage = null;
                return r;
            }

            if(curIterator == null)
                return null;

            if(curIterator.hasNext()){
                NestedInteger b = curIterator.next();
                //number
                if (b.isInteger()) {
                    return b.getInteger();
                } else {
                    t.push(curIterator);
                    curIterator = b.getList().iterator();
                    return next();
                }
            }
            else{
                //pop it and then
                curIterator = t.size() > 0 ? t.pop() : null;
                return next();
            }
        }

        //what the fuck, java cannot clone an iterator?
        @Override
        public boolean hasNext() {
            if(stage == null) {
                stage = next();
                if (stage == null)
                    return false;
                else{
                    return true;
                }
            }
            else
                return true;
        }
    }
}
