import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1446_지름길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());// 최대 12
        int D = Integer.parseInt(st.nextToken());// 최대 10000

        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (e > D) continue;// 도로보다 긴 경우 제외
            if (e - s < d) continue;// 더 걸리는 길 제외

            list.add(new int[]{s, e, d});
        }

        // 정렬 끝지점, 최소거리 오름차순
        list.sort((int[] o1, int[] o2)->{
            if(o1[1]==o2[1]) return o1[2] - o2[2];
            return o1[1]-o2[1];
        });

        int[] dp = new int[D+1];
        for (int i = 0; i < D + 1; i++) dp[i] = i;// 지름길 안갔을 때 거리 dp

        for (int[] arr : list) {
            int start = dp[arr[0]];
            int end = dp[arr[1]];
            int len = arr[2];

            if(start + len < end){// 갱신하는 경우
                // 뒷부분도 같이 바꿔줘야함 -> 끝나는게 더 빠른 것부터 정렬
                for (int i = arr[1]; i < D+1; i++) dp[i] = start + len++;
            }
        }

        System.out.println(dp[D]);
    }
}