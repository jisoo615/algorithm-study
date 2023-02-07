import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1107_리모콘 {
    public static boolean[] broken;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        broken = new boolean[10];
        if(N==100){// 100일땐 0
            System.out.println("0");
            return;
        }

        if(M!=0){// 고장난 버튼이 있을 경우
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }
        // i) 모든 수를 다 확인해야 한다. 1~999999 까지 돌면서 고장난 번호가 있다면 정답이 될 수 없고
        // ii) 고장난 번호가 없다면 해당 횟수와 기존에 구한 횟수 중 작은 값을 택한다.
        int answer = Math.abs(100-N);// 100에 더 가까우면 이 값.

        for (int i = 0; i < 1000000; i++) {
            int cnt = buttonCnt(i, N);
            if(answer > cnt)answer = cnt;
        }

        System.out.println(answer);
    }


    /**
     * @param x 누른 채널 번호
     * @param target 최종적으로 도달해야 하는 번호
     * @return 채널번호에 고장난 번호가 포함되면 MAX값, 아니면 숫자누른횟수+플마횟수
     */
    public static int buttonCnt(int x, int target){
        String channel = String.valueOf(x);

        for (int i = 0; i < channel.length(); i++) {
            int number = x % 10;
            if(broken[number]) return 1000000;
            x /= 10;
        }
        int cnt = channel.length() + Math.abs(target - Integer.parseInt(channel));
        return cnt;
    }
}
