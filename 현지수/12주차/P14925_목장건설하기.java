import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14925_목장건설하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] farm = new int[M+1][N+1];
        int[][] dp = new int[M+1][N+1];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                farm[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if(farm[i][j]>0) continue;
                dp[i][j] = Math.min( dp[i][j-1],
                        Math.min (dp[i-1][j-1], dp[i-1][j])) +1;
                answer = Math.max(answer, dp[i][j]);
            }
        }
        System.out.println(answer);
    }
}
