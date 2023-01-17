import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**9)

def dfs1(node):
    global count
    visit[node]=True

    if not visit[tree[node][0]] and tree[node][0]!=-1:
        dfs1(tree[node][0])
        count += 1
    if not visit[tree[node][1]] and tree[node][1]!=-1:
        dfs1(tree[node][1])
        count += 1

def dfs2(node):
    global count2
    visit[node]=True

    if not visit[tree[node][1]] and tree[node][1]!=-1:
        dfs2(tree[node][1])
        count2 += 1
n=int(input())
tree=[[] for _ in range(n+1)]

for i in range(n):
    a,b,c=map(int,input().split())
    tree[a].append(b)
    tree[a].append(c)

visit=[False]*(n+1)
count=0
dfs1(1)
count2=0
visit=[False]*(n+1)
dfs2(1)
print(2*count-count2)