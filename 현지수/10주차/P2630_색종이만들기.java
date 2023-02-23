import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2630_색종이만들기 {
    public static int[][] matrix;
    public static int[] answer = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        recur(0, 0, N);
        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]).append("\n").append(answer[1]);
        System.out.println(sb.toString());;
    }

    static public void recur(int x, int y, int len){// 한변의 길이
        int half = len/2;

        int C = isAllSame(x, y, len);
        if(C >= 0){
            answer[C]++;
            return;
        }

        recur(x, y, half);
        recur(x, y+half, half);
        recur(x+half, y, half);
        recur(x+half, y+half, half);
    }

    static public int isAllSame(int x, int y, int len){
        int color = matrix[x][y];
        for (int i = x; i < x+len; i++) {
            for (int j = y; j < y+len; j++) {
                if(color != matrix[i][j]) return -1;
            }
        }
        return color;
    }
}
