import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P17135_캐슬디펜스 {
    public static ArrayList<int[]> list = new ArrayList<>();
    public static int N, M, D, max=0;
    public static int[][] arr;
    public static int[] dx = {0, -1, 0};// 왼, 위, 오른
    public static int[] dy = {-1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M];// arr[N] = 성과 궁수가 있는 곳.
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 조합 MC3 (조합=순서 영향x, 순열x)
        comb(0, new int[3], -1);
        System.out.println(max);
    }

    public static void comb(int depth, int[] set, int preindex){
        if(depth==3){
            int[][] board = new int[N+1][M];
            for (int i = 0; i <= N; i++) {// 이중 배열은 클론이 깊은복사 안됨
                for (int j = 0; j < M; j++) {
                    board[i][j] = arr[i][j];
                }
            }
            max = Math.max(max, find(set, board));
            return;
        }
        for (int i = preindex+1; i < M; i++) {// preindex 보다 작은 수는 이미 썼던 조합임
            set[depth] = i;
            comb(depth+1, set, i);
            set[depth] = -1;
        }
    }

    public static int find(int[] position, int[][] board){
        int x, y, cnt=0;
        for (int i = N; i > 0; i--) {
            ArrayList<Enemy> killed = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                x = i;
                y = position[j];

                PriorityQueue<Enemy> pq = new PriorityQueue();
                Queue<Point> q = new LinkedList<>();
                boolean[][] visited = new boolean[N+1][M];
                q.add(new Point(x, y, 0));
                while (!q.isEmpty()){
                    Point p = q.poll();

                    if(p.depth == D) continue;
                    for (int k = 0; k < 3; k++) {
                        int nx = p.x + dx[k];
                        int ny = p.y + dy[k];
                        if(nx<0 || nx>=x || ny<0 || ny>= M) continue;// 궁수x위치 보다 위까지만 탐색
                        if(visited[nx][ny]) continue;
                        visited[nx][ny] = true;

                        if(board[nx][ny] == 1) pq.add(new Enemy(nx, ny, p.depth));
                        else q.add(new Point(nx, ny, p.depth+1));
                    }
                }// -------bfs
                if(pq.size()==0) continue;
                killed.add(pq.poll());
            }

            for(Enemy enemy : killed){
                if(board[enemy.x][enemy.y]==1){
                    board[enemy.x][enemy.y]=0;
                    cnt++;
                }
            }

        }

        return cnt;
    }
    static class Point{
        int x, y, depth;
        public Point(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    static class Enemy implements Comparable<Enemy>{
        int x,y,dist;
        public Enemy(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        @Override
        public int compareTo(Enemy o) {// 가까울 수록, 왼쪽 일 수록 먼저(y가 작을수록)
            if(this.dist == o.dist){
                return this.y - o.y;
            }
            return this.dist - o.dist;
        }
    }

}
