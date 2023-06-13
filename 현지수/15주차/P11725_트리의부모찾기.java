import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11725_트리의부모찾기 {
    public static int[] answer;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        graph = new ArrayList[N+1];
        answer = new int[N+1];

        for (int i = 1; i < N+1; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[p].add(c);
            graph[c].add(p);// 트리 라기 보다 양방향 그래프
        }
//        dfs(-1, 1);
        dfs2(-1, 1, new boolean[N+1]);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < N+1; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
    static void dfs2(int parent, int x, boolean[] visited){
        if(visited[x]) return;// 여기서 미리 걸러주는게 시간 절약됨

        for(int child : graph[x]){
            if(visited[child]) continue;

            answer[child] = x;

            visited[x] = true;
            dfs2(x, child, visited);
            visited[x] = false;
        }
    }

    static void dfs(int parent, int x){
        for(int child : graph[x]){
            if(child==parent) continue;// 양방향이라 부모 노드도 같이 들어있음 거르기
            answer[child] = x;
            dfs(x, child);
        }
    }
}
