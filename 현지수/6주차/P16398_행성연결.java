import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Spliterator;

// https://www.acmicpc.net/problem/16398
public class P16398_행성연결 {
    static class Edge{
        int u, v, w;
        public Edge(int u, int v, int w){this.u=u; this.v=v; this.w=w;}
    }

    public static int[] parent;
    public static void main(String[] args) throws IOException {
        long answer = 0;
//        int node_cnt = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] galaxy = new int[N][N];
        parent = new int[N+1];

        for (int i = 0; i < N; i++) {
            galaxy[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if(galaxy[i][j]==0) continue;
                pq.add(new Edge(i+1, j+1, galaxy[i][j]));
            }
        }

        for (int i = 0; i < N+1; i++) parent[i] = i;
        while (!pq.isEmpty()){
            Edge e = pq.poll();
            if( !union(e.u, e.v)) continue;// 이미 연결됏음 넘어가기
            answer += e.w;
//            node_cnt++;
//            if(node_cnt==N) break;
        }


        System.out.println(answer);
    }

    static public boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;// 이미 연결됐음 넘어감
        if(x > y){
            parent[x] = y;
        }else parent[y] = x;
        return true;
    }
    static public int find(int x){
        if(parent[x] == x) return x;
        return find(parent[x]);
    }
}
