import java.util.Arrays;
class 배달 {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
	        int[][] town = new int[N+1][N+1];

	        for(int i=0; i<=N; i++) {
	        	Arrays.fill(town[i], 500002);
                town[i][i] = 0;//같은 노드의 거리는 0임 1-1거리는 0
	        }

	        for(int[] r : road) {
	    		int weight = Math.min(r[2], town[r[0]][r[1]]);
    			town[r[0]][r[1]] = weight;
    			town[r[1]][r[0]] = weight;
	        }
	        //1에서 각 노드까지 걸리는 시간을 구한 후 k이하의 시간이 걸리는 노드개수를 반환하자
	        //풀로이드 워셜 - a-b-c 가 있을때 a-c거라는 a-b + b-c 거리다.
	        for(int k=1; k<=N; k++) {
		        for(int i=1; i<=N; i++) {
		        	for(int j=1; j<=N; j++) {
	        			town[i][j] = Math.min(town[i][j], town[i][k]+town[k][j]);
	        		}
	        	}
	        }

	        for(int i=1; i<=N; i++) {
        		if(town[1][i]<=K) answer+=1;
	        }

	        return answer;
    }
}