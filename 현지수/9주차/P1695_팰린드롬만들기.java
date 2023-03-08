import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1695_팰린드롬만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] origin = new int[N+1];
        int[] backward = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(st.nextToken());
            origin[i] = x;
            backward[N-i+1] = x;
        }
        // 순열과 역순열의 최장공통수열의 길이 LCS 를 구한 후, N-공통수열길이 하면 답이 됨
        // LCS 는 dp를 사용, dp[i][j] : origin 0~i까지의 문자열과, backward 0~j까지의 문자열의 공통수열길이 의미
        int[][] dp = new int[N+1][N+1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if(origin[i] == backward[j]){
                    dp[i][j] = dp[i-1][j-1] +1;// i, j 분자열 전 i-1, j-1 문자열중 일치한 길이+1
                }else{
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);// 전에 비교한 것 중 큰 거 가져옴
                }
            }
        }

        System.out.println(N - dp[N][N]);
    }
}
