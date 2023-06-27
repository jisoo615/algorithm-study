import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15686_치킨배달 {
    public static ArrayList<Point> chickenList = new ArrayList<>();
    public static ArrayList<Point> homeList = new ArrayList<>();
    public static int min = Integer.MAX_VALUE;
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                String str = st.nextToken();
                if(str.equals("2")) chickenList.add(new Point(i, j));
                if(str.equals("1")) homeList.add(new Point(i, j));
            }
        }

        // 조합으로 치킨수에서 m개를 뽑는 경우의 수를 구해서, 치킨거리 각각 구해서 최솟값을 구하기
        recur(0, M, new int[M], 0);
        System.out.println(min);
    }

    private static void recur(int start, int m, int[] arr, int idx) {
        // 조합 m개 고르기
        if(idx==m){
            min = Math.min(min, chickenDist(arr));
            return;
        }
        for (int i = start; i < chickenList.size(); i++) {//
            arr[idx] = i;
            recur(i+1, m, arr, idx+1);
        }
    }
    private static int chickenDist(int[] arr) {
        // 집 위치 - 치킨 위치
        int distance = 0;

        for (int i = 0; i < homeList.size(); i++) {
            //집마다 가장 가까운 치킨거리 구해서 합치기
            int minDist = Integer.MAX_VALUE;
            for(int j : arr){// |x-x|+|y-y|
                minDist = Math.min(minDist,
                        Math.abs( homeList.get(i).x - chickenList.get(j).x )+Math.abs( homeList.get(i).y - chickenList.get(j).y) );
            }
            distance+= minDist;
        }
        return distance;
    }
}
