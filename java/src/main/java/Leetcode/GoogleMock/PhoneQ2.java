package Leetcode.GoogleMock;

import java.util.*;

public class PhoneQ2 {

    public int minAreaRect(int[][] points) {
        HashMap<Integer, List<Integer>> xpt = new HashMap<>();
        HashMap<Integer, List<Integer>> ypt = new HashMap<>();
        HashSet<Integer> pts = new HashSet<>();

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) return o1[1] - o2[1];
                else return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < points.length; i++) {
            int px = points[i][0];
            int py = points[i][1];

            if(!xpt.containsKey(px))
                xpt.put(px, new ArrayList<>());
            xpt.get(px).add(i);

            if(!ypt.containsKey(py))
                ypt.put(py, new ArrayList<>());
            ypt.get(py).add(i);

            pts.add(px * 100000 + py);
        }

        int minArea = Integer.MAX_VALUE;
        //now we sort all the point?
        for (int i = 0; i < points.length; i++) {
            //find a right
            int px = points[i][0];
            int py = points[i][1];

            List<Integer> rights = xpt.get(px);
            int pxp = Collections.binarySearch(rights, i);
            List<Integer> tops = ypt.get(py);
            int pyp = Collections.binarySearch(tops, i);
            for (int j = pxp+1; j < rights.size(); j++) {
                for (int k = pyp+1; k < tops.size(); k++) {

                    int jy = points[rights.get(j)][1];
                    int kx = points[tops.get(k)][0];

                    //System.out.println(px + " " + py + " " + kx + " " + jy);

                    int opp = kx * 100000 + jy;
                    if(pts.contains(opp)){
                        //then we have a rectangle
                        int xd = kx-px;
                        int yd = jy-py;
                        //System.out.println(px + " " + py + " " + kx + " " + jy + " area: " + xd*yd);
                        minArea = Math.min(minArea, xd*yd);
                    }
                }
            }

        }

        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }


    public static void main(String[] args){
        int[][] ii = new int[][]{{1,1},{1,3},{3,1},{3,3},{4,1},{4,3}};
        PhoneQ2 e = new PhoneQ2();
        System.out.println(e.minAreaRect(ii));
    }
}
