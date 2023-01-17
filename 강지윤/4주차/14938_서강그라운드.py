#각 지역은 일정한 길이(1~15)로 다른 지역과 연결(양방향 통행)
#낙하한 지역 중심으로 수색범위(1~15)이내 모든 지역의 아이템 습득가능
#최대 얻을 수 있는 아이탬의 개수 구하기
INF = int(1e9) # 무한을 의미하는 값으로 10억을 설정

import sys
input = sys.stdin.readline

n,m,r = map(int,input().split()) #노드개수,수색범위,간선개수
item=list(map(int,input().split()))

# 2차원 리스트(그래프 표현)를 만들고, 모든 값을 무한으로 초기화
graph = [[INF] * (n + 1) for _ in range(n + 1)]

# 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
for a in range(1, n + 1):
    for b in range(1, n + 1):
        if a == b:
            graph[a][b] = 0

# 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
for _ in range(r):
    # A에서 B로 가는 비용은 C라고 설정
    a, b, c = map(int, input().split())
    graph[a][b] = c
    graph[b][a] = c #양방향일때

# 점화식에 따라 플로이드 워셜 알고리즘을 수행
for k in range(1, n + 1):
    for a in range(1, n + 1):
        for b in range(1, n + 1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

count=0
result=[]
for z in range(1,n+1): #지역별 아이템 먹을수 있는 최대개수 찾기 z가 떨어진 낙하지점
    for y in range(1,n+1): #자기 지역 포함
        if graph[z][y] <=m:
            count+=item[y-1]
    result.append(count)
    count=0

print(max(result))
