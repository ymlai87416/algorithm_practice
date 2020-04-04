package Leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/*
number: 295
url: https://leetcode.com/problems/find-median-from-data-stream/
level: hard
solution: write your AVL tree or red black tree, or just use priority queue.
 */

public class FindMedianFromDataStream {
    public static void main(String[] args){
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }

    static
    class MedianFinder {

        /** initialize your data structure here. */
        int size;
        PriorityQueue<Integer> left;
        PriorityQueue<Integer> right;

        public MedianFinder() {
            size = 0;
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {

            double median = findMedian();
            if(num < median)
                left.add(num);
            else
                right.add(num);

            size++;

            //then I need to rebalance the tree
            if(size % 2 == 0){
                if(left.size() == right.size()) return;
                else{
                    PriorityQueue<Integer> big = null;
                    PriorityQueue<Integer> small = null;
                    if(left.size()> right.size()){
                        big = left;
                        small = right;
                    }
                    else{
                        big = right;
                        small = left;
                    }

                    while(big.size() != small.size()){
                        int a = big.poll();
                        small.offer(a);
                    }
                }
            }
            else{
                while( ! (left.size() > right.size())){
                    int a = right.poll();
                    left.offer(a);
                }
            }
        }

        public double findMedian() {
            if (size == 0)
                return 0;
            else if (size % 2 == 0)
                return (left.peek() + right.peek())/2.0;
            else{
                return left.peek(); //assume we balance the tree
            }
        }
    }

}
