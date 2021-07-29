import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static int max_score(){

        int N = sc.nextInt();
        int sticker[][] = new int [2][N];
        int sum[][] = new int [2][N];
        for(int i=0; i<2; i++){
            for(int k=0; k<N; k++){
                sticker[i][k] = sc.nextInt();
            }
        }
        sum[0][0] = sticker[0][0];
        sum[1][0] = sticker[1][0];


        for(int i=1; i<N; i++){

            if(i==1){
                sum[0][i] = sum[1][i-1] + sticker[0][i]; 
                sum[1][i] = sum[0][i-1] + sticker[1][i];
            }

            else{
                sum[0][i] = Math.max( sum[1][i-1] + sticker[0][i] , Math.max(sum[0][i-2],sum[1][i-2]) + sticker[0][i]);
                sum[1][i] = Math.max( sum[0][i-1] + sticker[1][i] , Math.max(sum[0][i-2],sum[1][i-2]) + sticker[1][i]);
            }
        }
        // for(int i=0; i<2; i++){
        //     System.out.println();
        //     for(int k=0; k<N; k++){
        //         System.out.print(sum[i][k]+" ");
        //     }
        // }
        
        return Math.max(sum[0][N-1],sum[1][N-1]);
    }
    public static void main(String[] args) throws IOException{

        int T = sc.nextInt();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<T; i++){
            sb.append(max_score()+"\n");
        }
        System.out.print(sb);
    }
}

