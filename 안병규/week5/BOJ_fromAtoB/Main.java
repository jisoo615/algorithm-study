package week5.BOJ_fromAtoB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        br.close();

        int cnt = (int) getCount(A, B);

        System.out.println(cnt);
    }

    private static long getCount(long A, long B) {
        Queue<Long[]> q = new LinkedList<>();

        q.offer(new Long[]{A, 1L});
        while (!q.isEmpty()) {
            Long[] poll = q.poll();

            if (poll[0] == B) {
                return poll[1];
            }

            if (poll[0] < B) {
                poll[1]+=1;
                q.offer(new Long[]{poll[0] * 2, poll[1]});
                q.offer(new Long[]{poll[0] * 10 + 1, poll[1]});
            }
        }
        return -1;
    }
}
