import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 * 둘째 줄에는 가장 긴 증가하는 부분 수열을 출력한다. 그러한 수열이 여러가지인 경우 아무거나 출력한다.
 */
public class P14002_가장증가하는부분수열4 {
    static int A[], dp[], v[];//dp[x] = x를 포함하는 최대 증가수열 길이

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new int[n];
        dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int max = dynamic(0, n);
        sb.append(max).append("\n");

        Stack<Integer> stack = new Stack<>();
        for(int i =n-1; i>=0; i--) {

            if( dp[i] == max ) {
                stack.push(A[i]);
                max -= 1;
            }
        }
        while(!stack.isEmpty()) sb.append(stack.pop()).append(" ");

        System.out.println(sb);


    }

    static int dynamic(int start, int end) {//O(n^2) 방법
        dp[0] = 1;
        int max = dp[0];//기존 max를 dp[0]으로 해놔야 하는 이유= 배열 크기가 1일때 최대길이 1을 반환하기 위해서

        for(int i =1; i<end; i++) {//2~n
            dp[i] = 1;//=Arrays.fill(dp, 1)로 초반에 초기화 하는 것과 같음(왜냐 숫자 하나만 있어도 길이가 1이니까)
            for(int j =0; j<i; j++) {//1~i-1
                if(A[j] < A[i]) {
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

}
