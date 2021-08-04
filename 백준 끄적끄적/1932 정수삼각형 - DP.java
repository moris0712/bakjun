import java.util.*;
import java.io.*;

public class qqq {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int arr[][];
    static int sum_arr[][];

    public static void DP(int N){

        sum_arr[0][0] = arr[0][0];

        for(int i=1; i<N; i++){
            for(int k=0; k<=i; k++){
                if(k==0)
                    sum_arr[i][k] = sum_arr[i-1][k] + arr[i][k];
                else if(k==i)
                    sum_arr[i][k] = sum_arr[i-1][k-1] + arr[i][k];
                else    
                    sum_arr[i][k] = Math.max(sum_arr[i-1][k-1],sum_arr[i-1][k]) + arr[i][k];
            }
        }

        PriorityQueue<Integer> q = new PriorityQueue<Integer>(Collections.reverseOrder());

        for(int i=0; i<N; i++){
            q.add(sum_arr[N-1][i]);
        }
        System.out.println(q.poll());

    }

    public static void main(String[] args) throws IOException{

        int N = sc.nextInt();
        arr = new int [N][];
        sum_arr = new int [N][];

        for(int i=0; i<N; i++){
            arr[i] = new int [i+1];
            sum_arr[i] = new int [i+1];
            for(int k=0; k<=i; k++){
                arr[i][k] = sc.nextInt();
            }
        }
        DP(N);
        
        // System.out.println(Arrays.deepToString(arr));

        // System.out.println("sum_arr");
        // for (int i = 0; i < N; i++) {
        //     for (int k = 0; k <= i; k++) {
        //         System.out.print(sum_arr[i][k]+" ");
        //     }
        //     System.out.println();
        // }
        
    }
}




