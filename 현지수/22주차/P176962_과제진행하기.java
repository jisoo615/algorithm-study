import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/176962
public class P176962_과제진행하기 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().solution(
                new String[][] {{"korean", "11:40", "30"},{"english", "12:10", "20"},{"math", "12:30", "40"}}
        )));
        System.out.println(Arrays.toString(new Solution().solution(
                new String[][] {{"A", "12:00", "30"},{"B", "12:10", "20"},{"C", "15:00", "40"},{"D", "15:10", "30"}}
        )));
    }
    // 진행중에 새로운 과제 있으면 새로운 과제 시작해야 함
    // 과제 끝내면 최근에 했던 과제 부터 시작해야 함 - stack
    static class Solution {
        public String[] solution(String[][] plans) {
            String answer = "";
            ArrayList<Plan> list = new ArrayList<>();
            Stack<Plan> stack = new Stack<>();

            for (String[] plan : plans) {
                list.add(new Plan(plan[1], plan[2], plan[0]) );
            }
            list.sort((Plan p1, Plan p2)->{// 시작 시간 오름차순
                 return p1.startTime - p2.startTime;
            });

            for (int i = 0; i < list.size()-1; i++) {
                if(list.get(i).startTime+list.get(i).leftTime > list.get(i+1).startTime) {// 1. 다음 과제 전에 못끝낼때
                    int free = list.get(i+1).startTime - list.get(i).startTime;// 비는 시간
                    list.get(i).leftTime -= free;
                    stack.push(list.get(i));// 남은 과제 스택에 push

                }
                else{// 2. 다 끝낼수있을때, stack 과제하기
                    answer+=list.get(i).name+" ";// 끝낸 과제 기록

                    int free = list.get(i+1).startTime - (list.get(i).startTime + list.get(i).leftTime);// 비는 시간
                    while (!stack.isEmpty() && free > 0){// 2-1. 다음꺼 시작 전까지 남은것 끝내기
                        Plan p = stack.pop();

                        if(free >= p.leftTime){// 남은 과제1 끝냄
                            free -= p.leftTime;
                            answer+=p.name+" ";
                        }
                        else{
                            p.leftTime -= free;// 남은 과제1 못끝냄
                            free = 0;
                            stack.push(p);
                        }
                    }
                    
                }
            }
            stack.push(list.get(list.size()-1));// 마지막 과제는 무조건 바로 해결할수있음 -> stack에 넣어놓고 남은거 한번에 처리

            while(!stack.isEmpty()){
               answer+=stack.pop().name+" ";
            }

            return answer.split(" ");
        }
        static class Plan{
            int startTime, leftTime;
            String name;
            public Plan(String startTime, String leftTime, String name){
                String[] sarr = startTime.split(":");
                this.startTime += Integer.parseInt(sarr[0])*60 + Integer.parseInt(sarr[1]);
                this.leftTime = Integer.parseInt(leftTime);
                this.name=name;
            }
        }
    }
}
