import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1477_휴게소세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+2];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }arr[N] = 0; arr[N+1] = L;

        Arrays.sort(arr);
// M개를 세울 수 있는 간격 중 최대 거리를 구하라 -> 가능한것중 젤큰것 -> upper bound를 구해라,
        int start = 1, end = L, result = 0;
        while(start <= end){
            int mid = (start+end)/2;

            int cnt = find(mid, arr);
            if( cnt <= M ){// M 이하 세울수 있으니 간격 좁혀야
                result = mid;
                end = mid -1;
            }else{// M개 초과 하니 간격 늘리기
                start = mid+1;
            }
        }
        System.out.println(result);
    }
    static public int find(int dist, int[] arr){
        int cnt = 0;
        for (int i = 1; i < arr.length; i++) {
            if((arr[i] - arr[i-1]) <= dist) continue;// 건물을 세울수 없음
            else cnt += (arr[i] - arr[i-1]) / dist - ((arr[i]-arr[i-1])%dist==0? 1:0);
            // 건물을 세울수 있을때, 나누어 떨어지면 한개는 겹친다는뜻 -> -1개 해줌
        }
        return cnt;
    }

    public void wrongSolution(int[] arr, int N, int M){
        Arrays.sort(arr);
        // 휴게소들 사이의 거리를 구해서 배열로
        PriorityQueue<Integer> distance = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < arr.length-1; i++) {
            distance.add( arr[i+1] - arr[i] );
        }

        for (int i = 0; i < M; i++) {
            int longest = distance.poll();

            distance.add(longest/2);
            if(longest%2==0) distance.add(longest/2);
            else distance.add(longest/2+1);
        }

        System.out.println(distance.poll());
    }
}
