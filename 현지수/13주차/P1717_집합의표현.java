import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717_집합의표현 {
    public static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 자기 자신을 부모로 하는 초기 배열 생성
        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) parents[i] = i;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (command.equals("0")) {// 두개를 합쳐라
                union(a, b);
            } else {// 여부 판단
                a = find(a);
                b = find(b);
                sb.append(a==b? "YES":"NO").append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static int find(int x){
        if(parents[x]==x) return x;
        return parents[x] = find(parents[parents[x]]);
        // find 하면서 같은 집합은 같은 루트로 변경해줘야 시간초과 막을수있음
    }
    static void union(int a, int b){// 더 작은 수를 루트로, 루트를 변경해야 속한 전체 집합이 합쳐짐 주의
        a = find(a);
        b = find(b);
        if(a < b) parents[b] = a;
        else parents[a] = b;
    }
}
