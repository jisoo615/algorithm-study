import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] X = {-1, 0, 1, 0};
    static int[] Y = {0, -1, 0, 1};
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int m;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int num = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j])
                    renameIsland(j, i, num++);
            }
        }

        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    makeBridge(j, i, map[i][j]);
                }
            }
        }

        int[] bridge = new int[num];
        for (int i = 0; i < bridge.length; i++) {
            bridge[i] = i;
        }
        int sumBridgeLength = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.nodeA;
            int to = edge.nodeB;

            if (DisjointSet.find(bridge, from) != DisjointSet.find(bridge, to)) {
                sumBridgeLength += edge.cost;
                DisjointSet.union(bridge, from, to);
            }
        }

        for (int i = 1; i < num; i++) {
            if (1 != DisjointSet.find(bridge, bridge[i])) {
                System.out.println(-1);
                return;
            }
        }

        if (sumBridgeLength == 0)
            System.out.println(-1);
        else
            System.out.println(sumBridgeLength);
    }

    private static void renameIsland(int x, int y, int num) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});

        map[y][x] = num;
        visited[y][x] = true;

        while (!q.isEmpty()) {

            int[] poll = q.poll();
            int pollX = poll[0];
            int pollY = poll[1];

            for (int i = 0; i < 4; i++) {

                int dx = pollX + X[i];
                int dy = pollY + Y[i];

                if (dx < 0 || dy < 0 || dx >= m || dy >= n) continue;

                if (map[dy][dx] == 1 && !visited[dy][dx]) {
                    map[dy][dx] = num;
                    visited[dy][dx] = true;
                    q.add(new int[]{dx, dy});
                }
            }
        }
    }

    private static void makeBridge(int x, int y, int islandNumber) {

        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[n][m];

        for (int i = 0; i < 4; i++) {
            q.add(new int[]{x, y, 0});
            visited[y][x] = true;
            while (!q.isEmpty()) {

                int[] poll = q.poll();
                int dx = poll[0] + X[i];
                int dy = poll[1] + Y[i];
                int len = poll[2];

                if (dx < 0 || dy < 0 || dx >= m || dy >= n) continue;

                if (map[dy][dx] == 0) {
                    visited[dy][dx] = true;
                    q.add(new int[]{dx, dy, ++len});
                } else if (map[dy][dx] != islandNumber && len >= 2) {
                    pq.add(new Edge(islandNumber, map[dy][dx], len));
                    break;
                }
            }
        }
    }

    static class DisjointSet {

        public static int find(int[] nodes, int node) {
            if (nodes[node] != node)
                return find(nodes, nodes[node]);
//                nodes[node] = find(nodes, nodes[node]);

            return nodes[node];
        }

        public static void union(int[] nodes, int a, int b) {
            a = find(nodes, a);
            b = find(nodes, b);

            if (a < b) nodes[b] = a;
            else nodes[a] = b;
        }
    }

    static class Edge implements Comparable<Edge> {
        int nodeA;
        int nodeB;
        int cost;

        public Edge(int nodeA, int nodeB, int cost) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
