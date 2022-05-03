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
- number: 1, 1, 2, 5, 14, 42, 132, 429, 1430

```java
//this implementation can only be at 30
static long catalanNumber(int n){
    if(n == 0|| n ==1) return 1;
    return catalanNumber(n-1) * (2*n) * (2*n-1) / (n+1) / n;
}
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

Heap algorithm O(n!)
Heap algorithm cannot deal with duplicate items.

```java
private void helper(int n, ArrayList<List<Integer>> r){
    if(n == 1){
        ArrayList<Integer> t  = new ArrayList<>();
        for(int i=0; i<nums.length; ++i) t.add(nums[i]);
        r.add(t);
    }
    else{
        for(int i=0; i<n-1; ++i){
            helper(n - 1, r);
            if(n % 2 == 0) {
                swap(nums, i, n-1);
            } else {
                swap(nums, 0, n-1);
            }
        }
        helper(n - 1, r);
    }
}

private void swap(int[] input, int a, int b) {
    int tmp = input[a];
    input[a] = input[b];
    input[b] = tmp;
}

//in main
helper(nums.length, result);
```

Refer: [Permutations](https://leetcode.com/submissions/detail/226603025/)

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

## Base / Radix

- Read
```java
BigInteger p = new BigInteger(sc.next(), b);
```

- Write
```java
Integer.toBinaryString(N)
Integer.toOctalString(N)
Integer.toHexString(N)
```

- Write with fix length

```java
String.format("%8s", Integer.toBinaryString(N)).replace(' ', '0');
```

## Modular

### Modular exponentiation

```java
System.out.println(x.modPow(y, n));
```

### Multiplicative inverse

```java
var num = new BigInteger("7");
var mod = new BigInteger("20");
BigInteger inverse = num.modInverse(mod);
```

## Prime
    
### Seive

```java
long _sieve_size; // ll is defined as: typedef long long ll;
BitSet bs = new BitSet(10_000_010); // 10^7 should be enough for most cases
ArrayList<Integer> primes; // compact list of primes in form of vector<int>
void sieve(int upperbound) { // create list of primes in [0..upperbound]
    primes = new ArrayList<>();
    _sieve_size = upperbound + 1; // add 1 to include upperbound
    bs.set(0, (int)_sieve_size); // set all bits to 1
    bs.clear(0);
    bs.clear(1); // except index 0 and 1
    for (long i = 2; i <= _sieve_size; i++)
        if (bs.get((int) i)) {
            // cross out multiples of i starting from i * i!
            for (long j = i * i; j <= _sieve_size; j += i)
                bs.clear((int)j);
            primes.add((int) i); // add this prime to the list of primes
        }
} // call this method in main method

