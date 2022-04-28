# maths

## Fibonacci

There are close-form and also matrix form
Matrix multiplication is associative

Time complexity: O(log n)

Shortcut: fibonacci.close, fibonacci.matrix


```java
int fib(int n){
    int F[][] = new int[][]{{1,1},{1,0}};
    if (n == 0)
        return 0;
    power(F, n-1);
        
    return F[0][0];
}
    
void multiply(int F[][], int M[][])
{
    int x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
    int y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
    int z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
    int w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];
        
    F[0][0] = x;
    F[0][1] = y;
    F[1][0] = z;
    F[1][1] = w;
}
    
/* Optimized version of power() in method 4 */
void power(int F[][], int n)
{
    if( n == 0 || n == 1)
        return;
    int M[][] = new int[][]{{1,1},{1,0}};
        
    power(F, n/2);
    multiply(F, F);
        
    if (n%2 != 0)
        multiply(F, M);
}
```

## Catalan

Cat(N) can calculate the following:
- distinct binary tree of node N
- way to parathesis
- way to triangluarize convex polygon
- monotonic path in square grid

```java

```

## Factorial

For long, it can hold maximum of 20!

```java
long fact(int n)
{
    long res = 1;
    for (int i = 2; i <= n; i++)
        res = res * i;
    return res;
}
```

## Combinatorics

* Each row in pascal triangle is power of 2.

### nCr / Binomial

Shortcut: math.ncr

```java
//This implementation overflow beyond 60
long C(int n, int r) {
    if(r > n - r) r = n - r; // because C(n, r) == C(n, n - r)
    long ans = 1;
    int i;

    for(i = 1; i <= r; i++) {
        ans *= n - r + i;
        ans /= i;
    }

    return ans;
}
```

### nPr

```java
//this implementation overflow beyond 20
long nPr(int n, int r){
    long res = 1;
    for (int i = n; i > n - r; i--)
        res *= i;
    return res;
}
```

### Generate all permutation (Sorted)

```java
public void nextPermutation(int[] nums) {
    
    if(nums.length == 1) return;
    
    // Find longest non-increasing suffix
    int i = nums.length - 1;
    while (i > 0 && nums[i - 1] >= nums[i])
        i--;
    // Now i is the head index of the suffix

    // Are we at the last permutation already?
    if (i <= 0){
        Arrays.sort(nums);
        return;
    }

    // Let array[i - 1] be the pivot
    // Find rightmost element that exceeds the pivot
    int j = nums.length - 1;
    while (nums[j] <= nums[i - 1])
        j--;
    // Now the value array[j] will become the new pivot
    // Assertion: j >= i

    // Swap the pivot with j
    int temp = nums[i - 1];
    nums[i - 1] = nums[j];
    nums[j] = temp;

    // Reverse the suffix
    j = nums.length - 1;
    while (i < j) {
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        i++;
        j--;
    }
}
```

