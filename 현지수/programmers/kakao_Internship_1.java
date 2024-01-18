import java.util.HashMap;

public class kakao_Internship_1 {
    // 가장 많이 받은 선물 https://school.programmers.co.kr/learn/courses/30/lessons/258712

    static class Solution {
        public int solution(String[] friends, String[] gifts) {
            HashMap map = new HashMap<String, Integer>();

            for(int i=0; i<gifts.length; i++){
                String[] sarr = gifts[i].split(" ");
                // 0이 1에게 선물을 줌 -> 선물지수 0:+1, 1:-1
                map.put(sarr[0], (int)map.getOrDefault(sarr[0], 0)+1);
                map.put(sarr[1], (int)map.getOrDefault(sarr[1], 0)-1);
            }

            int max = -500001;
            for(int i=0; i<friends.length; i++){
                if((int)map.get(friends[i]) > max) max = (int)map.get(friends[i]);
            }

            // ... 다시 생각해보기
            return max;
        }
    }

}
