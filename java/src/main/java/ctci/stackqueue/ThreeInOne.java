package ctci.stackqueue;

class ThreeInOneTest {


    public static void main(String[] args) {

    }
}

class ThreeInOne{
    int[] base = new int[3];
    int[] ptr = new int[3];
    int n;
    int[] arr;

    public ThreeInOne(){
        n = 64;
        arr = new int[n*3];
        for(int i=0; i<3; ++i){
            base[i] = n * i;
            ptr[i]  =base[i];
        }
    }

    public void push(int stackNo, int value){
        if(ptr[stackNo] == base[stackNo+1] ||
                ptr[stackNo] == n)
            expandSize();

        arr[ptr[stackNo]++] = value;
    }

    public int pop(int stackNo) throws Exception{
        if(ptr[stackNo] ==  0 ||
                ptr[stackNo] == base[stackNo])
            throw new Exception("Stack " + stackNo +  " is empty");
        return arr[ptr[stackNo]--];
    }

    public boolean isEmpty(int stackNo){
        return base[stackNo] == ptr[stackNo];
    }

    private void expandSize(){
        n = n * 2;
        int[] newArr = new int[n*3];
        for(int i=0; i<3; ++i){
            for(int j=base[i]; j<ptr[i]; ++j){
                int offset = j-base[i];
                arr[i*n + offset] = arr[j];
            }
        }

        for(int i=0; i<3; ++i){
            int offset = ptr[i] - base[i];
            base[i] = n * i;
            ptr[i]  =base[i] + offset;
        }

    }

}
