import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 서강그라운드
public class P14938_서강그라운드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken())
        , m = Integer.parseInt(st.nextToken())// 수색범위
        , r = Integer.parseInt(st.nextToken())
        , INF = 30*15*2+1;

        st = new StringTokenizer(br.readLine());
        int[][] graph = new int[n+1][n+1];
        int[] items = new int[n+1];

        for (int i = 1; i < n + 1; i++) {
            Arrays.fill(graph[i], INF);
            items[i] = Integer.parseInt(st.nextToken());
        }

        int a, b, l;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            graph[a][b] = l;
            graph[b][a] = l;
        }

        for (int k = 1; k <n+1; k++) {// 경유
            for (int i = 1; i < n + 1; i++) {// 출발
                for (int j = 1; j < n + 1; j++) {// 도착
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }

        // 각 정점 기준으로 거리가 m 이하인 경로의, 정점의 아이템 갯수의 합, 중 제일 큰거
        int cnt=0, answer=0;
        boolean[] visited = new boolean[n+1];
        for (int i = 1; i < n + 1; i++) {// 기준 정점(낙하지역)
            cnt = items[i];
            visited[i] = true;
            for (int j = 1; j < n + 1; j++) {
                if(visited[j]) continue;
                if(graph[i][j] <= m) cnt += items[j];
            }
            answer = Math.max(answer, cnt);
            Arrays.fill(visited, false);
        }

        System.out.println(answer);
    }
}
