from sys import stdin
N=int(stdin.readline())
A=list(map(int,stdin.readline().split()))
test=[]
test1=[] #현재기준 LIS
count=0
test.append(A[0])
i=0
while True:
    if count==N-1: #전체 다 돌았을 시
        break
    if i==N-1: #마지막 원소까지 왔을때
        if len(test1)>len(test): #test1: 이전까지 LIS , test=현재 부분수열
            test=[]
        elif len(test1)<len(test):
            test1=[]
            test1=test
        count+=1
        i=count-1 #밑에 i+=1 고려함
    if A[i]<A[i+1] and test[-1]<A[i+1]:
        test.append(A[i+1])
    i+=1
print(len(test1))
print(*test1)
