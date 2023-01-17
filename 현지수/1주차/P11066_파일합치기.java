import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11066_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int n=0; int[] arr, sum; int[][] dp;

        while (T>0){
            T--;
            n = Integer.parseInt(br.readLine());
            sum = new int[n+1];
            arr= new int[n]; dp = new int[n][n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum[i+1] = sum[i] + arr[i];// 누적합
            }
            // A B C DP1477_ExpressWay
            //A0 ' ' '
            //B  0 ' '
            //C    0 '
            //D      0
            for (int j = 1; j <n; j++) {
                for (int i = j-1; i >= 0; i--) {
                    int result = Integer.MAX_VALUE;
                    for (int k = 0; k < j-i; k++) {
                        result = Math.min(result, dp[i][i+k] + dp[i+k+1][j]);
                    }
                    dp[i][j] = result + sum[j+1]-sum[i];
                }
            }

            sb.append(dp[0][n-1]).append("\n");
        }

        System.out.println(sb.toString());
    }

}
