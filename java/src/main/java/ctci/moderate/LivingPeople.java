package ctci.moderate;

public class LivingPeople {
    public int livingPeople(int[][] people){
        int[] yearCnt = new int[102];

        for(int i=0; i<people.length; ++i){
            yearCnt[people[i][0]-1900] +=1;
            yearCnt[people[i][1]+1-1900] -=1;

        }
        int result = 0;
        int rYear = -1;
        int runSum = 0;
        for(int i=0; i<101; ++i){
            runSum += yearCnt[i];
            if(runSum > result){
                result = runSum;
                rYear = 1900+i;
            }
        }

        return rYear;
    }

    public static void main(String[] args) {
        LivingPeople test = new LivingPeople();
        int[][] data = new int[][]{
                {1902, 1952}, {1926, 2000}, {1987, 1999}
        };

        System.out.println(test.livingPeople(data));
    }
}
