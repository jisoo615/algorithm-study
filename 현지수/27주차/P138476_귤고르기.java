import java.util.*;
import java.util.stream.Collectors;

public class P138476_귤고르기 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(6, new int[] {1, 3, 2, 5, 4, 5, 2, 3}));// 3
    }
    static class Solution {
        public int solution(int k, int[] tangerine) {
            int answer = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int t : tangerine){
                map.put(t, map.getOrDefault(t, 0)+1);
            }

            ArrayList<Integer> keys = map.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
            keys.sort((o1, o2)-> map.get(o2) - map.get(o1));// 내림차순

            int cnt = 0;
            for (int key : keys) {
                cnt += map.get(key);
                answer += 1;
                if(cnt >= k) break;
            }

            return answer;
        }
    }
}
