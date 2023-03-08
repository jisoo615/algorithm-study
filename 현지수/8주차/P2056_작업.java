import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2056_작업 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N+1];
        ArrayList<Integer>[] tasks = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            // [소요시간, 선행작업갯수, 선행작업번호들...] n번째 작업의 선행 작업은 모두 n보다 작은 숫자
            st = new StringTokenizer(br.readLine());
            tasks[i] = new ArrayList<>();
            tasks[i].add(Integer.parseInt(st.nextToken()));
            int t = Integer.parseInt(st.nextToken());
            for (int j = 0; j < t; j++) tasks[i].add(Integer.parseInt(st.nextToken()));
        }

        dp[1] = tasks[1].get(0);
        for (int i = 2; i <= N; i++) {
            int max = 0;
            for (int j = 1; j < tasks[i].size(); j++) {
                int before = tasks[i].get(j);// 선행 작업
                max = Math.max(max, dp[before]);// 선행작업중 제일 오래 걸리는 시간 구하기
            }
            dp[i] = tasks[i].get(0) + max;// 현재시간 + 젤 오래걸리는 시간
        }
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            answer = Math.max(answer, dp[i]);//  제일 오래걸리는 시간 찾기
        }
        System.out.println(answer);
    }
}
