import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class P12757_전설의JBNU {
    public static TreeMap<Integer, Integer> treeMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());// key와 젤 비슷한 숫자와의 차이가 k이하여야 함
        StringBuilder sb = new StringBuilder();
        treeMap = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            treeMap.put(key, value);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int key = 0, value=0, foundKey=0;
            switch (command){
                case 1:// 추가
                    key = Integer.parseInt(st.nextToken());
                    value = Integer.parseInt(st.nextToken());
                    treeMap.put(key, value);
                    break;
                case 2:// 변경
                    key = Integer.parseInt(st.nextToken());
                    value = Integer.parseInt(st.nextToken());
                    if(treeMap.containsKey(key)){
                        treeMap.put(key, value);
                        break;
                    }
                    foundKey = findKey(treeMap.ceilingKey(key), treeMap.floorKey(key), K, key);
                    if (foundKey >= 0) treeMap.put(foundKey, value);
                    break;
                case 3:// 출력
                    key = Integer.parseInt(st.nextToken());
                    if(treeMap.containsKey(key)){
                        sb.append(treeMap.get(key)).append("\n");
                        break;
                    }
                    foundKey = findKey(treeMap.ceilingKey(key), treeMap.floorKey(key), K, key);
                    if(foundKey >= 0) sb.append(treeMap.get(foundKey));
                    else sb.append( foundKey==-2 ? "?": -1 ).append("\n");
                    break;
            }
        }
        System.out.println(sb.toString());
        // treemap 활용 -> ceilingkey, floorkey 존재
    }
    static int findKey(Integer ceil, Integer floor, int k, int key){
        int cdiff = ceil==null ? k+1 : ceil - key;
        int fdiff = floor==null ? k+1 : key - floor;// 존재하지 않는다면 조건 만족 안하도록 k+1

        if(cdiff>k && fdiff>k) return -1;// 값이 없으면 -1

        if(cdiff==fdiff) return -2;// 값이 2개 이상일때 ?
        return cdiff < fdiff ? ceil : floor;
    }
}
