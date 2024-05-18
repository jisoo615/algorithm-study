public class Pro괄호변환 {
    // 재귀
    public static void main(String[] args) {
        System.out.println(new Solution().solution(")("));
    }
    static public class Solution{
        static public String solution(String p) {
            String answer="";
            answer = recur(p);
            return answer;
        }

        static public String recur(String s){
            if(s.isEmpty()) return "";

            int index = balancedStr(s);
            String u = s.substring(0, index);
            String v = s.substring(index, s.length());

            if(isRight(u)){// 발란스 + 올바름
                return u + recur(v);
            }
            else{// 발란스 + 안올바름 : 괄호 바꿔야함
                return "("+recur(v)+")"+correcting(u);
            }
        }

        static public String correcting(String u){
            String tmp = "";
            String s = u.substring(1, u.length()-1);
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='(') tmp += ")";
                else tmp += "(";
            }
            return tmp;
        }

        static public boolean isRight(String s){
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)=='(') cnt++;
                else cnt--;

                if(cnt<0) return false;// -가 되면 반대로된 괄호가 있다는 것
            }
            return true;
        }
        
        static public int balancedStr(String s){// 균형잡힌 문장의 끝인덱스 반환
            int open = 0, close = 0;
            for (int i = 0; i < s.length(); i++) {
                int ch = s.charAt(i);
                if(ch=='(') open++;
                else close++;

                if(open == close) return open + close;
            }
            return 0;
        }
    }
}
