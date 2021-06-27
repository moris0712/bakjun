import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int matrix [][];
    static int sum [][];

    public static void sum (int x1, int y1, int x2, int y2){
        sb.append(sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1]+"\n");
    }

    public static void main(String[] args) throws IOException{

        int N = sc.nextInt();
        int M = sc.nextInt();

        sum = new int [N+1][N+1];

        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= N; k++) {
                int temp = sc.nextInt();
                sum[i][k] = sum[i - 1][k] + sum[i][k - 1] + temp - sum[i - 1][k - 1];
            }
        }

        for(int i=0; i<M; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            sum(x1,y1,x2,y2);
        }
        System.out.print(sb);

        // for (int i = 0; i <= N; i++) {
        //     for (int k = 0; k <= N; k++) {
        //         System.out.print(sum[i][k] + " ");
        //     }
        //     System.out.println();
        // }
    }
}

// temp
// 0 0 0 0 0
// 0 1 2 3 4
// 0 2 3 4 5
// 0 3 4 5 6 
// 0 4 5 6 7

// sum
// 0  0  0  0  0
// 0  1  3  6  10
// 0  3  8  15 24
// 0  6  15 27 42
// 0  10 24 42 64


