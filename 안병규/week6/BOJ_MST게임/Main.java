package week6.BOJ_MST게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, i));
        }

        int idx = 0;
        for (int i = 0; i < K; i++) {

            nodes = new int[N+1];
            for (int j = 1; j <= N; j++) {
                nodes[j] = j;
            }

            int cnt = 0, sum = 0;
            PriorityQueue<Node> edge = new PriorityQueue<>(pq);
            for (int j = 0; j < idx; j++) {
                edge.poll();
            }

            while (!edge.isEmpty()) {
                Node node = edge.poll();
                if (find(node.a) != find(node.b)) {
                    cnt++;
                    sum += node.c;
                    union(node.a, node.b);
                }
            }
            idx++;
            if (cnt != N-1) System.out.print(0 + " ");
            else System.out.print(sum + " ");
        }
    }

    static int find(int n) {
        if (nodes[n] != n) {
            nodes[n] = find(nodes[n]);
        }
        return nodes[n];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) nodes[a] = b;
        else nodes[b] = a;
    }

    static class Node implements Comparable<Node> {
        int a;
        int b;
        int c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }
}
