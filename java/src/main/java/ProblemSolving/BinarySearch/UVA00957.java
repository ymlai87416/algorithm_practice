package ProblemSolving.BinarySearch;

import java.util.Scanner;

public class UVA00957 {

    static int[] pope = new int[100001];
    static int[] year = new int[1000001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextInt()) {
            int y = sc.nextInt();
            int p = sc.nextInt();
            int maxpy = 0;

            for(int i=0; i<1000001; ++i) year[i] = 0;

            for (int i = 0; i < p; ++i) {
                int py = sc.nextInt();
                pope[i] = py;
                for (int j = Math.max(1, py - y + 1); j < py; ++j) {
                    if(year[j] > 0) // I don't needed year which no pope is elected.
                        year[j] += 1;
                }
                year[py] += 1;
                if(maxpy < py) maxpy = py;
            }

            int maxP = 0;
            int firstYear =0 ;
            int lastYear = 0;
            for(int i=1; i<=maxpy; ++i){
                if(year[i] > maxP){
                    firstYear = i;
                    maxP = year[i];
                }
            }

            int start=0;
            int end = p-1;
            int target=firstYear + y - 1;

            while(start <= end){
                int mid = (start+end)/2;
                if(pope[mid] == target){
                    lastYear = target;
                    break;
                }
                else if(pope[mid] < target){
                    start = mid + 1;
                }
                else{
                    end = mid - 1;
                }
            }

            if(start > end) lastYear = pope[end];
            System.out.format("%d %d %d\n", maxP, firstYear, lastYear);
        }
    }
}
