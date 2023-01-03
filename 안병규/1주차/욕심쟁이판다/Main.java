package 욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] X = {-1, 0, 1, 0};
    static int[] Y = {0, 1, 0, -1};
    static int[][] map;
    static int[][] dp;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        dp = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }
        System.out.println(answer);
    }

    public static int dfs(int x, int y) {

        if (dp[x][y] > 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int dx = x + X[i];
            int dy = y + Y[i];

            if (dx < 0 || dy < 0 || dx >= n || dy >= n) {
                continue;
            }

            if (map[dx][dy] > map[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(dx, dy) + 1);
            }

        }
        return dp[x][y];
    }
}
