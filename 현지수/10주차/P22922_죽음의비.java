import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P22922_죽음의비 {
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static int[][] visited;// i,j 에 있을때의 체력
    public static char[][] arr;
    public static int N, D, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        arr = new char[N][N];
        visited = new int[N][N];
        Position start = new Position();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j]=='S') start = new Position(i, j, H, 0, 0);
            }
        }
        int answer = bfs(start);
        System.out.println(answer);
    }

    public static int bfs(Position start){
        Queue<Position> q = new LinkedList<>();
        visited[start.x][start.y] = start.health;
        q.add(start);

        while (!q.isEmpty()){
            Position now = q.poll();

            if(arr[now.x][now.y]=='E'){// 안전지대 도달했을때
                return now.move;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx < 0 || nx >= N || ny<0 || ny>=N) continue;
                Position next = new Position();

                next.health = now.health;
                next.umbrella = now.umbrella;
                if(arr[nx][ny]=='U'){// 우산을 만나면 (U에서도 비내림)
                    next.umbrella = D;
                    next.umbrella--;
                }
                else if(arr[nx][ny]=='.'){// 비를 맞음
                    if(next.umbrella>0) next.umbrella--;
                    else next.health--;
                }
                if(next.health == 0) continue;// 체력 0이면 죽음
                // 전에 자나간 경로의 체력보다 많이 있을 때만 지나갈 수 있음
                if(visited[nx][ny] >= next.health + next.umbrella) continue;
                visited[nx][ny] = next.health + next.umbrella;

                next.move = now.move+1;
                next.x = nx;
                next.y = ny;
                q.add(next);
            }
        }
        return -1;
    }

    static class Position{
        int x, y, health, umbrella, move;
        public Position(int x, int y, int health, int umbrella, int move){
            this.x = x; this.y = y; this.health = health; this.umbrella = umbrella; this.move = move;
        }
        public Position(){};
    }
}