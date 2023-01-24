import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// https://www.acmicpc.net/status?user_id=ske05058&problem_id=1753&from_mine=1
public class P1753_최단경로 {
    static class Node implements Comparable<Node> {
        int v, w;
        Node(int v, int w){this.v = v; this.w = w;}
        @Override
        public int compareTo(Node o) {// 오름차순
            return this.w - o.w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) graph[i] = new ArrayList();
// 그래프 만들기
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        int[] dist = new int[N+1];
        int INF = 3000000;
        Arrays.fill(dist, INF);
        // 다익스트라
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        dist[start] = 0;
        while (!pq.isEmpty()){
            Node n = pq.poll();

            for(Node linked : graph[n.v]) {
                int weight = n.w + linked.w;
                if(dist[linked.v] > weight){// 갱신
                    dist[linked.v] = weight;
                    pq.add(new Node(linked.v, weight));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N+1; i++) sb.append(dist[i]==INF ? "INF" : dist[i]).append("\n");
        System.out.println(sb.toString());
    }
}
