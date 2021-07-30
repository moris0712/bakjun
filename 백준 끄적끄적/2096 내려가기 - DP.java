import java.util.*;
import java.io.*;

public class aaa {


    static Scanner sc = new Scanner(System.in);
    static int arr[][];
    static int max_arr[][];
    static int min_arr[][];

    public static int max_dp (int N){

        max_arr[0][0] = arr[0][0]; 
        max_arr[0][1] = arr[0][1];
        max_arr[0][2] = arr[0][2];

        for(int i=1; i<N; i++){
            max_arr[i][0] = Math.max(max_arr[i-1][0],max_arr[i-1][1]) + arr[i][0];
            max_arr[i][1] = Math.max(Math.max(max_arr[i-1][0],max_arr[i-1][1]),max_arr[i-1][2]) + arr[i][1];
            max_arr[i][2] = Math.max(max_arr[i-1][2],max_arr[i-1][1]) + arr[i][2];
        }
        return Math.max(Math.max(max_arr[N-1][0],max_arr[N-1][1]),max_arr[N-1][2]);
    }

    public static int mim_dp(int N) {

        min_arr[0][0] = arr[0][0];
        min_arr[0][1] = arr[0][1];
        min_arr[0][2] = arr[0][2];

        for (int i = 1; i < N; i++) {
            min_arr[i][0] = Math.min(min_arr[i - 1][0], min_arr[i - 1][1]) + arr[i][0];
            min_arr[i][1] = Math.min(Math.min(min_arr[i - 1][0], min_arr[i - 1][1]), min_arr[i - 1][2]) + arr[i][1];
            min_arr[i][2] = Math.min(min_arr[i - 1][2], min_arr[i - 1][1]) + arr[i][2];
        }
        return Math.min(Math.min(min_arr[N - 1][0], min_arr[N - 1][1]), min_arr[N - 1][2]);
    }

    public static void main(String[] args) throws IOException{

        int N = sc.nextInt();

        arr = new int [N][3];
        max_arr = new int [N][3];
        min_arr = new int [N][3];

        for(int i=0; i<N; i++){
            for(int k=0; k<3; k++){
                arr[i][k] = sc.nextInt();
            }
        }
        //min_dp(N);
        System.out.print(max_dp(N)+" ");
        System.out.println(mim_dp(N));

    }
}

