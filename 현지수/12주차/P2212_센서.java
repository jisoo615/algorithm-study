import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P2212_센서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N, K, answer= 0;
        ArrayList<Integer> diff = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        int[] express = new int[N];
        K = Integer.parseInt(br.readLine());
        if(N <= K ){
            System.out.println("0");
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) express[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(express);
        for (int i = 0; i < N-1; i++) diff.add( express[i+1] - express[i] );
        diff.sort(Comparator.reverseOrder());

// K-1개의 거리를 빼고 나머지 거리를 더하기
        for (int i = K-1; i < N-1; i++) {
            answer += diff.get(i);
        }

        System.out.println(answer);
    }
}
