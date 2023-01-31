import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13418
public class P13418_학교탐방하기_kruskal {
    static class Edge{
    int u, v, w;
    public Edge(int u, int v, int w){this.u=u; this.v=v; this.w=w;}
}
    public static int[] parents;
    public static void main(String[] args) throws IOException {
        int answer = 0;
        PriorityQueue<Edge> maxi = new PriorityQueue<>((e1, e2) -> e2.w - e1.w);// 내림차순
        PriorityQueue<Edge> mini = new PriorityQueue<>((e1, e2) -> e1.w - e2.w);// 오름차순

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];

        for (int i = 0; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt( st.nextToken());
            int v = Integer.parseInt( st.nextToken());
            int w = Integer.parseInt( st.nextToken())==1? 0:1;// 오르막0, 내리막1 이니까 저장할땐 1, 0 으로 저장
            maxi.add(new Edge(u, v, w));
            mini.add(new Edge(u, v, w));
        }
        for (int i = 0; i < N+1; i++) parents[i] = i;
        int max = (int) Math.pow(kruskal(maxi), 2);
        for (int i = 0; i < N+1; i++) parents[i] = i;
        int min = (int) Math.pow(kruskal(mini), 2);
        answer = max - min;
        System.out.println(answer);
    }

    static int kruskal(PriorityQueue<Edge> pq){
        int result = 0;
        while (!pq.isEmpty()){
            Edge e = pq.poll();
            if(!union(e.u, e.v)) continue;
            result += e.w;
        }
        return result;
    }
    static boolean union(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        if(x > y) parents[x] = y;
        else parents[y] = x;
        return true;
    }
    static int find(int x){
        if(parents[x]==x) return x;
        return find(parents[x]);
    }
}
