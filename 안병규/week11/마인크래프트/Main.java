package week11.마인크래프트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        /*
            1. 블럭 높이의 최소값, 최대값을 구한다.
            2. 최소 블럭값부터 최대 블럭값까지 순회하며 최소 시간을 구한다.
                2-1. 순회하는 블럭의 값은 현재 위치까지 채우거나 없애야하는 기준이다.
                2-2. 순회하는 범위만큼 로직을 실행하면서 최소 시간을 계속해서 초기화한다.
         */

        int[][] ground = new int[N][M];
        int min = 256, max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int height = Integer.parseInt(st.nextToken());
                ground[i][j] = height;
                // 최소 값과 최대 값을 구한다.
                if (min > height) {
                    min = height;
                }
                if (max < height) {
                    max = height;
                }
            }
        }

        int mt = Integer.MAX_VALUE;
        int block = 0;
        for (int i = max; i >= min; i--) {
            int t = 0;
            int b = B;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    int diff = ground[j][k] - i;
                    if (diff > 0) {
                        t += diff * 2;
                        b += diff;
                    } else if (diff < 0) {
                        t += Math.abs(diff);
                        b += diff;
                    }
                }
            }
            if (b >= 0 && mt > t) {
                mt = t;
                block = i;
            }
        }

        System.out.println(mt + " " + block);

        br.close();
    }
}
