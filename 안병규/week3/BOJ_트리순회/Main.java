package week3.BOJ_트리순회;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] tree;
    static int count = 0;
    static int N;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][2];
        list = new ArrayList<>();

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int parentNode = Integer.parseInt(st.nextToken());
            tree[parentNode][0] = Integer.parseInt(st.nextToken());
            tree[parentNode][1] = Integer.parseInt(st.nextToken());
        }

        dfs(1, false);
        count = 0;
        dfs(1, true);
    }

    private static void dfs(int n, boolean flag) {

        if (tree[n][0] != -1) {
            dfs(tree[n][0], flag);
            count++;
        }
        if (!flag) {
            list.add(n);
        } else {
            if (list.get(list.size() - 1) == n) {
                System.out.println(count);
            }
            count++;
        }
        if (tree[n][1] != -1) {
            dfs(tree[n][1], flag);
            count++;
        }

    }
}
