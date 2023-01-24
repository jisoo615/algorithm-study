import sys
input = sys.stdin.readline

n, k = map(int, input().split())
num = input().rstrip()
stack = []
for i in num:
    while stack and stack[-1] < i and k > 0: #스택이 비어져있고 stack[-1]>= i ,k<=0 클때 while문 벗어남
        stack.pop()                          #새로 들어온 수가 더 큰경우 앞에 있는것 지우기
        k -= 1
    stack.append(i)

print(''.join(stack[:len(stack)-k])) #k가 0이상일때 k만큼 지우고 출력

