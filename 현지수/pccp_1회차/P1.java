import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.util.stream.Collectors;

public class P1 {
    public static void main(String[] args) {
        System.out.println(new Solution().solution("edeaaabbccd"));

    }
    static class Solution {
        public String solution(String input_string) {
            String answer = "";
            Set<Character> alone = new HashSet<>();
            Stack<Character> stack = new Stack<>();

            for(char c : input_string.toCharArray()){
                if(!stack.isEmpty() && stack.peek() == c) continue;
                else stack.push(c);
            }

            HashMap<Character, Integer> map = new HashMap<>();
            while(!stack.isEmpty()){
                map.put(stack.peek(), map.getOrDefault(stack.pop(), 0)+1);
            }

            Map<Character, Integer> srotedMap = map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (e1, e2) -> e1, LinkedHashMap::new));

            for (Character c : srotedMap.keySet()){
                if(srotedMap.get(c)>1) answer+=c;
            }

            return answer;
        }
    }
}
