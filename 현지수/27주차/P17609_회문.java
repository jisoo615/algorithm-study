import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P17609_회문 {
    public static int answer = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            answer = 0;// 0으로 초기화
            String str = br.readLine();
            int left = 0, right = str.length()-1;

            while(left < right){
                if( str.charAt(left) == str.charAt(right) ){
                    left++;
                    right--;
                }
                else{
                    boolean exceptLeft = isPalindrome(str, left+1, right);// 왼쪽 제외하고 검사
                    boolean exceptRight = isPalindrome(str, left, right-1);// 오른쪽 제외하고 검사
                    if(exceptLeft || exceptRight){// 둘 중 하나는 참이 나왔으면 '유사회문 1'
                        answer = 1;
                        break;
                    }else{// 둘다 거짓이면 '아무것도 아님 2'
                        answer = 2;
                        break;
                    }
                }
            }// while end

            sb.append(answer+"\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean isPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

}