Refer: [Next Permutation](https://leetcode.com/submissions/detail/226319532/)


### Generate all permutation

Heap algorithm

```java
private boolean shouldSwap(int[] nums, int start, int curr) {
    for (int i = start; i < curr; ++i)
        if (nums[i] == nums[curr])
            return false;

    return true;
}


private void permute(int[] nums, int left, int right, ArrayList<List<Integer>> r) {
    if (left == right) {
        ArrayList<Integer> t = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) t.add(nums[i]);
        r.add(t);
    } else {
        for (int i = left; i <= right; i++) {
            boolean check = shouldSwap(nums, left, i);
            if (check) {
                swap(nums, left, i);
                permute(nums, left + 1, right, r);
                swap(nums, left, i);
            }
        }
    }
}

private void swap(int[] input, int a, int b) {
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
}
```

Refer: [Permutations II](https://leetcode.com/submissions/detail/228523597/)

### Generate a random permutation

Fisher-Yates algorithm
Time complexity: O(n)

```java
Random r = new Random();

public int[] shuffle() {
    for(int i=temp.length-1; i>=1; --i){
        int j=  r.nextInt(i+1);
        swap(temp, i, j);
    }

    return temp;
}

private void swap(int[] a, int i, int j){
    int temp = a[i];
    a[i]= a[j];
    a[j] = temp;
}
```

Refer: [Shuffle an array](https://leetcode.com/submissions/detail/230731452/)

### Generate all combination

```java
//n is starting element, k is number of elements left to filled
//nmax is the maximum of each element could be
private void helper2(int n, int k, int nmax, Stack<Integer> cur, List<List<Integer>> r){
    if(k == 0){
        r.add(new ArrayList<Integer>(cur));
        return;
    }

    for(int i=n; i<=nmax; ++i){
        cur.push(i);
        helper2(i+1, k-1, nmax, cur, r);
        cur.pop();
    }
}
```

Refer: [Combinations](https://leetcode.com/submissions/detail/230498519/)

## Prime
    
### Seive
- check prime
- count different prime

### Prime factor
- num of prime
- num diff prime factor
- sum of prime factor
- num divisor
- sum of divisor
- euler phi - num of coprime

### Prime testing

```java
BigInteger BRN = BigInteger.valueOf(RN);
BN.isProbablePrime(10)
```

Refer: [API Doc](https://docs.oracle.com/javase/9/docs/api/java/math/BigInteger.html#isProbablePrime-int-)

## Greatest common dvisior

LCM * GCD = N * M

```java
static int gcd(int a,int b) {
    if (b==0) {
        return a;
    }
    int d;
    d = gcd(b, a%b);
    return d;
}
```

### linear diophantine equation 

To solve for integral root of equation e.g 25x + 18y = 839
- Run euclid and find out that 25*-5 + 18 *7=1
- Now multiply by 839/gcd(25, 18) => 25*-4195+18*5873=839
- x = -4915+18n, y = 5873-25n
- try to find all n that fit the requirement. e.g. both x and y > 0
```java
// store x, y, and d as global variables
void extendedEuclid(int a, int b) {
    if (b == 0) { x = 1; y = 0; d = a; return; } // base case
    extendedEuclid(b, a % b); // similar as the original gcd
    int x1 = y;
    int y1 = x - (a / b) * y;
    x = x1;
    y = y1;
}
```

Refer: []()

## Modular


### Multiplicative inverse


## Probability

N/A?

## Matrix

This is a library of matirx operations

## Cycle finding

## Game theory


## Calculator


### Infix to Postfix

Do actual faster than direct implementation.

```java
//TODO: enhance with bracket
static HashMap<String, Integer> piroirtyOp = new HashMap<String, Integer>();
piroirtyOp.put("+", 1);
piroirtyOp.put("-", 1);
piroirtyOp.put("*", 2);
piroirtyOp.put("/", 2);

List<String> ts = tokenize(s);
List<String> reversePolish = new ArrayList<String>();
Stack<String> sop  = new Stack<String>();
for(int i=0; i<ts.size(); ++i){
    String curToken = ts.get(i);
    if(curToken.compareTo("+") == 0 || curToken.compareTo("-") == 0 ||
            curToken.compareTo("*") == 0 || curToken.compareTo("/") == 0){
        while(!sop.empty()){
            String u = sop.peek();
            if(piroirtyOp.get(u) >= piroirtyOp.get(curToken))
                reversePolish.add(sop.pop());
            else
                break;
        }
        sop.push(curToken);
    }
    else
        reversePolish.add(curToken);
}
while(!sop.empty())
    reversePolish.add(sop.pop());
```

Refer: [Basic Calculator II](https://leetcode.com/submissions/detail/230025662)

### Postfix calculator



```java
Stack<Integer> si = new Stack<Integer>();

for(int i=0; i<reversePolish.size(); ++i){
    String curToken = reversePolish.get(i);
    if(curToken.compareTo("+") == 0 || curToken.compareTo("-") == 0 ||
            curToken.compareTo("*") == 0 || curToken.compareTo("/") == 0){
        int right = si.pop();
        int left = si.pop();
        int result = 0;
        switch(curToken){
            case "+":
                result = left+right;
                break;
            case "-":
                result = left-right;
                break;
            case "*":
                result = left*right;
                break;
            case "/":
                result = left/right;
                break;
        }
        si.push(result);
    }
    else
        si.push(Integer.valueOf(curToken));
}

return si.pop();
```

Refer: [Basic Calculator II](https://leetcode.com/submissions/detail/230025662)

### Infix calculator

```java
Stack<String> st = new Stack<>();
Stack<String> stOp = new Stack<>();
String currOp= "+";
Integer number;

for(String ss : token){
    
    if(Character.isDigit(ss.charAt(0))){
        number = Integer.parseInt(ss);
        
        process(st, currOp, number);
    }
    if(ss.compareTo("(") == 0){
        st.push("(");
        stOp.push(currOp);
        currOp = "+";
    }
    else if(ss.compareTo(")") == 0){
        //now we add all the number until we see the (
        int sum = 0;
        while(!st.isEmpty()){
            String sss = st.pop();
            if(sss.compareTo("(") == 0){
                currOp = stOp.pop();
                process(st, currOp, sum);
                break;
            }
            else
                sum += Integer.parseInt(sss);
        }
        //System.out.println();
    }
    else{
        currOp = ss;
    }
    
}

//now we add up all the thing
int sum = 0;
while(!st.isEmpty()){
    sum += Integer.parseInt(st.pop());
}
return sum;

private void process(Stack<String> st, String currOp, int number){
    if(currOp.compareTo("*") == 0){
        int other = Integer.parseInt(st.pop());
        int a = other * number;
        st.push(String.valueOf(a));
    }
    else if(currOp.compareTo("/") == 0){
        int other = Integer.parseInt(st.pop());
        int a = other / number;
        st.push(String.valueOf(a));
    }
    else if(currOp.compareTo("+") == 0){
        st.push(String.valueOf(number));
    }
    else if(currOp.compareTo("-") == 0){
        st.push(String.valueOf(-1 * number));
    }
}
```

Refer: [Basic Calculator III](https://leetcode.com/submissions/detail/637896732/)
