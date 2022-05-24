package ctci.sortsearch;

public class SparseSearch {
    public int sparseSearch(String[] arr, String target){
        int low = 0;
        int high = arr.length-1;

        while(low <= high){
            int mid = (low+high)/2;
            int tmid = -1;
            int offset = 0;
            while(tmid == -1){
                if(mid+offset > high && mid-offset < low)
                    break;
                if(mid+offset <= high && arr[mid+offset].compareTo("") != 0)
                    tmid = mid+offset;
                else if(mid-offset >= low && arr[mid-offset].compareTo("") != 0)
                    tmid = mid-offset;
                else
                    offset++;
            }
            if(tmid == -1)
                return -1;

            int compareR = arr[tmid].compareTo(target);
            if(compareR == 0)
                return tmid;
            else if(compareR< 0)
                low = tmid+1;
            else
                high = tmid-1;

        }

        return -1;
    }


    public static void main(String[] args) {
        String[] data = new String[]{"at", "", "", "",  "ball", "", "", "car", "", "", "dad", "", ""};
        SparseSearch test = new SparseSearch();
        System.out.println(test.sparseSearch(data, "at"));
        System.out.println(test.sparseSearch(data, "ball"));
        System.out.println(test.sparseSearch(data, "car"));
        System.out.println(test.sparseSearch(data, "dad"));
        System.out.println(test.sparseSearch(data, "even"));
    }
}
