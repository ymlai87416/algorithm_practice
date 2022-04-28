# Sample different room and return the mean
# python interactive_runner.py python3 local_testing_tool.py3 -- python3 e.py
import sys
import random

f = open("debug.txt", "w", encoding='UTF-8')
def debug(message):
    pass
    f.write(message + "\n")
    f.flush()

def main():
    # read line
    line = input()
    tcase = int(line)

    for i in range(0, tcase):
        #debug("**************")
        line = input()
        #debug("C " + line)

        line = line.split()
        N = int(line[0])
        if(N == -1):
            break

        K = int(line[1])

        list = []
        got = []

        for i in range(0, N):
            list.append(-1)
            got.append(i)

        # get inital room
        line = input()
        #debug("C " + line)
        line = line.split()
        list[int(line[0])-1] = int(line[1])


        random.shuffle(got)
        #debug(str(got))

        nextIdx = 0
        for j in range(0, K):
            # we try to teleport every room
            
            while(nextIdx < N and list[got[nextIdx]] != -1):
                nextIdx = nextIdx+1
            if nextIdx >= N:
                break

            query = "T " + str(got[nextIdx]+1)
            print(query)
            sys.stdout.flush()
            #debug("M " + query)

            line = input()
            
            #debug("C " + line)
            linex = line.split()

            list[int(linex[0])-1] = int(linex[1])

            nextIdx = nextIdx+1

        # if N < K we just add up and /2
        if(N <= K+1):
            sum = 0
            for i in range(0, N):
                sum += list[i]
            sum = sum//2
            ans = "E " + str(sum)
            print(ans)
            #debug(ans)
            sys.stdout.flush()
        else:
            
            # from the result, we know we have random sample
            total = 0
            cnt = 0
            valid = []
            for i in range(0, N):
                if list[i] > -1:
                    total += list[i]
                    cnt += 1
                    valid.append(list[i])
            mean = total/cnt

            valid.sort()
            vn = len(valid)
            median = (valid[vn//2-1]/2.0+valid[vn//2]/2.0, valid[vn//2])[vn % 2] if vn else None

            ans = round(median * N / 2)
            
            #debug("mean: " + str(mean) + " " + str(ans))

            ans = "E " + str(ans)
            print(ans)
            #debug(ans)
            sys.stdout.flush()


if __name__ == '__main__':
    main()