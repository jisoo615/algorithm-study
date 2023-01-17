import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22856_트리순회 {
    static class Node{
        int left, right;
        boolean visited= false;
    }
    static public Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int MaxTree = 100001;
        tree = new Node[MaxTree];
        int N = Integer.parseInt(st.nextToken());
// 트리 만들기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            tree[a] = new Node();
            tree[a].left = left;
            tree[a].right = right;
        }

        int edgeCnt = inOrder(1)-1;
// 맨 오른쪽 노드들만 1번 지나감, 나머지 간선들은 2번씩 지나가는데
        int rightest = 0, idx=1;
        while(true){
            if( tree[idx].right != -1){
                idx = tree[idx].right;
                rightest += 1;
            }else break;
        }

        System.out.println( 2*edgeCnt - rightest );
    }

    static public int inOrder(int node){// 중위순회 하면서 간선 세기
        int cnt = 0;
        tree[node].visited = true;

        if(tree[node].left != -1 && !tree[tree[node].left].visited){// 왼쪽 자식이 있으면 왼쪽 순회
            cnt += inOrder(tree[node].left);
        }
        if(tree[node].right != -1 && !tree[tree[node].right].visited){// 오른쪽 자식이 있으면 왼쪽 순회
            cnt += inOrder(tree[node].right);
        }

        return cnt+1;
    }
}
