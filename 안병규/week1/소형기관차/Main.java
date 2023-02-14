package 소형기관차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] sum = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int max = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][n + 1];
        for (int i = 1; i < 4; i++) {
            for (int j = i * max; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - max] + (sum[j] - sum[j - max]));
            }
        }

        System.out.println(dp[3][n]);
    }
}
