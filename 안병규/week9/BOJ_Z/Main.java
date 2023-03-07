package week9.BOJ_Z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        br.close();

        int N = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        System.out.println(dfs(N, c, r, 0));
    }
    
    private static int dfs(int N, int r, int c, int q) {
        
        if (N == 0) {
            return q;
        }
        int half = (int) Math.pow(2, N-1);
        int quad = 0;
        if (r < half) {
            if (c < half) {
                quad = 1;
            } else {
                quad = 2;
                c -= half;
            }
        } else {
            if (c < half) {
                quad = 3;
                r -= half;
            } else {
                quad = 4;
                r -= half;
                c -= half;
            }
        }
        q += (quad - 1) * (half * half);

        return dfs(N-1, r, c, q);
    }
}
