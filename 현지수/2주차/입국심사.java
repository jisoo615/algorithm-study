import java.util.Arrays;

//프로그래머스 입국심사 https://school.programmers.co.kr/learn/courses/30/lessons/43238
public class 입국심사 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[] {7,10}));
    }

    static class Solution {
        public long solution(int n, int[] times) {
            // n명이 모두 입국심사를 마치는 최소 시간을 구해라
            // 모두가 끝나는 시간을 구하는 거니까, 0분에 times.length명이 아니라, 7분에 1명, 10분에 2명이 끝난다는 식으로 구해야
            long left = 0, right = Arrays.stream(times).max().getAsInt() * n, result=0;
            while (left < right){
                long mid = (left+right)/2;

                int cnt = 0;// 여기선 모두 입국심사를 끝낼때까지의 시간을 구하니까
                for (int i = 0; i < times.length; i++) {
                    cnt += mid / times[i];
                }
                // 최소 n명 이상을 다 끝내는, 최초 n 이상이 나오는 곳
                if(n <= cnt){// 시간 줄이기
                    result = mid;
                    right = mid-1;
                }else{// 시간 늘리기
                    left = mid+1;
                }
            }

            return result;
        }
    }
}
