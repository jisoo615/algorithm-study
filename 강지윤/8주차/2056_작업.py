#모든 작업을 완료하기 위해 필요한 최소시간 구하기
#서로 선행 관계가 없는 작업들은 동시에 수행 가능
import sys
input = sys.stdin.readline

n = int(input())
dp = [0] * (n+1) #작업별 소요되는 시간
for i in range(1,n+1):
    work = list(map(int, input().split()))
    for num in work[1:]: #선행관계 작업 개수
        dp[i] = max(dp[i], work[0]+dp[num]) #현 작업 시간 + 선행관계에 있는 작업들 중 가장 마지막에 끝나는 작업
print(max(dp))