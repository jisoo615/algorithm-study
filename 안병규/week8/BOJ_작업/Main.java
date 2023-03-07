package week8.BOJ_작업;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] indegree = new int[N + 1];
        int[] time = new int[N + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        for (int i = 1; i < graph.size(); i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            int num = Integer.parseInt(st.nextToken());
            for (int j = 0; j < num; j++) {
                int e = Integer.parseInt(st.nextToken());
                graph.get(e).add(i);

                indegree[i]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                result[i] = time[i];
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int next = graph.get(now).get(i);
                indegree[next]--;

                result[next] = Math.max(result[next], result[now] + time[next]);
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < result.length; i++) {
            answer = Math.max(answer, result[i]);
        }

        System.out.println(answer);
    }
}
