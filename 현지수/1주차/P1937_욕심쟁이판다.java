import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1937_욕심쟁이판다 {
    static int[][] dp, arr;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            int j = 0;
            for(String s : br.readLine().split(" ")){
                arr[i][j] = Integer.parseInt(s);
                j++;
            }
        }
        //--------입력-------------

        // dp + dfs
        int answer=0;
        for(int i=0; i<n; i++){
            for (int j = 0; j < n; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer);
    }

    public static int dfs(int x, int y){
        if(dp[x][y]!=0) return dp[x][y];// 전에 구했던 케이스면 dp반환

        dp[x][y] = 1; // 기본 1년
        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if(nx >= n || nx <0 || ny>=n || ny<0) continue;
            if(arr[nx][ny] > arr[x][y]){// 다음이 더 클떄
                dp[x][y] =Math.max(dp[x][y], dfs(nx, ny)+1);// 기존, 새로운길 중 큰 값으로 지정
            }
        }

        return dp[x][y];
    }
}
