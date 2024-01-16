import java.util.Arrays;
import java.util.PriorityQueue;

public class P4 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[][] {{2, 0, 10},{1, 5, 5},{3, 5, 3},{3, 12, 2}})));
        System.out.println(Arrays.toString(new Solution().solution(new int[][] {{3, 6, 4},{4, 2, 5},{1, 0, 5},{5, 0, 5}})));
    }
    static class Solution {
        public long[] solution(int[][] program) {
            long[] answer = new long[11];
            // 우선수위큐 : [호출된 작업들 중] [중요도 우선] 그다음 [시작시간 우선]순위 따짐
            PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2)->{
                if(p1[0]==p2[0]) return p1[1] - p2[1];
                return p1[0]-p2[0];
            });
            Arrays.sort(program, (p1, p2)->p1[1] - p2[1]);

            int current = program[0][1], index = 0;
            for (int i = 0; i < program.length; i++) {
                if(current == program[i][1]){
                    index = i+1;
                    pq.add(program[i]);
                }else break;
            }

            // 대기시간 구하기
            while(!pq.isEmpty()){
                int[] arr = pq.poll();
                if(arr[1] < current){// 대기
                    answer[arr[0]] += (current - arr[1]);
                    current += arr[2];
                }
                else{// 노대기
                    current += arr[1] + arr[2];
                }

                // 호출된 작업을 pq에 담기
                while(index<program.length){
                    if(pq.isEmpty()){
                        current = current >= program[index][1] ? current : program[index][1];// pq에 없으면 일단 다음꺼 무조건 호출
                        pq.add(program[index]);
                        index++;
                        continue;
                    }
                    if(current >= program[index][1]){
                        pq.add(program[index]);
                        index++;
                    }else break;
                }// 호출 end

            }
            answer[0] = current;

            return answer;
        }
    }
}
