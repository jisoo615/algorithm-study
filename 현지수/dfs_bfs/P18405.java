import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class P18405 {
    static int N, K, S, X, Y;
    static int[][] map;
    static ArrayList<Point> viruses = new ArrayList<Point>();

    static class Point implements Comparable<Point>{
        int x, y, num;
        public Point(int x, int y, int num){
            this.x = x; this.y = y; this.num = num;
        }

        @Override
        public int compareTo(Point o) {// 작은게 우선
            if(this.num < o.num) return -1; //
            else if(this.num > o.num) return 1;
            else return 0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 작은 숫자K의 바이러스 부터 증식 -> 정렬 후 순서대로 bfs 증식 시켜주기
        String[] arr = br.readLine().split(" ");
        N = Integer.parseInt(arr[0]);
        K = Integer.parseInt(arr[1]);
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            arr = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(arr[j]);
                if(map[i][j] != 0) viruses.add(new Point(i, j, map[i][j]));
            }
        }
        arr = br.readLine().split(" ");
        S = Integer.parseInt(arr[0]);
        X = Integer.parseInt(arr[1]);
        Y = Integer.parseInt(arr[2]);

        for (int i = 0; i < S; i++) {
            BFS();
        }

        System.out.println(map[X-1][Y-1]);
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static public void BFS(){
        Collections.sort(viruses);

        int size = viruses.size();
        for (int i = 0; i < size; i++) {
            Point v = viruses.remove(0);
            for (int j = 0; j < 4; j++) {
                int nx = v.x + dx[j];
                int ny = v.y + dy[j];
                if(nx<0 || nx >= N || ny<0 || ny>=N) continue;
                if(map[nx][ny]>0) continue;
                map[nx][ny] = v.num;
                viruses.add(new Point(nx, ny, v.num));
            }
        }
    }
}
