N = int(input())
arr = [[] for _ in range(N + 1)]
result = set([])
for i in range(N):
    tmp = int(input())
    arr[tmp].append(i + 1)

for i in range(1, N + 1):
    visited = [False for _ in range(N + 1)]
    stack = [i]
    visited[i] = True

    while stack:
        top = stack.pop()

        for j in arr[top]:
            if not visited[j]:
                stack.append(j)
                visited[j] = True
            elif visited[j] and i == j: #사이클 돌았을때
                result.add(j)

result = list(result)
result.sort()
print(len(result))
for i in range(len(result)):
    print(result[i])