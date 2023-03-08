import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P16435_스네이크버드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        ArrayList<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add( Integer.parseInt(st.nextToken()) );
        }
        // 정렬을 하고 아래 while문을 돌려야 정답으로 나옴. 왜지??
        Collections.sort(list);
        while ( !list.isEmpty() ){
            if(list.contains(L)){
                list.remove(Integer.valueOf(L));
                L++;
            }
            else if(list.get(0) < L){
                list.remove(0);
                L++;
            }else{
                list.clear();
            }
        }
        System.out.println(L);
    }
}
