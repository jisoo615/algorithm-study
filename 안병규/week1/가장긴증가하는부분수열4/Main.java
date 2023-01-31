package 가장긴증가하는부분수열4;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int[] dp = new int[n + 1];
        int max = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        bw.write(max + "\n");

        Stack<Integer> nums = new Stack<>();
        for (int i = n; i > 0; i--) {
            if (max == dp[i]) {
                nums.push(arr[i]);
                max--;
            }
        }

        while (!nums.empty()) {
            bw.write(nums.pop() + " ");
        }

        bw.flush();
        bw.close();
    }
}
