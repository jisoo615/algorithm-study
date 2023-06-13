import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P16967_배열복원하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H,W,X,Y;
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int[][] b = new int[H+X][W+Y];
        for (int i = 0; i < b.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < b[0].length; j++){
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 아랫부분에 윗부분을 빼면 됨
        for (int i = 0; i < H - X; i++) {// 안겹치는 부분
            for (int j = 0; j < W - Y; j++) {
                b[i+X][j+Y] -= b[i][j];// 겹친 부분 - 원래 수
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(b[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
