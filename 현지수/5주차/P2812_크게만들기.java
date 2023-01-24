import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 https://www.acmicpc.net/problem/2812
 */
public class P2812_크게만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        char[] arr = br.readLine().toCharArray();

        if(N==K){
            System.out.println("0");
            return;
        }

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < N; i++) {
            while ( !stack.isEmpty() && stack.peek() < arr[i] && K > 0) {
                stack.pop();
                K--;
            }
            stack.push(arr[i]);
        }

        /* 테스트케이스 내림차순일때
           7 3
           7654321
           output: 76543
         */
        while (K > 0){
            K--;
            stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        System.out.println(sb.reverse().toString());
    }

}
