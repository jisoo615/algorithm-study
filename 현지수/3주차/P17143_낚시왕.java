import javax.sql.rowset.serial.SerialJavaObject;
import java.awt.image.ImagingOpException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 구현 https://www.acmicpc.net/problem/17143
public class P17143_낚시왕 {
    public static int[] dx = {0, -1, 1, 0, 0};// 위 아래 오른 왼
    public static int[] dy = {0, 0, 0, 1, -1};
    public static char[][] ocean, moved_ocean;
    public static HashMap<Character, Shark> sharkMap;

    static class Shark {
        int x, y, speed, dir, size=0, xlen, ylen;
        char number;

        public Shark(int x, int y, int speed, int dir, int size, int xlen, int ylen, char number) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.xlen = xlen;
            this.ylen = ylen;
            this.number = number;
        }

        char move() {// 스피드만큼 이동, 벽을 치고 갔으면 방향 바꿔주기
            int nx = x, ny = y;
            if (dir == 1 || dir == 2) {// 위아래
                for (int i = 0; i < speed; i++) {
                    nx += dx[dir];
                    if(nx==0){
                        nx = 2;
                        changeDirection();
                    }else if(nx==xlen+1){
                        nx = xlen-1;
                        changeDirection();
                    }
                }
            } else {// 좌우
                for (int i = 0; i < speed; i++) {
                    ny += dy[dir];
                    if(ny==0){
                        ny = 2;
                        changeDirection();
                    }else if(ny==ylen+1){
                        ny = ylen-1;
                        changeDirection();
                    }
                }
            }

            this.x = nx; this.y = ny;// 좌표 갱신

            if (moved_ocean[nx][ny] != Character.MIN_VALUE) {// 이미 상어가 있을때
                return eatShark(nx, ny);// 잡아먹힌 number 반환
            }
            moved_ocean[nx][ny] = this.number;
            return '#';// 아무것도 잡아먹고 잡아먹히지 않았을때
        }

        char eatShark(int nx, int ny) {
// 상어 잡아먹거나 잡아먹히기
            Shark old = sharkMap.get(moved_ocean[nx][ny]);
            if (old.size < this.size) {// 잡아먹음
                sharkMap.remove(moved_ocean[nx][ny]);
                moved_ocean[nx][ny] = this.number;
                return old.number;
            } else{// 잡아먹힘
                return this.number;
            }
        }
        
        void changeDirection(){
            if(dir==1 || dir==3) dir+=1;
            else dir-=1;
        }
    }

    static class Person{
        int x, y, totalSize;
        Person(){
            this.x = 0; this.y=0; this.totalSize = 0;
        }
        void moveForward(){
           y+=1;
        }
        void fishing(){
            char nearest = Character.MIN_VALUE;
            for (int i = 1; i < ocean.length; i++) {
                if( ocean[i][y] == Character.MIN_VALUE ) continue;
                nearest = ocean[i][y];
                break;
            }
            if(nearest==Character.MIN_VALUE) return;// 없으면 못잡고 다음칸 가

            Shark shark = sharkMap.get(nearest);
            this.totalSize += shark.size;
            sharkMap.remove(nearest);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int R = Integer.parseInt(st.nextToken());// 세로
        int C = Integer.parseInt(st.nextToken());// 가로
        int M = Integer.parseInt(st.nextToken());// 상어 수

        ocean = new char[R+1][C+1];
        moved_ocean = new char[R+1][C+1];
        sharkMap = new HashMap<>();

        int r, c, s, d, z;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            //s를 최소화 해서 저장하기 : (N-1)*2 칸 이후 = 제자리
            if(d==1 || d==2) {// 위아래
                s = s % ((R-1)*2);
            }else{// 우 좌
                s = s % ((C-1)*2);
            }
            ocean[r][c] = (char) ('A'+i);
            sharkMap.put(ocean[r][c], new Shark(r, c, s, d, z, R, C, ocean[r][c]));
        }

        Person fisherman = new Person();
        while(fisherman.y < C){
            fisherman.moveForward();
            fisherman.fishing();

            for (int i = 0; i < M; i++) {
                char key = (char)('A'+i);
                if(sharkMap.containsKey(key)){
                    int eatenShark = sharkMap.get(key).move();
                    if(eatenShark =='#') continue;
                    sharkMap.remove(eatenShark);
                }
            }

            ocean = moved_ocean;// 옮겨주고
            moved_ocean = new char[R+1][C+1];// 초기화
        }
        answer = fisherman.totalSize;
        System.out.println(answer);
    }
}
