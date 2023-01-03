import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_11066 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int K = Integer.parseInt(br.readLine());
            int[][] dp = new int[K + 1][K + 1];
            int[] sum = new int[K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= K; j++) {
                sum[j] = sum[j - 1] + Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= K; i++) {
                for (int j = 1; j <= K - i; j++) {
                    dp[j][i + j] = Integer.MAX_VALUE;
                    for (int k = j; k < i + j; k++) {
                        dp[j][i + j] = Math.min(dp[j][i + j],
                                dp[j][k] + dp[k + 1][i + j] + sum[i + j] - sum[j - 1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.println(sb);
    }
}
