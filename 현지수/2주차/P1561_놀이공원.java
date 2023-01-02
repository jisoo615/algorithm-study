import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1561_놀이공원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());// 사람수
        int M = Integer.parseInt(st.nextToken());// 놀이기구 수
        int[] ride_time = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            ride_time[i] = Integer.parseInt(st.nextToken());
        }
// ------------------------------------------------------------------------------
        if(N <= M){ System.out.println(N);return;}
        long longest_time = Arrays.stream(ride_time).max().getAsInt();
        
        // 총 걸리는 시간을 구하기
        long start = 0, end = (N/M)*longest_time, total_time=0;
        while(start <= end){// 더이상 줄이면 안되는 순간 전, N명 이상을 모두 태울수 있는 최소 시간
            // start <= end 해야됨 <로 하면 찾는 수보다 하나 큰것만 찾음
            long mid = (start+end)/2;

            long cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += mid/ride_time[i];// mid 시간동안 태울수 있는 총 인원의 수 cnt
            }

            if(N <= cnt){// 시간 줄이기
                total_time = mid;
                end = mid-1;
            }else{// 시간 늘리기
                start = mid+1;
            }
        }
        
        // 찾은 걸리는 시간에 맞춰 N이 몇번쨰 놀이기구 타는지
        int answer = 0;
        int pre = M; // total_time-1 시간 까지 탄 명 수 pre
        for (int i = 0; i < M; i++) {
            pre += (total_time-1) / ride_time[i];
        }
        // total_time 시간에 탄 사람중 +1 하면서 N이 될때의 놀이기구 반환
        for (int i = 0; i < M; i++) {
            if(total_time % ride_time[i] == 0){// 나누어 떨어지면 여기서 탔다는 뜻 -> 마지막으로 0이 되는 곳이 정답
                answer = i+1;
                pre++;
            }
            if(pre == N) break;// N이 되는 순간
        }
        System.out.println(answer);
    }

}
