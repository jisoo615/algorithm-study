import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P18418 {
    static int N;
    static int[][] map;
    static String answer = "NO";
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
// 선생님 1, 학생 2, 장애물 -1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                String str = st.nextToken();
                if(str.equals("T")) map[i][j] = 1;
                else if(str.equals("S")) map[i][j] = 2;
            }
        }
        DFS(0, -1, 0);
        System.out.println(answer);
    }

    static public boolean findStudent(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while(true){
                nx += dx[i];
                ny += dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N) break;
                if(map[nx][ny] == -1) break;
                if(map[nx][ny] == 2) return true;
            }
        }
        return false;
    }

    static public void DFS(int row, int col, int depth){// blanks에서 3개 뽑기
        if(depth==3){
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] == 1){
                        if(findStudent(i, j)){
                            return;
                        }
                    }
                }
            }
            answer = "YES";
            return;
        }
        // 장애물 -1 배치
        for (int i = col+1; i < N; i++) {// 같은 행의 다음 열
            if(map[row][i] != 0) continue;
            map[row][i] = -1;
            DFS(row, i, depth+1);
            map[row][i] = 0;
        }
        for (int i = row+1; i < N; i++) {// 다음 행의 모든 열
            for (int j = 0; j < N; j++) {
                if(map[i][j] != 0) continue;
                map[i][j] = -1;
                DFS(i, j, depth+1);
                map[i][j] = 0;
            }
        }
    }

}
