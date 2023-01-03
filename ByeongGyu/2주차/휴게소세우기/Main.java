package 휴게소세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[] rest = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            rest[i] = Integer.parseInt(st.nextToken());
        }
        rest[N+1] = L;

        Arrays.sort(rest);

        int answer = binarySearch(M, L, rest);
        System.out.println(answer);
    }

    private static int binarySearch(int M, int L, int[] rest) {

        int start = 1, end = L;

        while (start <= end) {
            int sum = 0;
            int mid = (start + end) / 2;

            for (int i = 1; i < rest.length; i++) {
                sum += (rest[i] - rest[i - 1] - 1) / mid;
            }

            if (sum > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}