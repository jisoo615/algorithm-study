n = int(input()) # 수열의 길이
a = list(map(int, input().split())) # 수열
dp = [[a[i]] for i in range(n)] # dp, 초기 수열 설정

for i in range(1, len(a)) :
    num = a[i]
    for j in range(0, i) :
        if a[j] < num and len(dp[i]) <= len(dp[j]): # 증가하는 수열인지 and 최장증가수열인지
            dp[i] = dp[j] + [num]

answer = max(dp, key = len)
print(len(answer))
print(* answer)