import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<ArrayList<Node>> tree;
    static boolean[] visited;
    static int max = 0;
    static int endNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            while (true) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) break;
                int e = Integer.parseInt(st.nextToken());
                tree.get(vertex).add(new Node(v, e));
            }
        }

        dfs(1, 0);

        visited = new boolean[V + 1];
        dfs(endNode, 0);

        System.out.println(max);

    }

    static void dfs(int vertex, int cost) {
        if (cost > max) {
            max = cost;
            endNode = vertex;
        }

        visited[vertex] = true;

        for (int i = 0; i < tree.get(vertex).size(); i++) {
            Node nd = tree.get(vertex).get(i);

            if (!visited[nd.vertex]) {
                dfs(nd.vertex, nd.edgeCost + cost);
            }
        }
    }

    static class Node {
        int vertex;
        int edgeCost;

        public Node(int vertex, int edgeCost) {
            this.vertex = vertex;
            this.edgeCost = edgeCost;
        }
    }
}


