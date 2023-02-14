import java.lang.Math;
import java.util.*;
// 레밸 2 탐욕법
public class Kakao_23blind_택배배달과수거하기 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(4, 5, new int[] {1, 0, 3, 1, 2}, new int[] {0, 3, 0, 4, 0}));
    }


    static class Solution {
        // 테케 [0,6], [0,0]
        public long solution(int cap, int n, int[] deliveries, int[] pickups) {
            int pickup = 0, deliver = 0, cnt=0;
            long answer = 0;
            for (int i = n-1; i >=0; i--) {
                if(deliveries[i]!=0 || pickups[i]!=0){// 둘다 0일땐 택배수거가 필요 없음
                    cnt = 0;
                    deliver -= deliveries[i];
                    pickup -= pickups[i];

                    while(deliver < 0 || pickup < 0){// 택배 수거 중 하나라도 필요하면 지나가야함
                        cnt++;// 해당 지점을 몇번 가야 하는지 체크
                        deliver += cap;
                        pickup += cap;
                    }

                    answer += (i+1)*2*cnt;// i+1=편도거리
                }
            }
            return answer;
        }


        public long solution2(int cap, int n, int[] deliveries, int[] pickups) {
            long answer = 0;

            for(int i=n-1; i>0; i--){
                deliveries[i-1] += deliveries[i];
                pickups[i-1] += pickups[i];
                deliveries[i] = (int) Math.ceil(deliveries[i]/(double)cap);
                pickups[i] = (int) Math.ceil(pickups[i]/(double)cap);
            }
            deliveries[0] = (int)Math.ceil(deliveries[0]/(double)cap);
            pickups[0] = (int)Math.ceil(pickups[0]/(double)cap);

            for(int i=0; i<n-1; i++){
                int preBig = Math.max(deliveries[i], pickups[i] );
                int postBig = Math.max(deliveries[i+1], pickups[i+1] );
                if(preBig > postBig){
                    answer += (i+1)*2;
                }
            }
            if(deliveries[n-1]!=0 && deliveries[n-1]!=0) answer += n*2;

            return answer;
        }
    }
}
