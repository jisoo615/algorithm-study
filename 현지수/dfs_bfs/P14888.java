import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14888 {
    static int N;
    static int[] A;
    static int[] operators = new int[4];// operator = + - x /
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split(" ");
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arr[i]);
        }
        arr = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(arr[i]);
        }

        dfs(A[0], 1);

        System.out.println(max+"\n"+min);
    }

    static public void dfs(int sum, int index){
        if(index == N){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {// + - x /
            if(operators[i] > 0){
                operators[i]--;
                switch (i){
                    case 0 : dfs(sum + A[index], index+1);
                    break;
                    case 1 : dfs(sum - A[index], index+1);
                    break;
                    case 2 : dfs(sum * A[index], index+1);
                    break;
                    case 3 : dfs(sum / A[index], index+1);
                    break;
                }
                operators[i]++;
            }
        }
    }
}
