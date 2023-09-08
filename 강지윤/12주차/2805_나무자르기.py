import sys
input = sys.stdin .readline
n,m = map(int, input().split())

trees = list(map(int, input().split()))

left, right = 0, max(trees) #시작점과 끝점을 정함

while left <= right:
    mid = (left + right) // 2
    total = 0

    for tree in trees:
        if tree >= mid:
            total += tree - mid

    if total >= m:
        left = mid + 1
    else:
        right = mid - 1

print(right)