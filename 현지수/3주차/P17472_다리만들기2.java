import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17472
public class P17472_다리만들기2 {
    public static int N, M;
    public static int[][] map, min_dist;
    public static Queue<int[]> q;
    public static PriorityQueue<Node> bridgeList;
    public static boolean[][] visited;
    public static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1}, parents;

    static class Node implements Comparable<Node>{
        int from, to, len;
        public Node(int from, int to, int len){
            this.from =from; this.to = to; this.len = len;
        }
        @Override
        public int compareTo(Node o) {// 오름차순 - 거리가 작은것 부터니까
            return this.len - o.len;
        }// 길이 짧은것이 처음으로 오도록
    }

    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 나라 구분하기 bfs
        int number = 1;
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && !visited[i][j]){
                    divideCountry(i, j, number++);
                }
            }
        }

        // 각 나라끼리 최소거리 구하기 - 다리 찾기
//        bridgeList = new ArrayList<>();
        bridgeList = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j]>0) findBridge(i, j, map[i][j]);
            }
        }
        
        // 우선순위큐 -> 배열리스트 -> 다시 pq로 : pq사용시 poll사용하기
//        Collections.sort(bridgeList);

        // 부모를 찾아서 부모가 같으면 같은 것 중에 젤 작은 값인 간선 택하기
        parents = new int[number];
        for (int i = 1; i < number; i++) {
            parents[i] = i;// 부모 자기 자신으로 초기화
        }
        while(!bridgeList.isEmpty()){// 다리길이 짧은순
            Node n = bridgeList.poll();
            if(findRoot(n.from) != findRoot(n.to)){// 연결되지 않았다면
                union(n.from, n.to);// 둘 이어줌
                answer += n.len;
            }
        }

        // 부모가 다른지 검사
        int root = findRoot(1);
        for (int i = 1; i < parents.length; i++) {
            if(findRoot(i) != root){
                System.out.println("-1");
                return;
            }
        }

        System.out.println(answer);
    }

    static public int findRoot(int v){// 최종 부모를 찾음
        if(parents[v] == v) return v;
        return findRoot(parents[v]);
    }
    static public void union(int x, int y){// 두 정점을 이어줌, 숫자가 작을수록 루트임
        x = findRoot(x);
        y = findRoot(y);
        if(x < y) parents[y] = x;
        else parents[x] = y;
    }

    static public void findBridge(int x, int y, int vertax){
        q = new LinkedList<>();
        visited = new boolean[N][M];

        for (int i = 0; i < 4; i++) {// 4방향 직진으로 탐색
            q.add(new int[] {x, y, 0});// x, y, 건설중이 다리 길이
            visited[x][y] = true;

            while (!q.isEmpty()){
                int[] point = q.poll();
                int nx = point[0]+dx[i], ny = point[1]+dy[i], len = point[2];

                if(nx <0 || nx >= N || ny <0 || ny >= M || visited[nx][ny]) continue;
                if(map[nx][ny] != vertax) {
                    if (map[nx][ny] != 0) {// 다른 섬에 도착했을 때 다리 완성
                        if (len >= 2) {
                            bridgeList.add(new Node(vertax, map[nx][ny], len));
                            break;
                        }
                    } else {// 바다 일때 다리 길이 늘려가기
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, len + 1});
                    }
                }
            }
            q.clear();
        }
    }

    static public void divideCountry(int x, int y, int number) {// bfs
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x,y});
        visited[x][y] = true;
        map[x][y] = number;

        while (!q.isEmpty()){
            int[] point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];
                if(nx >= map.length || nx <0 || ny >= map[0].length || ny <0 || visited[nx][ny]) continue;
                if(map[nx][ny]==1){
                    visited[nx][ny] = true;
                    map[nx][ny] = number;
                    q.add(new int[] {nx, ny});
                }
            }
        }

    }

}