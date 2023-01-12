import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] stairScores = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            stairScores[i] = Integer.parseInt(br.readLine());
        }

        int[] maxScores = new int[n + 1];

        maxScores[1] = stairScores[1];

        if (n==1) {
            System.out.println(maxScores[n]);
            System.exit(0);
        }

        maxScores[2] = stairScores[1] + stairScores[2];

        for (int i = 3; i <= n; i++) {
            maxScores[i] = stairScores[i] + Math.max(stairScores[i - 1] + maxScores[i - 3], maxScores[i - 2]);
        }

        System.out.println(maxScores[n]);
    }
}
