import sys
input = sys.stdin.readline
n, k = map(int, input().split())
coin = []

for i in range(n):
    coin.append(int(input()))

dp = [10001] * (k + 1)
dp[0] = 0

for j in coin:
    for i in range(j, k + 1):
        dp[i] = min(dp[i], dp[i - j] + 1)
if dp[k] == 10001:
    print(-1)
else:
    print(dp[k])
