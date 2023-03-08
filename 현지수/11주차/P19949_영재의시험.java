import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P19949_영재의시험 {
    public static int N=10, answer=0;
    public static int[] arr = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 영재는 연속 3개를 같은 답으로 찍지 않음, 총 10문제
        // 문제당 5가지 답을 쓸 쑤 있음
        // 백트랙킹 - 정답이 5개 이하면 더이상 탐색x, 진행하다 10개 다 차면 종료
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        backtracking(new int[N], 0, 0);
        System.out.println(answer);
    }

    static void backtracking(int[] exam, int wrongCnt, int index){
        if(wrongCnt > 5) return;
        if(index==N){
            answer++;
            return;
        }

        for (int i = 1; i < 6; i++) {
            exam[index] = i;

            if(index >= 2){// 영재는 3문제 이상 같은 숫자면 안됨
                if(exam[index] == exam[index-2]){
                    if( exam[index] == exam[index-1] ) continue;
                }
            }

            if( arr[index]==exam[index] ) backtracking(exam, wrongCnt, index+1);
            else backtracking(exam, wrongCnt+1, index+1);
            exam[index] = 0;
        }
    }

}
