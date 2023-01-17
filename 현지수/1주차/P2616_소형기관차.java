import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2616_소형기관차 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer( br.readLine() );
        int capacity = Integer.parseInt(br.readLine());
        int[] train=new int[n+1], sum = new int[n+1];
        int[][] dp = new int[3+1][n+1];
        int answer = 0;

        for (int i = 1; i < n+1; i++) {
            train[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + train[i];// 누적합
        }

        for (int i = 1; i < 4; i++) {// 소형기관차 1,2,3대
            for (int j = i * capacity; j < n + 1; j++) {
                dp[i][j] = Math.max(dp[i][j-1] , dp[i-1][j-capacity] + (sum[j]-sum[j-capacity]) );
            }
        }

        answer = dp[3][n];
        System.out.println(answer);
    }
}
