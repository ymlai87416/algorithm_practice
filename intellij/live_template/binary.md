# Binary

## Number of bit set

```java
static int NumberOfSetBits(int i)
{
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
}

static int NumberOfSetBits(int i){
    int cnt = 0;
    while(i != 0){
        i = i & (i - 1); //equivalent to i -= (i & -i);
        ++cnt;
    }
    return cnt;
}
```

Refer: [UVA10496](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/ProblemSolving/TSP/UVA10496.java)

## Last set bit

Get last set bit, useful in Fenwick tree.

```java
static int lastSetBit(int n) {
    return n & (-n);
}
```

Remove last set bit

```java
x = x & (x - 1);
```

## Rotate bit

```java
private int rotate8_(int data, int distance){
    distance = distance & 7;
    data &= 0xFF;
    return (data >> distance) | (data << (8 - distance)) & 0xFF;
}
```

Refer: [GJ-2022-1B-C](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/GoogleCodeJam/Y2022/Round1B/C/Solution.java)