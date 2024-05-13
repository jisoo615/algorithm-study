import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class P18352 {
    public static void main(String[] args) throws IOException {
        /*
        x 에서 모든 노드N 까지의 거리를 구하고 특정 k의 거리인 노드를 출력
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int N = Integer.parseInt(arr[0]);// 노드
        int M = Integer.parseInt(arr[1]);// 간선
        int K = Integer.parseInt(arr[2]);// 거리
        int X = Integer.parseInt(arr[3]);// 출발

        int[] dist = new int[N+1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <N+1; i++) {// 그래프 초기화 (2차원 배열 하니까 메모리 초과 -> 인접리스트로 변경)
            graph.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            String[] arr2 = br.readLine().split(" ");
            graph.get(Integer.parseInt(arr2[0])).add(Integer.parseInt(arr2[1]));
        }// 단방향 그래프를 받고

        // 최단거리는 bfs가 더 빠름 ... 동시에 한칸씩 가면 먼저 도착한 것이 최단거리를 달성하기때문
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        while(!queue.isEmpty()){
            int node = queue.poll();

            for (int i=0; i<graph.get(node).size(); i++){
                int next = graph.get(node).get(i);
                if(dist[next] == 0){//방문 안했으면 갱신
                    dist[next] = dist[node]+1;
                    queue.add(next);
                }
            }
        }

        int num = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < N+1; i++) {
            if(dist[i] == K && i!=X){// 출발지점은 빼기
                num++;
                sb.append(i+"\n");
            }
        }
        if(num==0) System.out.println("-1");
        else System.out.println(sb.toString());
    }
}
