package 계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] DP = new int[N + 1];

        DP[1] = arr[1];
        if (N > 1) DP[2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            DP[i] = Math.max(DP[i - 3] + arr[i - 1], DP[i - 2]) + arr[i];
        }

        System.out.println(DP[N]);
    }
}
