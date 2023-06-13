import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1911_흙길보수하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        Arrays.sort(arr, (int[] o1, int[] o2)->{return o1[0]-o2[0];});
        int cover = 0, diff = 0;
        for (int i = 0; i < N; i++) {
            if(cover > arr[i][0]){// 이전 커버가 덮어씌워지면
                diff = arr[i][1] - cover;
            }else{// 덮어씌워지지 않으면
                diff = arr[i][1] - arr[i][0];
                cover = arr[i][0];
            }
            int cnt = diff%K>0 ? diff/K+1 : diff/K;
            cover += cnt*K;
            answer += cnt;
        }
        System.out.println(answer);
    }
}
