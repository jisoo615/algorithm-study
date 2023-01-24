import javax.swing.plaf.multi.MultiSeparatorUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* https://www.acmicpc.net/problem/1700
1. 먼 미래에 사용될 것을 교체한다. 바로 안 쓰일 것을 교체
2. 각 물건들이 사용되는 시기 record가 있어야 함
3. 자리가 없을때 가장 나중에 사용될 물건을 빼고 탭을 꼽는다.
 */
public class P1700_멀티탭스케줄링 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int answer = 0;
        int[] order = new int[K];
        boolean[] using = new boolean[101];
        HashMap<Integer, PriorityQueue<Integer>> record = new HashMap<>();

        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
            PriorityQueue<Integer> pq = record.getOrDefault(order[i], new PriorityQueue<Integer>());// 오름차순
            pq.offer(i);
            record.put(order[i], pq);
        }
//-----------------------------------------------
        int rest = N;
        for (int i = 0; i < K; i++) {
            int product = order[i];
            // 이미 사용중일때
            if(using[product]){
                record.get(product).poll();
                continue;
            }

            // 사용중 아닐때
            // 자리 있을때
            if(rest > 0){
                record.get(product).poll();
                using[product] = true;
                rest--;
                continue;
            }
            // 자리 없을때 = 코드 빼는 횟수
            int farest = 1, max = -1;
            for(int key : record.keySet()){
                if( !using[key] ) continue;// 사용중 아닌건 필요X

                if(record.get(key).isEmpty()){// 나중에 더이상 사용 안하면 그 자리에
                    farest = key;
                    break;// 틀렸던 부분 - 이제 사용 안하는 물건 발견시 탐색 break;
                }
                else if(max < (record.get(key).peek())){// 가장 나중에 사용할 콘센트 뽑고 그자리에
                    max = record.get(key).peek();
                    farest = key;
                }
            }
            using[farest] = false;// 뽑을 물건 체크
            using[product] = true;
            record.get(product).poll();
            answer++;
        }

        System.out.println(answer);
    }
}
