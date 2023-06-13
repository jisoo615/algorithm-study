import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16437_양구출작전 {
    public static Long[] sheeps;
    public static boolean[] visited;
    public static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        sheeps = new Long[N+1];
        graph = new ArrayList[N+1];
        sheeps[1] = 0L;
        for (int i = 1; i < N+1; i++) graph[i] = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            if ( st.nextToken().equals("W") ){
                sheeps[i] = Long.parseLong(st.nextToken()) * -1;
            }else{ sheeps[i] = Long.parseLong(st.nextToken()); }

            graph[ Integer.parseInt( st.nextToken() ) ].add( i );
        }

        Long answer = dfs(1, (long)0, false);
        System.out.println(answer);
    }

    static Long dfs(int x, Long sheep, boolean leafNode){
        Long pre = sheep;
        leafNode = true;

        for(int next : graph[x]){
            leafNode = false;
            sheep += dfs(next, pre, leafNode);
        }
        
        if(leafNode && sheeps[x]<0) return sheep;// 말단노드가 늑대일때
        else if(sheep + sheeps[x] < 0) return 0L;// 양 < 늑대 일때
        else return sheeps[x] + sheep;
    }
}
