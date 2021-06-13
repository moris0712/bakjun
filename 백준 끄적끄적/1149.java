import java.util.*;
import java.io.*;
import java.awt.*;

public class bbb {

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int cost_arr[][] = new int [N][3];
        int min_cost[][] = new int [N][3];

        for(int i=0; i<N; i++){
            for(int k=0; k<3; k++){
                cost_arr[i][k] = sc.nextInt();
            }
        }

        min_cost[0][0] = cost_arr[0][0];
        min_cost[0][1] = cost_arr[0][1];
        min_cost[0][2] = cost_arr[0][2];

        // 이 알고리즘은 다음것에서 최소값을 구하는것이아니고 
        // 이전까지의 합의 최솟값(min_cost[i-1][0,1,2])과 다음것들 3개(cost_arr[i][0,1,2])를 각각더한다. 

        for(int i=1; i<N; i++){
            min_cost[i][0] = Math.min(min_cost[i-1][1],min_cost[i-1][2]) + cost_arr[i][0];
            min_cost[i][1] = Math.min(min_cost[i-1][0],min_cost[i-1][2]) + cost_arr[i][1];
            min_cost[i][2] = Math.min(min_cost[i-1][0],min_cost[i-1][1]) + cost_arr[i][2];
        }

        System.out.println(Math.min(Math.min(min_cost[N-1][0],min_cost[N-1][1]),min_cost[N-1][2]));


    }
}
