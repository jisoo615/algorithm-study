import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P13913_숨바꼭질4 {
    static public boolean[] visited = new boolean[100001];
    static public int[] trace= new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int cnt = 0;

        bfs(N, K, 1);

        StringBuilder sb = new StringBuilder();
        int start = K;
        // K에서 N으로 거꾸로 따라감
        while (start!=N) {
            sb.insert(0, start+" ");
            start = trace[start];
            cnt++;
        }
        sb.insert(0, N+" ").insert(0, cnt+"\n");
        System.out.println(sb.toString());
    }
    // N이 k가 되어야 함
    public static void bfs(int N, int K, int depth){
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        visited[N] = true;

        while (!q.isEmpty()){
            int X = q.poll();
            if(X==K) continue;

            int[] arr = {X+1, X-1, X*2};
            for (int i = 0; i < 3; i++) {
                int next = arr[i];
                if( next>100000 || next<0 ) continue;
                //아직 안 방문한 것만 탐색해도 되는 이유 :
                //bfs면 동일 depth를 한번에 돌고 다음 depth+1로 넘어가서, 어차피 먼저 저장된 것이 더 빠른 경로임
                if(visited[next]) continue;
                visited[next]=true;

                trace[next] = X;
                q.add(next);
            }
        }
    }

}
