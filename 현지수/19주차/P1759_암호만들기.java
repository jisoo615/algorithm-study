import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1759_암호만들기 {
    public static int L,C;
    public static String[] alphabet;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabet = br.readLine().split(" ");

        Arrays.sort(alphabet);

        //C 개중  L개를 선택, 사전순으로
        comb(0, 0, "", 0);

        System.out.println(sb.toString());
    }

    static void comb(int depth, int index, String str, int cnt_aeiou){
        if(depth==L){
            if(cnt_aeiou >= 1 && (depth - cnt_aeiou) >= 2){// 모음 하나 이상, 자음 2개 이상
                sb.append(str +"\n");
            }
            return;
        }
        if(index==C) return;

        // 이 문자를 선택할때
        if(alphabet[index].equals("a") || alphabet[index].equals("e") || alphabet[index].equals("i") || alphabet[index].equals("o") || alphabet[index].equals("u")){
            comb(depth+1, index+1, str+ alphabet[index], cnt_aeiou+1);
        }else comb(depth+1, index+1, str+ alphabet[index], cnt_aeiou);

        //이 문자를 선택 안할때
        comb(depth, index+1, str, cnt_aeiou);
    }
}
