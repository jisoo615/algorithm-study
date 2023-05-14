import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2225_합분해 {
    public static void main(String[] args) throws IOException {
        // K개로 N을 만들 수 있는 경우의 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        long[][] dp = new long[K+1][N+1];

        Arrays.fill(dp[1], 1);// 1개로 n을 만들 수 있는 경우 = 1

        for (int i = 2; i <= K; i++) {// k개로
            dp[i][0] = 1;// 0을 만들 수 있는 경우는 항상 1
            for (int j = 1; j <= N; j++) {// 만들어야 하는 수
                dp[i][j] = ( dp[i][j-1] + dp[i-1][j] ) % 1000000000;
            }
        }
        System.out.println(dp[K][N]);
    }
}
