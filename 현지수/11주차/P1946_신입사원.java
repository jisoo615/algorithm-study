import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1946_신입사원 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int cnt = 0;
            int[][] arr = new int[N][2];
            for (int j = 0; j < N; j++) {// 등 수
                st = new StringTokenizer(br.readLine());
                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr, (a1, a2)-> a1[0] - a2[0]);
            int min = 100001;
            for (int j = 0; j < N; j++) {
                if( min > arr[j][1] ){
                    min = arr[j][1];
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb.toString());
    }
}
