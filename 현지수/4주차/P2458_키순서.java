import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 키 순서
public class P2458_키순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int INF = 10000;// 최댓값
        int N = Integer.parseInt( st.nextToken() );// 정점 수
        int M = Integer.parseInt( st.nextToken() );// 간선 정보
        int from, to;

        int[][] graph = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            graph[from][to] = 1;
        }

// 출발 도착 경유 순으로 하니까 틀림.. 경유 출발 도착으로 하기
        for (int k = 1; k < N + 1; k++) {// 경유
            for (int i = 1; i < N + 1; i++) {// 출발
                for (int j = 1; j < N +1; j++) {// 도착
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
                }
            }
        }

        int answer = 0;
        // i - j가 연결됐는지 확인. 단방향이라 양쪽 다 확인해봐야됨. N-1개 라면 순서를 알 수 있는 경우
        for (int i = 1; i < N + 1; i++) {
            int linked = 0;
            for (int j = 1; j < N + 1; j++) {
                if( graph[i][j] != INF || graph[j][i] != INF ) linked++;
            }
            if(linked == N-1) answer++;
        }

        System.out.println(answer);
    }
}
