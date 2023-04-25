// https://school.programmers.co.kr/learn/courses/30/lessons/152995
import java.util.Arrays;

public class S152995_인사고과 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][] {{2,2},{1,4},{3,2},{3,2},{2,1}}));// return 4
        System.out.println(new Solution().solution(new int[][] {{100,1},{100,2},{50,1}}));// return 2
        System.out.println(new Solution().solution(new int[][] {{2, 2},{2, 2},{2, 3},{3, 2}}));// return 3
        System.out.println(new Solution().solution(new int[][] {{4,1},{2,4},{3,5}}));// return 2

    }
    static class Solution {
        public int solution(int[][] scores) {
            // 원호인지 알 필요가 없음 원호점수만 알면 됨
            int[] wonho = scores[0];
            Arrays.sort(scores, (int[] s1, int[] s2) -> {// 내림차순, 오름차순
                // 내림차순이 아닌 이유: 보다 태도점수랑 동료평가도 낮은 경우를 찾아야 함 - 내림차순 내림차순 이면, 태도점수가 같을때 동료평가가 낮은 사람일떄 인센없다고 처리함
                if (s1[0] == s2[0]) {
                    return s1[1] - s2[1];// 동료평가 오름차순
                }
                return s2[0] - s1[0];
            });

            int wonhoSum = wonho[1] + wonho[0];
            int answer = 0;

            int peerMax = 0;
            for(int[] s : scores){
                int sum = s[0]+s[1];

                if(peerMax > s[1]){
                    // 동료평가 낮으면 인센  x
                    if(s.equals(wonho)) return -1;// 두 점수가 맞는지 판별해야 하는데 sum을 기준으로 해서 문제였음
                }
                else if(sum > wonhoSum) {
                    // 합이 원호보다 높은 사람
                    answer++;
                }
                peerMax = Math.max(peerMax, s[1]);// 여태까지 가장 큰 동료평가점수 갱신
            }

            return answer+1;// 랭킹 + 원호번째
        }
    }
}
