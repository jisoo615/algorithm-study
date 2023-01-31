import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.ECField;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P16202_MST게임 {
    static class Edge{
    int u, v, w;
    public Edge(int u, int v, int w){this.u=u; this.v=v; this.w=w;}
}

    public static int[] parent;
    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2)-> e1.w - e2.w);// 전체 간선 - 하나씩 빼는 용
        PriorityQueue<Edge> pq;// mst 구하기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), w = i+1;
            edges.add(new Edge(u, v, w));
        }

        StringBuilder sb = new StringBuilder();
        parent = new int[N+1];
        for (int j = 0; j < K; j++) {
            // 초기화
            int result = 0;
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            pq = new PriorityQueue<>(edges);
            // kruskal
            while (!pq.isEmpty()){
                Edge e = pq.poll();
                if(!union(e.u, e.v)) continue;
                result += e.w;
            }
            sb.append(isDisconnected()==false? 0 : result).append(" ");
            edges.poll();
        }
        System.out.println(sb.toString());
    }

    static boolean isDisconnected(){
        int root = find(1);
        for (int i = 1; i < parent.length; i++) {
            if(find(parent[i])!=root) return false;
        }
        return true;
    }

    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        if(x > y) parent[x] = y;
        else parent[y] = x;
        return true;
    }
    static int find(int x){
        if(parent[x]==x) return x;
        return find(parent[x]);
    }

}
