import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2933_미네랄 {
    public static char[][] map;
    public static boolean[][] visited, floatingCheck;
    public static int[] dx = {1, 0, 0, -1};// 하 좌 우 상
    public static int[] dy = {0, -1, 1, 0};
    public static ArrayList<Point> floatings;
    public static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 번갈아 막대 던지기
            if(i%2==0) leftAttack(R - Integer.parseInt(st.nextToken()));
            else rightAttack(R - Integer.parseInt(st.nextToken()));

            // 공중에 뜬 클러스터 구하기, bfs
            floatings = new ArrayList<>();
            findFloatings();

            // 찾은거 내려주기(이전 모형 그대로)
            if(floatings.size()==0) continue;
            goDown();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append( map[i][j] );
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void findFloatings(){
        Queue<Point> q = new LinkedList<>();
        visited = new boolean[R][C];
        floatingCheck = new boolean[R][C];
        for (int i = 0; i < C; i++) {// 바닥과 붙은곳을 시작점으로 bfs
            if(map[R-1][i]=='x'){
                visited[R-1][i] = true;
                q.add(new Point(R-1, i));
            }
        }
        // 바닥과 연결된 클러스터는 visited가 true로 구분
        while (!q.isEmpty()){
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if(nx<0 || nx>=R || ny<0 || ny>= C) continue;
                if(map[nx][ny]=='.') continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new Point(nx, ny));
            }
        }
        // 떠있는것 구하기
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(visited[i][j]==false && map[i][j]=='x'){
                    floatings.add(new Point(i, j));
                    floatingCheck[i][j] = true;
                }
            }
        }
    }

    static void goDown(){
        // 내릴 수 있는 최솟값 구하기
        // 아래가 floatings에 있는거면 넘어감
        int min = Integer.MAX_VALUE;
        for(Point p : floatings){
            int dist = 0;
            for (int i = p.x+1; i < R; i++) {
                if(map[i][p.y]=='x'){
                    if(floatingCheck[i][p.y]){
                        dist = Integer.MAX_VALUE;
                        break;
                    }
                    else{// 땅에 붙은 클러스터에 부딧혔을때
                        break;
                    }
                }else{// 더 갈 수 있을때
                    dist++;
                }
            }

            min = Math.min(min, dist);
        }

        floatings.sort((Point p1, Point p2)->{return p2.x - p1.x;});// 아래부터 밑으로 땡겨야 하니까 x내림차순
        for (int i = 0; i < floatings.size(); i++) {
            Point p = floatings.get(i);
            map[p.x][p.y] = '.';
            map[p.x+min][p.y] = 'x';
        }

    }

    static void leftAttack(int height){
        for (int i = 0; i < C; i++) {
            if(map[height][i] == 'x'){
                map[height][i] = '.';
                return;
            }
        }
    }
    static void rightAttack(int height){
        for (int i = C-1; i >=0; i--) {
            if(map[height][i] == 'x'){
                map[height][i] = '.';
                return;
            }
        }
    }
}
