from sys import stdin #소형기관차 3대 이용
T=int(stdin.readline()) #객차의 수
P= list(map(int,stdin.readline().split())) #각 객차의 손님 수
S= int(stdin.readline()) #소형기관차가 최대로 끌 수 있는 객차의 수
dp =[[0]*(T+1) for _ in range(4)] #2차원배열
sum=[0]
person=0

for k in P: #구간까지 합 게산
    person+=k
    sum.append(person)

for i in range(1,4):
    for j in range(i*S,T+1):
        if i==1: #1일때
            dp[i][j]=max(dp[i][j-1],sum[j]-sum[j-S])
        else:
            dp[i][j]=max(dp[i][j-1],dp[i-1][j-S]+sum[j]-sum[j-S])

print(dp[3][T])