import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
// https://www.acmicpc.net/problem/17420
public class P17420_깊콘이넘쳐흘러 {

    static class Gifticon{
        long expireDate, useDate;
        Gifticon(long expireDate, long useDate){
            this.expireDate = expireDate; this.useDate = useDate;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        long answer = 0L;

        // 사용 기간이 제일 적은 것을 먼저 사용한다.
        ArrayList<Gifticon> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Gifticon(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken())) );
        }
        list.sort(Comparator.comparingLong(g -> g.useDate));

        Gifticon cur;
        long max_expireDate = list.get(0).useDate, // 사용예정일을 넘어야 하니까 초기설정.
             max_cur_expireDate = 0L;// 같은 사용일인 구간 내에서 가장 큰 만료일 저장

        for (int i = 0; i < list.size(); i++) {
            cur = list.get(i);
            if( max_expireDate > cur.expireDate ){// 이전 남은 기간 > 지금 남은 기간 : 지금꺼를 늘려야함
                max_expireDate = Math.max(max_expireDate, cur.useDate);// 이전 남은 기간 vs 현재 사용예정일 중 큰값
                long cnt = (max_expireDate-cur.expireDate+29) / 30;// 소숫점에서 올림
                cur.expireDate += cnt * 30;
                answer += cnt;
            }

            // useDate 같은 구간 내에서의 남은 기간 갱신
            max_cur_expireDate = Math.max(max_cur_expireDate, cur.expireDate);

            // 시용예정일이 다르면 max_expireDate 갱신해야 함. 같을땐 갱신 안해도 됨
            if(i == N-1) continue;
            if(cur.useDate != list.get(i+1).useDate ) max_expireDate = max_cur_expireDate;
        }

        System.out.println(answer);
    }
}
