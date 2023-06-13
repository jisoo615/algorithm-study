import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976_여행가자 {
    public static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        parents = new int[N+1];
        for (int i = 0; i < N+1; i++) {
            parents[i] = i;
        }
        // 차피 양방향
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) {// i < j i에서 j로
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N+1; j++) {
                if(st.nextToken().equals("0")) continue;
                union(i, j);
            }
        }
        st = new StringTokenizer(br.readLine());
        int root = find( Integer.parseInt(st.nextToken()) );
        for (int i = 1; i < M; i++) {
            if( root == find( Integer.parseInt(st.nextToken()) ) ) continue;
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }

    static int find(int x){
        if(parents[x]==x) return x;
        return parents[x] = find(parents[x]);
    }
    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b) parents[b] = a;
        else parents[a] = b;
    }
}
