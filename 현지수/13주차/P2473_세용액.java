import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2473_세용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Long[] arr = new Long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        if(N<=3){
            System.out.println(arr[0]+" "+arr[1]+" "+arr[2]);
            return;
        }
        int[] answer = new int[3];//용액 인덱스를 담는 배열
        // 처음 한 개를 고정, 남은 범위에서 이분탐색해서 두용액+고정용액 합이 0과 가까운거 고르기
        Long sum_min = 3000000001L;
        for (int i = 0; i < N-2; i++) {
            int first = i, left = i+1, right = N-1;

            while(left < right){
                Long sum = arr[first]+arr[left]+arr[right];
                if(sum_min > (long)Math.abs( sum ) ){
                    answer[0] = first;
                    answer[1] = left;
                    answer[2] = right;
                    sum_min =(long) Math.abs(sum);
                }

                if(sum==0){
                    break;
                }
                else if( sum < 0){//높이기
                    left++;
                }else{// 낮추기
                    right--;
                }
            }

        }
        System.out.println(arr[answer[0]]+" "+arr[answer[1]]+" "+arr[answer[2]]);
    }
}
