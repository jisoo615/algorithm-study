import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1487_물건팔기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int max = 0, answer = 0;
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (int[] a1, int[] a2)-> a1[0] - a2[0]);
        int cost = -1;
        for (int i = 0; i < N; i++) {
            if(cost < arr[i][0]) cost = arr[i][0];
            else continue;// 이전 비교한 가격과 같으면 또 구할필요 없음

            int sum = 0;
            for (int j = 0; j < N; j++) {
                if(arr[i][0] <= arr[j][0]){// 제시 금액이 더 클때만 팜
                    if( cost <= arr[j][1] ) continue;// 배송비 더 나가면 안팜
                    sum += cost - arr[j][1];
                }
            }
            if(max < sum){
                max = sum;
                answer = cost;
            }
        }

        System.out.println(answer);
    }
}
