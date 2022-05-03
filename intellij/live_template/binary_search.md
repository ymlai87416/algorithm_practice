# Binary search

## Finding mid

Wrong: mid = (high + low) / 2
Correct: mid = low + (high-low)/2

Consider case where high=-4 and low=-5, mid will be -4 which is high, so it will keep looping because you cannot reduce high value.

## Finding exact value

Where the exact value must be find
Inclusive version stop at low > high, while exclusive version stop at low == high.

```java
private int iterativeInclusive(){
    int low = 0, high = nums.length-1;
    while(low <= high){
        int mid = low + (high-low)/2;
    
        if(nums[mid] == target){
            return mid;
        }

        if(nums[mid] < target)
            low = mid+1;
        else
            high = mid-1;
    }
    
    return -1;
}

private int iterativeExclusive(){
    int low = 0, high = nums.length;
    while(low < high){
        int mid = low + (high-low)/2;
    
        if(nums[mid] == target){
            return mid;
        }

        if(nums[mid] < target)
            low = mid+1;
        else
            high = mid;
    }
    
    return -1;
}
```

Refer: [Binary Search](https://leetcode.com/submissions/detail/692206573/)


## Finding floor value or ceiling value

Better use inclusive high version.
For exclusive version, you have to search range says (0, 2),
then it can stucks with (0, 2) forever, because you can only assign end with mid+1, which is the end itself, no progress is made, so I have to handle arrays of length 2 also as base case.

```java
private int findK(long low, long high){
    if(low == high) return (int) low;
    long mid = low+(high-low)/2;  //fuck!! there introduce a bug, consider case -5, -4.
    int cnt = count(mid);
    
    if(cnt < k)
        return findK(mid+1, high);
    else
        return findK(low, mid);
    
}
```

Refer: [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/submissions/)

Refer: [Search Insert Position](https://leetcode.com/problems/search-insert-position/)
