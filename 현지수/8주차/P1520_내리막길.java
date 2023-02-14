import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1520_내리막길 {
    public static int[][] arr, dp;
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        dp = new int[N][M];
        // dp
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0,0));
    }

    static public int dfs(int x, int y){
        if(x==dp.length-1 && y==dp[0].length-1) return 1;// 도착점은 1
        if(dp[x][y]!=-1) return dp[x][y];// 간적있으면 기존값

        dp[x][y] = 0;// 간적 없으면 0으로 초기화, 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(!inBound(nx,ny)) continue;
            if(arr[x][y] <= arr[nx][ny]) continue;
            dp[x][y] += dfs(nx, ny);
        }
        return dp[x][y];
    }

    public static boolean inBound(int x, int y){
        if(x<0 || x>=arr.length || y<0 || y>=arr[0].length) return false;
        return true;
    }

}
