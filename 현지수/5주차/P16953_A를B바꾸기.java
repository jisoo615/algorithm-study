import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P16953_A를B바꾸기 {
    // 2를 곱한다.
    // 1을 수의 가장 오른쪽에 추가한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
//B부터 거꾸로 시작하기 + bfs로 풀기
        // 2로 나누어 떨어지지 않으면 -1 /10 하기
        int cnt = 0, answer=-1;

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(B, 0));
        while (!q.isEmpty()){
            Point p = q.poll();
            if(p.x < A) continue;

            if(p.x==A){
                cnt = p.y;
                break;
            }

            if(p.x%2==0){
                q.add(new Point(p.x/2, p.y+1));
            }else if((p.x-1)%10==0){
                q.add(new Point((p.x-1)/10, p.y+1));
            }
        }

        System.out.println(cnt>0 ? cnt+1 : answer);
    }
}
