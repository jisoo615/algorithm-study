package 놀이공원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] times = new int[M];
        for (int i = 0; i < M; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        long answer = solution(N, M, times);

        System.out.println(answer);

    }

    private static long solution(long N, int M, int[] times) {
        if (N <= M) {
            return N;
        }

        long lastMinute = binarySearch(times, N, M);

        int answer = 0, sum = M;
        for (int i = 0; i < M; i++) {
            sum += (lastMinute - 1) / times[i];
        }

        for (int i = 0; i < M; i++) {
            if (lastMinute % times[i] == 0) {
                sum++;
            }

            if (sum == N) {
                answer = ++i;
                break;
            }
        }
        return answer;
    }

    private static long binarySearch(int[] times, long n, long m) {

        long left = 0L;
        long right = 2_000_000_000L * 30;

        while (left <= right) {

            long sum = m;
            long mid = (left + right) / 2;

            for (int time : times) {
                sum += mid / time;
            }

            if (sum < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
