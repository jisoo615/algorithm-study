import java.util.ArrayList;
import java.util.Arrays;

public class P12936_줄서는방법 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString( new Solution().solution(3, 5) ));// [3,1,2]
        System.out.println(Arrays.toString( new Solution().solution(3, 6) ));// [3,2,1]
        System.out.println(Arrays.toString( new Solution().solution(4, 4) ));// [1,3,4,2]
        System.out.println(Arrays.toString( new Solution().solution(4, 24) ));// [4,3,2,1]
    }
    static class Solution {
        int[] answer;
        long[] fac;
        ArrayList<Integer> people = new ArrayList<>();

        public int[] solution(int n, long k){
            // 몇번째 자리에 어떤 숫자가 오는지 찾기
            answer = new int[n];
            fac = new long[n+1];
            factorial(n);// n-1! = n번째 자리의 수가 반복되는 횟수 ex. 2번 ['1',2,3] ['1',3,2] [2,1,3]...

            /**
             * k번째 / 반복횟수 = 숫자가 바뀐 횟수 = 남은 숫자 인덱스
             * k % 반복횟수 = 다음에 구할 k번째
             */
            int depth = 0;
            while (k > 1){
                int quote = (int) (k /fac[--n]);// 인덱스구하기
                k = k % fac[n];// 나머지가 0이면 맨 마지막, 1이면 첫번째 경우 (1이하 될때까지 나누기 진행)

                int index = k==0 ? quote-1 : quote;
                answer[depth++] = people.remove(index);
            }

            /**
             * k=0이면 맨 마지막 순서, 1이면 첫번째 순서 -> 남은 숫자 순서대로 정답에 넣기
             */
            if(k==0) {
                for (int i = people.size()-1; i >=0; i--) {
                    answer[depth++] = people.get(i);
                }
            }else{
                for (int i = 0; i < people.size(); i++) {
                    answer[depth++] = people.get(i);
                }
            }// reverse order 해서 for(x : people) 하면 시간초과

            return answer;
        }

        public long factorial(int n){
            people.add(0, n);
            if(n==1) return 1;
            return fac[n] = n * factorial(n-1);
        }

//        public int[] solution2(int n, long k) {// n명이 줄을 서는데 k번째 방법의 경우를 구하라
//            this.k = k;
//            this.cnt = 0L;
//            visited = new boolean[n];
//            recur(new int[n], 0);
//
//            return answer;
//        }
//        public void recur(int[] arr, int depth){// 기본 순열
//            if(depth==arr.length){
//                cnt++;
//                System.out.println(cnt+" : "+Arrays.toString(arr));
//                if(cnt == k) this.answer = arr.clone();
//                return;
//            }
//
//            for (int i = 0; i < this.visited.length; i++) {
//                if(visited[i]) continue;
//                arr[depth] = i+1;
//                visited[i] = true;
//                recur(arr, depth+1);
//                arr[depth] = 0;
//                visited[i] = false;
//            }
//        }
    }
}
