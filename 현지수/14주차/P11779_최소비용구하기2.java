import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P11779_최소비용구하기2 {
    public static int N, min=0, cnt=0, start, end;
    public static int[] distance;
    public static int[] prevNode;
    public static ArrayList<Node>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        distance = new int[N+1];
        prevNode = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) graph[i] = new ArrayList<Node>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));// 방향그래프!
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        // 다익스트라 - 시작 점에서 모든 노드 까지의 최소 거리를 구한다.
        dijkstra(start);

        StringBuilder answer = new StringBuilder();
        answer.append(distance[end]).append("\n");
        Stack routes = findRoute(start, end, new Stack<>());
        answer.append(routes.size()).append("\n");
        while (!routes.isEmpty()) answer.append(routes.pop()).append(" ");

        System.out.println(answer.toString());
    }
    static Stack<Integer> findRoute(int start, int end, Stack<Integer> stack){
        if(end==start){
            stack.push(end);
            return stack;
        }
        stack.push(end);
        return findRoute( start, prevNode[end], stack );
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()){
            Node node = pq.poll();
            if( distance[node.v] < node.w ) continue;// 현재 노드까지 거리비교도 해서 미리 걸러줘야 시간초과 안남
            if(node.v == end) break;// 도착점 도달하면 종료

            for(Node next : graph[node.v]){
                if(node.w + next.w >= distance[next.v]) continue;
                distance[next.v] = node.w + next.w;
                prevNode[next.v] = node.v;
                pq.add(new Node(next.v, distance[next.v]));
            }
        }
    }

    static class Node implements Comparable<Node>{
        int v, w;
        public Node(int v, int w){
            this.v = v; this.w = w;
        }
        @Override
        public int compareTo(Node o) {// 오름차순
            return this.w - o.w;
        }
    }
}
