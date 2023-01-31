import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P13418_학교탐방하기_prim {// 정점 순서대로 돌면서 순간 순간 최소 간선 선택
    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static int[] parents;
    public static int N;
    public static ArrayList<ArrayList<Edge>> edgeList;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        PriorityQueue<Edge> maxi = new PriorityQueue<>((e1, e2) -> e2.w - e1.w);// 내림차순
        PriorityQueue<Edge> mini = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);// 오름차순

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        edgeList = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            edgeList.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken()) == 1 ? 0 : 1;// 오르막0, 내리막1 이니까 저장할땐 1, 0 으로 저장
            edgeList.get(u).add(new Edge(u, v, w));
            edgeList.get(v).add(new Edge(v, u, w));
        }
        int max = prim(maxi);
        int min = prim(mini);
        System.out.println(max*max - min*min);
    }

    public static int prim(PriorityQueue<Edge> pq){
        int result = 0;
        boolean[] visited = new boolean[N+1];
        visited[0] = true;
        pq.add(edgeList.get(0).get(0));

        while (!pq.isEmpty()){
            Edge e = pq.poll();
            if(visited[e.v]) continue;
            result += e.w;
            visited[e.v] = true;
            for(Edge edge : edgeList.get(e.v)){
                pq.add(edge);
            }
        }

        return result;
    }
}
