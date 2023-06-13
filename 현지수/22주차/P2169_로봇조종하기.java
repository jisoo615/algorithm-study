import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2169
public class P2169_로봇조종하기 {
    // i. dp로 왼쪽, 위쪽 중에 큰걸 더하기 - 안됨
    // ii. 왼쪽으로 가는 걸 생각x - 하 좌 우 나눠 구하기
    public static int MIN = -100000001;// 1000*1000*100
    public static int N, M;
    public static int[][][] dp;
    public static int[][] map;
    public static int[] dx ={1, 0, 0}, dy={0, -1, 1};// 하 좌 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N+1][M+1][3];
        map = new int[N+1][M+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i+1][j+1] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i+1][j+1], MIN);// dp값 초기화, 방문여부 판단
            }
        }

        int answer = sol(1, 1, 0);
        System.out.println(answer);
    }

    static int sol(int x, int y, int dir){
        if(x==N && y==M) return map[x][y];// 도착점에 오면 종료

        if(dp[x][y][dir] != MIN) return dp[x][y][dir];// 이미 지나갔던 경우 dp값

        for (int i = 0; i < 3; i++) {// 하 좌 우 순서
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<1 || ny<1 || nx>N || ny>M) continue;
            if((dir==1 && i==2) || (dir==2 && i==1)) continue;// -> <- || <- ->로 가면 제자리
            dp[x][y][dir] = Math.max( dp[x][y][dir], sol(nx, ny, i) + map[x][y] );// 세 방향 중 큰 값을 선택
        }

        return dp[x][y][dir];
    }
}
