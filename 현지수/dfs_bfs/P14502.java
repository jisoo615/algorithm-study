import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P14502 {// bfs
    static int N;
    static int M;
    static int[][] map;
    static int maxSafeZone = Integer.MIN_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Queue<Point> queue = new LinkedList<>();
    static ArrayList<Point> empties = new ArrayList<Point>();
    static ArrayList<Point> viruses = new ArrayList<Point>();

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.y = y; this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        M = Integer.parseInt(arr[1]);
        map = new int[N][M];
        // 0인 칸 에서 3개를 고르고, 바이러스를 퍼트려 남은 0칸이 가장 클 때
        // 최대 64 개중 3개 고름
        for (int i = 0; i < N; i++) {
            String[] sarr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(sarr[j]);
                if(map[i][j]==0) empties.add(new Point(i, j));
                else if(map[i][j]==2) viruses.add(new Point(i, j));
            }
        }

        selectWalls(0, 0);

        System.out.println(maxSafeZone);
    }

    public static void selectWalls(int cnt, int start){
        if(cnt == 3){
            maxSafeZone = Math.max(maxSafeZone, BFS());
            return;
        }
        for (int i = start; i < empties.size(); i++) {
            Point newWall = empties.get(i);
            map[newWall.x][newWall.y] = 1;
            selectWalls(cnt+1, i+1);
            map[newWall.x][newWall.y] = 0;
        }

    }

    static int BFS(){
        int virusCnt = 0;
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < viruses.size(); i++) {
            Point virus = viruses.get(i);
            int x = virus.x;
            int y = virus.y;
            queue.add(virus);

            while (!queue.isEmpty()){// 바이러스가 퍼짐
                Point current = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = current.x + dx[j];
                    int ny = current.y + dy[j];
                    if (nx < 0 || N-1 < nx || ny < 0 || M-1 < ny) continue;
                    if(map[nx][ny]!=0 || visited[nx][ny]) continue;
                    queue.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    virusCnt++;
                }
            }

        }
        int safeCnt = empties.size() - virusCnt - 3;
        return safeCnt;
    }
}
