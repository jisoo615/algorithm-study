import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1629_곱셈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long answer = pow(A, B, C);
        System.out.println(answer);
    }

    static long pow(long A, long B, long C){
        if(B==1) return A%C;

        long temp = pow(A, B/2, C);

        if(B%2==1){// B가 홀수일때
            return (temp * temp %C) *A %C;
        }
        return (temp * temp %C);
    }
}
