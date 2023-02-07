import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P14500_테트로미노 {
    public static int max;
    public static int[] dx = {1, -1, 0, 0};// 하상좌우
    public static int[] dy = {0, 0, -1, 1};
    public static int[][] arr;
    public static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        max = 0;

        // 현재의 칸에서 이어지게 3칸을 무작위로 고르도록 하면 ㅗ모양을 제외한 모든 모양이 만들어진다. 이것을 이용하여 dfs로 풀어보자
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 시작점 i,j
                visited[i][j] = true;
                dfs(0, arr[i][j], i, j, -1);// 추가 3칸 고르기
                visited[i][j] = false;
            }
        }
        System.out.println(max);
    }

    // visited 배열을 매번 처음 시작할 때마다 생성해서 넘겼더니 시간초과 남. 어차피 흔적 지워줄거면 바깥에 만들어 두기
    public static void dfs(int depth, int sum,int i, int j, int dir){// 시작점 제외 3칸 추가
        if(depth==3){
            if(max < sum){
                max = sum;
            }
            return;
        }
        
        if(depth==1){// ㅗ모양 구하기 ㅗ ㅏ ㅓ ㅜ 시작점이 튀어나온 곳임
            // dir 상 하 좌 우
            if(dir<=1){// 상하 0 1
                if(inBound(0, j-1) && inBound(0, j+1)){
                    int temp = sum + arr[i][j-1] + arr[i][j+1];
                    if(temp > max){
                        max = temp;
                    }
                }
            }else{// 좌우 2 3
                if(inBound(i-1, 0) && inBound(i+1, 0)){
                    int temp = sum + arr[i+1][j] + arr[i-1][j];
                    if(temp > max){
                        max = temp;
                    }
                }
            }
        }
        
        for (int k = 0; k < 4; k++) {
            int nx = i + dx[k];
            int ny = j + dy[k];
            if( !inBound(nx, ny) || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(depth+1, sum+arr[nx][ny], nx, ny, k);
            visited[nx][ny] = false;
        }

    }

    public static boolean inBound(int x, int y){
        if( x<0 || x>=arr.length || y<0 || y>=arr[0].length ) return false;
        return true;
    }
}
