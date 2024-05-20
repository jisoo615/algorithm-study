import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P18418 {
    static int N;
    static int[][] hallway;
    static ArrayList<Point> blanks = new ArrayList<Point>();
    static ArrayList<Point> teachers = new ArrayList<Point>();
    static String answer = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hallway = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                String str = st.nextToken();
                if(str.equals("T")){
                    hallway[i][j] = 1;
                    teachers.add(new Point(i, j));
                }
                else if(str.equals("S")) hallway[i][j] = 2;
                else blanks.add(new Point(i, j));
            }
        }
        DFS(0, 0);
        System.out.println(answer);
    }

    static public boolean verify(){
        for (int i = 0; i < teachers.size(); i++) {
            Point teacher = teachers.get(i);

            for (int j = teacher.y; j >=0; j--) {// 선생님의 4 방향 에서 s를 볼 수 있는가
                if(hallway[teacher.x][j] == 2) return false;
            }
            for (int j = teacher.y; j < N; j++) {
                if(hallway[teacher.x][j] == 2) return false;
            }
            for (int j = teacher.x; j >=0; j--) {
                if(hallway[j][teacher.y] == 2) return false;
            }
            for (int j = teacher.x; j < N; j++) {
                if(hallway[j][teacher.y] == 2) return false;
            }
        }
        return true;
    }

    static public void DFS(int startIdx, int depth){// blanks에서 3개 뽑기
        if(depth==3){
            if(verify()){
                answer = "YES";
                return;
            }
        }
        Point newBarrier = blanks.get(startIdx);
        hallway[newBarrier.x][newBarrier.y] = -1;
        for (int i = startIdx+1; i < N; i++) {
            DFS(i, depth+1);
        }
        hallway[newBarrier.x][newBarrier.y] = 0;
    }

    static class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x; this.y = y;
        }
    }

}
