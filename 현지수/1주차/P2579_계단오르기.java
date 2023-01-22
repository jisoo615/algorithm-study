import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2579_계단오르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if(n==1){
            System.out.println(arr[0]);
            return;
        }else if(n==2){
            System.out.println(arr[0]+arr[1]);
            return;
        }

        int dp[] = new int[n];
        dp[0] = arr[0];// 첫계단
        dp[1] = arr[0] + arr[1];// 두번째 계단
        dp[2] = Math.max(dp[0]+arr[2], arr[1]+arr[2]);// 세번째 계단
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max( dp[i-3]+arr[i-1]+arr[i], dp[i-2]+arr[i]);//0,  2,3   / 1, 3
        }


        System.out.println(dp[n-1]);
    }
}
