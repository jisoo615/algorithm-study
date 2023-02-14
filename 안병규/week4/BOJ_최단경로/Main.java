package week4.BOJ_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E, start;
    static int[] d;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }

        d = new int[V + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        dijkstra();

        for (int i = 1; i <= V; i++) {
            if (d[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        }
    }

    private static void dijkstra() {
        d[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int distance = node.distance;

            if (d[now] < distance) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                Node n = graph.get(now).get(i);
                int cost = d[now] + n.distance;
                if (cost < d[n.index]) {
                    d[n.index] = cost;
                    pq.offer(new Node(n.index, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
