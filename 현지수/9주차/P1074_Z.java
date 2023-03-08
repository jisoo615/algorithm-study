import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1074_Z {
    public static int[] dx = {0, 0, 1, 1};// 제트모양 이동
    public static int[] dy = {0, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        // r,c가 어느 분면에 위치하는지 찾기
        int answer = dfs((int)Math.pow(2, N), R,C);
        System.out.println(answer);
    }
    // 2에 있으면 1/4을 한번 더해주고, 3에 있으면 1/4을 두번 더해주고, 4에 있으면 1/4을 세번 더해줌
    public static int dfs(int num, int r, int c){// num은 한변의 길이
        if(num==1) return 0;

        int half = num/2;
        
        if( r < half && c < half) return dfs(num/2, r, c);// 제트 순서 1
        if( r < half && c >= half) return half*half + dfs(num/2, r, c-half);// 2
        if( r >= half && c < half) return 2*half*half + dfs(num/2, r-half, c);// 3
        return 3*half*half + dfs(num/2, r-half, c-half);// 4
    }

}
