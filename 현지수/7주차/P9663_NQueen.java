import javax.naming.Name;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P9663_NQueen {
    static public int N, answer;
    static public boolean[] slash, backSlash;
    static public int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // 가로 세로 대각선2방향
        arr = new int[N];// arr[col] = row col에 저장된 퀸의 row위치 정보
        // 각 두 점의 가로차이==새로차이 라면 대각선임(두점으로 만든 사각형이 정사각형이면 대각선)
        answer = 0;
        Arrays.fill(arr, -1);
        nqueen(0);

        System.out.println(answer);
    }

    public static void nqueen(int col){
        if(col==N){
            answer++;
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[col] = i;// col=0일때 0~7row에 넣는 경우 ok...
            if( isPossible(col) ){
                nqueen(col+1);// 다음 행에 놓기
            }
        }
        return;
    }

    public static boolean isPossible(int col){// arr[col] 의 row에 넣어도 되는지 판단
        int row = arr[col];
        for (int i = 0; i < col; i++) {
            if( arr[i]==row // 같은 row에 있으면
                    || Math.abs(col - i) == Math.abs(row - arr[i]) ){// 대각선인 점이 있으면
                return false;
            }
        }
        return true;
    }
}
