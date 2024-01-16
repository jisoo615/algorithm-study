public class P2 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution(new int[][] {{20, 30},{30, 20},{20, 30}}));
    }

    static class Solution {
        int[][] ability;
        boolean[] visited;
        int max = 0;
        public int solution(int[][] ability) {
            this.ability = ability;
            int depth = ability.length;
            int needs = ability[0].length;
            visited = new boolean[depth];

            recur(new int[needs], 0, depth, 0);

            return max;
        }

        public void recur(int[] arr, int index, int depth, int sum){
            if(index==arr.length){
                if(sum > max) max = sum;
                return;
            }

            for (int i = 0; i < visited.length; i++) {
                if(!visited[i]){
                    visited[i] = true;
                    arr[index] = i;
                    recur(arr, index+1, depth, sum + this.ability[i][index]);
                    visited[i] = false;
                }
            }
        }
    }
}
