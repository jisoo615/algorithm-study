import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
// https://www.acmicpc.net/problem/21276
public class P21276_계보복원가호석 {
    public static ArrayList<ArrayList<Integer>> graph;
    public static HashMap<String, ArrayList> map;
    public static int[] indegree;
    public static HashMap<Integer, String> findName;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 위상정렬!
        int N = Integer.parseInt(br.readLine());

        indegree = new int[N];
        HashMap<String, Integer> findIdx = new HashMap<>();
        findName = new HashMap<>();
        graph = new ArrayList<>();
        map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i <N; i++) {
            String name = st.nextToken();

            findIdx.put(name, i);
            findName.put(i, name);
            graph.add(new ArrayList<>());
            map.put(name, new ArrayList<String>());
        }

        int M = Integer.parseInt(br.readLine());
        // x의 조상인 y : y는 조상 중에 하나 임=바로 위 아닐 수도 있음
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int child = findIdx.get( st.nextToken() );
            int ancestor = findIdx.get( st.nextToken() );

            graph.get(ancestor).add(child);
            indegree[child]++;
        }

        StringBuilder sb = new StringBuilder();
        int root = 0;
        ArrayList<String> rootList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if(indegree[i]==0){// 진입차수가 0이면 root
                root++;
                rootList.add(findName.get(i));
            }
        }

        sb.append(root+"\n");
        rootList.sort(Comparator.naturalOrder());
        for(String name : rootList){
            sb.append(name).append(" ");
            goDown(findIdx.get(name));
        }

        // 알파벳 순서대로 저장해둔 map에서 꺼내기
        List<String> alphaOrder = map.keySet().stream().sorted().collect(Collectors.toList());
        for(String name : alphaOrder){
            sb.append("\n");
            
            List<String> children = map.get(name);
            children.sort(Comparator.naturalOrder());

            sb.append(name+" "+children.size());

            for(String child : children) sb.append(" "+child);
        }
        System.out.println(sb.toString());
    }

    static public void goDown(int root){
        // 진입차수 0인것 부터 아래로, 0인것과 이어진 간선 지우고 또 0인것 찾기
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            int ancestor = q.poll();

            for (int i = 0; i < graph.get(ancestor).size(); i++) {
                int son = graph.get(ancestor).get(i);
                indegree[son]--;
                
                if(indegree[son]==0){// 이어진 간선을 없앨때 진입차수가 0이면 바로 아래 자식
                    q.add(son);
                    map.get( findName.get(ancestor) ).add( findName.get(son) );
                }
            }
        }
    }
}
