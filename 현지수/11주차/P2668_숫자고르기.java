import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class P2668_숫자고르기 {
    public static int[] arr;
    public static int[] check;
    public static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        arr = new int[N+1];
        check = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            dfs( i );
            for (int j = 0; j < check.length; j++) {
                check[j] = check[j]==2 ? 2 : 0;
            }
        }
        // 시작 숫자와 마지막 숫자가 같으면 그 그래프는 사이클이 존재함 - 을 이용해서 사이클이 발생하는 숫자들을 찾자
        int cnt = 0;
        for (int i = 0; i < check.length; i++) {
            if(check[i]==2){
                sb.append(i).append("\n");
                cnt++;
            }
        }

        System.out.println(sb.insert(0, cnt+"\n").toString());
    }
    static void dfs(int node){
        if(check[node]==2) return;// 두번 방문하면 사이클
        check[node] += 1;
        dfs(arr[node]);
    }
}
