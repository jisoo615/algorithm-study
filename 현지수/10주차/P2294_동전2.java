import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2294_동전2 {
    static public int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N+1];
        for (int i = 1; i <= N; i++) coins[i] = Integer.parseInt(br.readLine());
        // 조합으로 시간초과 -> dp

        //dp[i] = i돈을 만드는 데 필요한 동전 갯수
        int[] dp = new int[K+1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;// 초기값 !!!
        for (int i = 1; i <= N; i++) {
            for (int j = coins[i]; j <= K; j++) {// 동전보다 작은 경우 이전 값 고대로
                // 이전 값과 새로운 값 중에 작은 값 저장
                dp[j] = Math.min( dp[j], dp[j-coins[i]] + 1 );
            }
        }

        System.out.println(dp[K]==10001? -1 : dp[K]);
    }

}
