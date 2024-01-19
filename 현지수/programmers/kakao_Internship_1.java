import java.util.HashMap;
// 가장 많이 받은 선물 https://school.programmers.co.kr/learn/courses/30/lessons/258712

public class kakao_Internship_1 {

    public static void main(String[] args) {
        System.out.println( new Solution().solution(new String[] {"muzi", "ryan", "frodo", "neo"},
                new String[] {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"})
        );
    }

    static class Solution {
        public int solution(String[] friends, String[] gifts) {
            HashMap<String, Integer> giftPoint = new HashMap<>();
            HashMap<String, HashMap<String, Integer>> map = new HashMap<String, HashMap<String, Integer>>();// 누가 누구에게 선물을 줬는지 저장
            HashMap<String, Integer> result = new HashMap<>();

            //1. giftPoint, map, result 초기화
            for (int i = 0; i < friends.length; i++) {
                giftPoint.put(friends[i], 0);
                map.put(friends[i], new HashMap<>());
                result.put(friends[i], 0);
            }
            // map.receivers 초기화
            for(int i=0; i<friends.length; i++) {
                HashMap<String, Integer> receiver = map.get(friends[i]);

                for(int j=0; j<friends.length; j++) {
                    if(i == j) {
                        continue;
                    }
                    receiver.put(friends[j], 0);
                }
            }

            // 2. 선물 기록
            for(int i=0; i<gifts.length; i++){
                String[] sarr = gifts[i].split(" ");
                String from = sarr[0];
                String to = sarr[1];
                // 0이 1에게 선물을 줌 -> 선물지수 0:+1, 1:-1
                giftPoint.put(from, (int)giftPoint.getOrDefault(from, 0)+1);
                giftPoint.put(to, (int)giftPoint.getOrDefault(to, 0)-1);

                // from이 누구to에게 줬는지를 표시
                HashMap<String, Integer> receivers = map.get(from);
                receivers.put(to, receivers.getOrDefault(to, 0)+1);
                map.put(sarr[0], receivers);
            }

            // 3. 결과 : 둘 중 더 많이 준 사람이 선물을 +1 하게 되고, 같은 값이면 선물지수가 더 많은 사람이 선물 +1됨, 선물지수도 같으면 +0
            for (int i = 0; i < friends.length; i++) {
                String from = friends[i];
                HashMap<String, Integer> receivers = map.get(from);
                for (int j=i+1; j<friends.length; j++){
                    String to = friends[j];

                    int fromToto = receivers.get(to);
                    int toToFrom = map.get(to).get(from);

                    if(fromToto > toToFrom){
                        result.put(from, result.get(from)+1);
                    }else if(fromToto < toToFrom){
                        result.put(to, result.get(to)+1);
                    }else{// 같으면 선물지수로 결정
                        int fromPoint = giftPoint.get(from);
                        int toPoint = giftPoint.get(to);

                        if( fromPoint > toPoint ){
                            result.put(from, result.get(from)+1);
                        }else if( fromPoint < toPoint ){
                            result.put(to, result.get(to)+1);
                        }
                    }
                }
            }

            int max = Integer.MIN_VALUE;
            for(String f : friends){
                max = Math.max( max, result.get(f) );
            }
            return max;
        }
    }

}
