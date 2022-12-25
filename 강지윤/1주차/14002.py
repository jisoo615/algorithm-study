from sys import stdin
N=int(stdin.readline())
A=list(map(int,stdin.readline().split()))
dp = [0]*N
test=[[x] for x in A]

for i in range(N):
        for j in range(i):
            if A[i]>A[j] and dp[i]<dp[j]:
                test[i]=test[j]+[A[i]]
                dp[i]=dp[j]
        dp[i]+=1
answer=max(dp)
print(answer)
print(*test[dp.index(answer)])