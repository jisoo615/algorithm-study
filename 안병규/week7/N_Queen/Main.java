package week7.N_Queen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] board;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();
        board = new int[N];

        dfs(0);

        System.out.println(answer);
    }

    static void dfs(int n) {
        if (n == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[n] = i;

            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (board[j] == board[n]) {
                    flag = false;
                    break;
                }

                if (Math.abs(n - j) == Math.abs(board[n] - board[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                dfs(n + 1);
            }
        }
    }
}
