package week8.BOJ_곱셈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long go = go(A, B, C);

        System.out.println(go);
    }

    static long go(long A, long B, long C) {

        if (B == 1) return A % C;

        long val = go(A, B/2, C);
        val = val * val % C;
        if (B%2==1) val = val * A % C;

        return val;
    }
}
