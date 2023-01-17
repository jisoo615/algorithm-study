package 가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        int[] LIS = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = arr[0];
        int lisIndex = 0;
        for (int i = 1; i < n; i++) {

            if (LIS[lisIndex] < arr[i]) {
                LIS[++lisIndex] = arr[i];
            } else {
                int lowIdx = lower_bound(LIS, lisIndex, arr[i]);

                LIS[lowIdx] = arr[i];
            }
        }

        System.out.println(++lisIndex);
    }

    private static int lower_bound(int[] lis, int lisIndex, int val) {

        int start = 0, end = lisIndex;

        while (start < end) {

            int mid = (start + end) / 2;

            if (lis[mid] < val) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}
