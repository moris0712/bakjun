import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int cost_arr[][] = new int [N][3]; // i번째 색깔칠하는데 드는 비용이 저장되는 배열
        int min_cost[][] = new int [N][3]; // i번째 까지 색깔칠하는데 최소비용이 저장되는 배열

        for(int i=0; i<N; i++){
            for(int k=0; k<3; k++){
                cost_arr[i][k] = sc.nextInt();
            }
            if(i==0){
                min_cost[i][0] = cost_arr[i][0];
                min_cost[i][1] = cost_arr[i][1];
                min_cost[i][2] = cost_arr[i][2];
            }
            else{
                min_cost[i][0] = Math.min(min_cost[i-1][1] + cost_arr[i][0], min_cost[i-1][2] + cost_arr[i][0]);
                min_cost[i][1] = Math.min(min_cost[i-1][0] + cost_arr[i][1], min_cost[i-1][2] + cost_arr[i][1]);
                min_cost[i][2] = Math.min(min_cost[i-1][0] + cost_arr[i][2], min_cost[i-1][1] + cost_arr[i][2]);
            }
        }

        // System.out.println();
        // for(int i=0; i<N; i++){
        //     for(int k=0; k<3; k++){
        //         System.out.print(cost_arr[i][k]+" ");
        //     }
        //     System.out.println();
        // }

        // System.out.println();
        // for (int i = 0; i < N; i++) {
        //     for (int k = 0; k < 3; k++) {
        //         System.out.print(min_cost[i][k] + " ");
        //     }
        //     System.out.println();
        // }
        System.out.println(Math.min(min_cost[N-1][0],Math.min(min_cost[N-1][1],min_cost[N-1][2])));

        

        
    }
}
