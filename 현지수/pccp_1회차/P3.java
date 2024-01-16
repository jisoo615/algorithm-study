import java.util.Arrays;
import java.util.Stack;

public class P3 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(new int[][] {{4,26}} )));
    }
    static class Solution {
        Stack<Integer> stack;
        String root = "Rr";
        public String[] solution(int[][] queries) {
            StringBuilder sb = new StringBuilder();

            for(int[] q : queries){
                root = "Rr";
                stack = new Stack<>();
                recur(q[0], q[1]);

                while (!stack.isEmpty()){
                    Integer now = stack.pop();
                    if(root.equals("Rr")) root = getType(now);
                    else break;
                }
                sb.append(root+" ");
                stack.clear();
            }

            return sb.toString().split(" ");
        }
        public void recur(int gen, int parent){
            if(gen==1) return;

            int child = 0;
            if(parent%4==0){
                child = 4;
                parent = parent / 4;
            }else{
                child = parent % 4;
                parent = parent / 4 + 1;
            }
            stack.push(child);
            recur(gen-1, parent);
        }
        public String getType(int index){
            if(index==1) return "RR";
            else if(index==4) return "rr";
            else return "Rr";
        }
    }
}
