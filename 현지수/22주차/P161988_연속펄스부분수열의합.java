public class P161988_연속펄스부분수열의합 {
    // 프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/161988
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[]{2, 3, -6, 1, 3, -1, 2, 4}));// 10
//        System.out.println(new Solution().solution(new int[]{100, -100, 100, 200}));// 300
    }
    static class Solution {
        public long solution(int[] sequence) {
            long[] dp_1 = new long[sequence.length];
            long[] dp_2 = new long[sequence.length];
// dp[n] = n을 포함하는 합의 최댓값 (이어서 가거나, 첫시작)

            // -1 1, 1 -1순서대로 곱해줌
            dp_1[0] = sequence[0];// +
            dp_2[0] = sequence[0]* -1;// -
            long answer = Math.max(dp_1[0], dp_2[0]);

            int k = -1;
            for (int i = 1; i < sequence.length; i++) {// - +
                int temp = sequence[i];
                dp_1[i] = Math.max(dp_1[i-1] + k*temp, k*temp);// 1 -1 1
                k *= -1;
                dp_2[i] = Math.max(dp_2[i-1] + k*temp, k*temp);// -1 1 -1

                answer = Math.max(answer, Math.max(dp_1[i], dp_2[i]) );
            }

            return answer;
        }
    }

}