boolean isPrime(long N) { // a good enough deterministic prime tester
    if (N <= _sieve_size) return bs.get((int)N); // O(1) for small primes
    for (int i = 0; i < primes.size(); i++)
        if (N % primes.get(i) == 0) return false;
    return true; // it takes longer time if N is a large prime!
}
```


- check prime
- count different prime

### Prime factor

```java
ArrayList<Long> primeFactors(long N) { 
    ArrayList<Long> factors = new ArrayList<>();
    int PF_idx = 0;
    long PF = primes.get(PF_idx); // primes has been populated by sieve
    while (PF * PF <= N) { // stop at sqrt(N); N can get smaller
        while (N % PF == 0) { N /= PF; factors.add((long)PF); } // remove PF
        PF = primes.get(++PF_idx); // only consider primes!
    }
    if (N != 1) factors.add(N); // special case if N is a prime
    return factors; // if N does not fit in 32-bit integer and is a prime
} // then ‘factors’ will have to be changed to vector<ll>
// inside int main(), assuming sieve(1000000) has been called before
```

- num of prime

```java
long numPF(long N) {
    int PF_idx = 0;
    long PF = primes.get(PF_idx), ans = 0;
    while (PF * PF <= N) {
        while (N % PF == 0) { N /= PF; ans++; }
        PF = primes.get(++PF_idx);
    }
    if (N != 1) ans++;
    return ans;
}
```

- num diff prime factor

```java
//modified seive method
memset(numDiffPF, 0, sizeof numDiffPF);
void sieve(int upperbound) { // create list of primes in [0..upperbound]
    primes = new ArrayList<>();
    _sieve_size = upperbound + 1; // add 1 to include upperbound
    bs.set(0, (int)_sieve_size); // set all bits to 1
    bs.clear(0);
    bs.clear(1); // except index 0 and 1
    for (long i = 2; i <= _sieve_size; i++)
        if (numDiffPF[i] == 0) // i is a prime number
            for (int j = i; j < MAX_N; j += i)
                numDiffPF[j]++; // increase the values of multiples of i
} // call this method in main method
```

- sum of prime factor
- num divisor

```java
long numDiv(long N) {
    int PF_idx = 0;
    long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
    while (PF * PF <= N) {
        long power = 0; // count the power
        while (N % PF == 0) { N /= PF; power++; }
        ans *= (power + 1); // according to the formula
        PF = primes.get(++PF_idx);
    }
    if (N != 1) ans *= 2; // (last factor has pow = 1, we add 1 to it)
    return ans;
}
```
- sum of divisor

```java
long sumDiv(long N) {
    int PF_idx = 0;
    long PF = primes.get(PF_idx), ans = 1; // start from ans = 1
    while (PF * PF <= N) {
        long power = 0;
        while (N % PF == 0) { N /= PF; power++; }
        ans *= ((long)Math.pow((double)PF, power + 1.0) - 1) / (PF - 1);
        PF = primes.get(++PF_idx);
    }
    if (N != 1) ans *= ((long)Math.pow((double)N, 2.0) - 1) / (N - 1); // last
    return ans;
}
```


### Co-Prime

euler phi - num of coprime

```java
long EulerPhi(long N) {
    int PF_idx = 0;
    long PF = primes.get(PF_idx), ans = N; // start from ans = N
    while (PF * PF <= N) {
        if (N % PF == 0) ans -= ans / PF; // only count unique factor
        while (N % PF == 0) N /= PF;
        PF = primes.get(++PF_idx);
    }
    if (N != 1) ans -= ans / N; // last factor
    return ans;
}
```

### Prime testing

```java
BigInteger BRN = BigInteger.valueOf(RN);
BN.isProbablePrime(10)
```

Refer: [API Doc](https://docs.oracle.com/javase/9/docs/api/java/math/BigInteger.html#isProbablePrime-int-)

## Greatest common dvisior

LCM * GCD = N * M

```java
int gcd(int a,int b) {
    if (b==0) {
        return a;
    }
    int d;
    d = gcd(b, a%b);
    return d;
}

int lcm(int a, int b) { return a * (b / gcd(a, b)); }
```

```java
BigInteger gcd_pq = p.gcd(q);
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

Refer: [UVA10633](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/Mathematics/ExtendedEuclidean/UVA10633.java)



## Probability

N/A?

## Matrix

This is a library of matirx operations

## Cycle finding

```java
ListNode f(ListNode input){
    if(input == null) return null;
    else return input.next;
}

Pair floydCycleFinding(int x0) { // function int f(int x) is defined earlier
    // 1st part: finding k*mu, hare’s speed is 2x tortoise’s
    ListNode tortoise = f(x0), hare = f(f(x0)); // f(x0) is the node next to x0
    while (tortoise != hare) { 
        tortoise = f(tortoise); 
        hare = f(f(hare)); 
    }

    if(tortoise == null && hare == null) return null;

    // 2nd part: finding mu, hare and tortoise move at the same speed
    int mu = 0; hare = x0;
    while (tortoise != hare) {
        tortoise = f(tortoise); 
        hare = f(hare); mu++;
    }
    // 3rd part: finding lambda, hare moves, tortoise stays
    int lambda = 1; hare = f(tortoise);
    while (tortoise != hare) {
        hare = f(hare); 
        lambda++; 
    }
    return new Pair(mu, lambda);
}
```

Refer: [Linked List Cycle](https://leetcode.com/submissions/detail/689577137/)

## Game theory

TBC

### Nim game

TBC

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
