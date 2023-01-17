from sys import stdin
n,m,l = list(map(int,stdin.readline().split()))
loc = list(map(int,stdin.readline().split()))#휴게소 위치
loc.append(0) #0과 끝에도 휴게소라고 가정
loc.append(l)
loc.sort()

start,end = 1,l
while start<=end:
    count =0
    mid = (start+end)//2 #휴게소 거리의 최댓값
    for i in range(1,len(loc)):
        if (loc[i]-loc[i-1])>mid:
            count+=(loc[i]-loc[i-1]-1)//mid #최대 mid간격만큼 휴게소 설치했을때의 개수를 count
    if count>m:
        start = mid+1
    else:
        end = mid -1
        answer = mid
print(answer)
