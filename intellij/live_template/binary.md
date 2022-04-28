# Binary

## Number of bit set

```java
static int NumberOfSetBits(int i)
{
    i = i - ((i >>> 1) & 0x55555555);
    i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
    return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
}
```

Refer: [UVA10496](https://github.com/ymlai87416/algorithm_practice/blob/master/java/src/main/java/ProblemSolving/TSP/UVA10496.java)

## Rotate bit