import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분수열 2
public class P12015_가장긴증가하는부분수열2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 배열 내용은 상관없이 길이만 구하면 됨
        ArrayList<Integer> list = new ArrayList<>();
        int idx = 0;
        list.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if( list.get( list.size()-1 ) < arr[i]) list.add(arr[i]);
            else{
// 2               idx = Collections.binarySearch(list, arr[i]);
                idx = binarySearch(list, arr[i]);
// 2               if( idx >= 0) list.set(idx, arr[i]);
// 2               else list.set(idx*-1-1, arr[i]);
                list.set(idx, arr[i]);
            }
        }

        System.out.println(list.size());
    }

    static public int binarySearch(ArrayList<Integer> list, int target){// target보다 크거나 같은 것이 나오는 첫번째 위치
        int start = 0, end = list.size()-1, mid = 0;
        while (start < end){
            mid = (start+end)/2;

            if(list.get(mid) >= target){
                end = mid;
            }else{
                start = mid+1;
            }
        }

        return end;
    }
}
