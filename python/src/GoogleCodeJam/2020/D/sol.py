import sys
import array

#debug = open("C:\\GitProjects\\algorithm_practice\\python\\src\\GoogleCodeJam\\2020\\D\\debug.txt", "w")

def solve(b):
    result = []
    for i in range (0, b):
        result.append(2)

    for i in range(0, 15):
        leftQ = 10
        if i!= 0:
            qLeft = -1
            q2Mid = -1
            inverse = False
            flip = False

            for j in range(0, len(result)):
                k = b -j - 1
                if result[k] == 2 or result[j] == 2:
                    continue
                if(j > k):
                    break
                if result[j] != result[k]:
                    qLeft = j
                if result[j] == result[k]:
                    q2Mid = j

            #debug.write("Current: " + str(''.join([str(i) for i in result])) + "\n")

            if qLeft == -1:
                #debug.write("Symmetric\n")
                leftQ = 9
                print(q2Mid +1 )
                aMid = int(input())
                if aMid != result[q2Mid]:
                    flipString(result)
                    flip = True
            else:
                if q2Mid == -1 :
                    #debug.write("even length: a+Inv(a)\n")
                    # case 100110, which flip and reverse are the same
                    leftQ = 9
                    print(qLeft+1)
                    aLeft = int(input())

                    if aLeft != result[qLeft]:
                        flipString(result)
                        flip=True
                else:
                    #debug.write("Asym even length\n")
                    leftQ = 8
                    print(qLeft+1)
                    aLeft = int(input())
                    print(q2Mid+1)
                    aMid = int(input())

                    if aMid != result[q2Mid]:
                        flipString(result)
                        flip=True
                        if aLeft != result[qLeft]:
                            inverseString(result)
                            inverse=True
                    else:
                        if aLeft != result[qLeft]:
                            inverseString(result)
                            inverse=True

            #debug.write("Flip: true\n" if flip else "Flip: false\n")
            #debug.write("Inverse: true\n" if inverse else "Inverse: false\n")

        #debug.write("Left query: " + str(leftQ) + "\n")

        for i in range(0, leftQ):
            n = nextQuery(result)
            if n==-1:
                return ''.join([str(i) for i in result])
            #debug.write("Query: " + str(n))
            print(n)
            c = int(input())
            result[n-1] =c
            #debug.write(" Ans: " + str(c) + "\n")

    return ''.join([str(i) for i in result])

def nextQuery(result):
    a = len(result)
    mid = int(a/2)
    for i in range(0, mid):
        if result[mid+i] == 2:
            return mid+i+1
        elif result[mid-1-i] == 2:
            return mid-1-i+1

    return -1

def inverseString(input):
    for i in range(0, len(input)):
        j = len(input) - i - 1
        if i >=j:
            break
        temp = input[i]
        input[i] = input[j]
        input[j] = temp


def flipString(input):
    book = [1, 0, 2]
    for i in range(0, len(input)):
        input[i] = book[input[i]]

#name = "A-small"
#path = ""

#sys.stdin = open(path + name + ".in")
#sys.stdout = open(path + name + ".out", "w")

def main():

    #debug.write("start...\n")
    #a = [2,1,0,0,0,0,1,1,0,1,1,0,1,1,1,0,0,0,0,2]
    #inverseString(a)
    #debug.write(''.join([str(i) for i in a]) + "\n")
    #debug.flush()

    line = input()
    testCases = int(line.split()[0])
    b = int(line.split()[1])

    for testCase in range(1, testCases + 1):
        ans = solve(b)
        print(ans)
        c = input()
        if c == "N":
            #debug.write("case " + str(testCase) + ": failed\n")
            #debug.write("ans: " + ans + "\n")
            exit()
        #else:
            #debug.write("case " + str(testCase) + ": passed\n")
            #debug.write("ans: " + ans + "\n")

if __name__ == '__main__':
    main()