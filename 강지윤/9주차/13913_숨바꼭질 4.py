import sys
input = sys.stdin.readline
from collections import deque
cnt=0
def path(x):
    arr=[]
    result=x
    for j in range(visit[x]+1):
        arr.append(result)
        result=node[result] #이전 기록
    print(" ".join(map(str,arr[::-1])))
def bfs():
    q=deque()
    q.append(n)
    while q:
        x=q.popleft()
        if x==k:
            print(visit[x])
            path(x)
            return
        for i in [x-1,x+1,x*2]:
            if 0<=i<100001 and visit[i]==0:
                q.append(i)
                visit[i]=visit[x]+1
                node[i]=x # 이전기록 저장


n,k = map(int,input().split())
visit=[0]*100001 #count
node=[0]*100001 #경로 기록
bfs()
