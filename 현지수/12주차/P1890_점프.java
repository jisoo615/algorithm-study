import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1890_점프 {
    public static long[][] arr, dp;
    public static long answer = 0;
    public static int N = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new long[N][N];
        dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(dp[i][j] == 0 || arr[i][j]==0) continue;
                long jump = arr[i][j];
                // +1 이 아니라 이전경로(dp[i][j]) 만큼 더해줘야함 주의!!
                if(i+jump <N) dp[(int)(i+jump)][j] += dp[i][j];
                if(j+jump <N) dp[i][(int)(j+jump)] += dp[i][j];
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}
