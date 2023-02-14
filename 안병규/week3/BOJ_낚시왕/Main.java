package week3.BOJ_낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int M;
    static int[] mx = {0, 0, 0, 1, -1};
    static int[] my = {0, -1, 1, 0, 0};
    static ArrayList<ArrayList<Queue<Shark>>> board;
    static ArrayList<ArrayList<Queue<Shark>>> tmp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = getBoard();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            board.get(r).get(c).add(new Shark(r, c, s, d, z));
        }

        int result = fishing();

        System.out.println(result);
    }

    private static ArrayList<ArrayList<Queue<Shark>>> getBoard() {
        ArrayList<ArrayList<Queue<Shark>>> board = new ArrayList<>();
        for (int i = 0; i <= R; i++) {
            board.add(new ArrayList<>());
            for (int j = 0; j <= C; j++) {
                board.get(i).add(new LinkedList<>());
            }
        }
        return board;
    }

    private static int fishing() {

        int sum = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R; j++) {
                if (!board.get(j).get(i).isEmpty()) {
                    Shark shark = board.get(j).get(i).poll();
                    sum += shark.z;
                    break;
                }
            }
            board = moveSharks();
            kill();
        }

        return sum;
    }

    private static void kill() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {

                if (board.get(i).get(j).size() >= 2) {
                    Queue<Shark> sharks = board.get(i).get(j);

                    while (true) {
                        Shark shark = sharks.poll();

                        if (sharks.isEmpty()) {
                            sharks.add(shark);
                            break;
                        } else if (shark.z > sharks.peek().z) {
                            sharks.add(shark);
                        }
                    }
                }
            }
        }
    }

    private static ArrayList<ArrayList<Queue<Shark>>> moveSharks() {

        tmp = getBoard();

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (!board.get(i).get(j).isEmpty()) {
                    Shark shark = board.get(i).get(j).poll();

                    int dr = shark.d;
                    int distance = getMovingDistance(dr, shark.s);

                    int x = shark.c;
                    int y = shark.r;
                    while (distance != 0) {
                        distance--;

                        int nextX = x + mx[dr];
                        int nextY = y + my[dr];
                        if (nextX < 1 || nextX > C || nextY < 1 || nextY > R) {
                            if (dr == 1) {
                                dr = 2;
                            } else if (dr == 2) {
                                dr = 1;
                            } else if (dr == 3) {
                                dr = 4;
                            } else {
                                dr = 3;
                            }
                            nextX = x + mx[dr];
                            nextY = y + my[dr];
                        }
                        x = nextX;
                        y = nextY;
                    }
                    shark.d = dr;
                    shark.r = y;
                    shark.c = x;
                    tmp.get(y).get(x).add(shark);
                }
            }
        }
        return tmp;
    }

    private static int getMovingDistance(int dr, int speed) {

        int distance;
        if (dr == 1 || dr == 2) {
            distance = speed % ((R - 1) * 2);
        } else {
            distance = speed % ((C - 1) * 2);
        }

        return distance;
    }

    public static class Shark {

        int r;
        int c;
        int s;
        int d;
        int z;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

}
