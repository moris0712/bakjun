import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    static int arr[][];
    static int N;
    static int M;
    static boolean visited[][];

    public static void move(int x, int y, int length) {

        int[] x_arr = { 1, -1, 0, 0 };
        int[] y_arr = { 0, 0, 1, -1 };

        Queue<Point> q = new LinkedList<Point>();
        Point p = new Point();

        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            p = q.poll();
            int temp_x = p.x;
            int temp_y = p.y;
            if (temp_x == N - 1 && temp_y == M - 1) {
                System.out.println(arr[temp_x][temp_y]);
                break;
            }
            for (int i = 0; i < 4; i++) {
                temp_x = p.x + x_arr[i];
                temp_y = p.y + y_arr[i];
                // System.out.println("aatemp: " + temp_x + " " + temp_y+"
                if (0 <= temp_x && temp_x <= N - 1 && 0 <= temp_y && temp_y <= M - 1) {
                    if (!visited[temp_x][temp_y] && arr[temp_x][temp_y] == 1) {
                        visited[temp_x][temp_y] = true;
                        // System.out.print("\n"+"aatemp: " + temp_x + " " + temp_y + " length: " +
                        arr[temp_x][temp_y] = arr[p.x][p.y] + 1;
                        q.add(new Point(temp_x, temp_y));

                    }
                }
            }
        }
        // return 0;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        visited[0][0] = true;

        move(0, 0, 1);
        
        
        // for (int i = 0; i < N; i++) {
        //     for (int j = 0; j < M; j++) {
        //         System.out.print(arr[i][j]+" ");
        //     }
        //     System.out.println();
        // }

    }
}
