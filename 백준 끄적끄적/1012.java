import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static int[][] arr;
    static boolean[][] visited;
    static int M;
    static int N;
    static int result=0;

    public static void find(int x, int y){
        int[] add_x = {1,0,-1,0};
        int[] add_y = {0,1,0,-1};
        
        visited[x][y] = true;
        for(int i=0; i<4; i++){
            int temp_x = x + add_x[i];
            int temp_y = y + add_y[i];

            if(0<=temp_x && temp_x<=M-1 && 0<=temp_y && temp_y<=N-1){
                //System.out.println("??: "+ (x+add_x[i]) +"??: "+(y + add_y[i]));
                if( arr[temp_x][temp_y] == 1 && !visited[temp_x][temp_y]){
                    find(temp_x,temp_y);
                }
            }
        } 
    }
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt(); //테스트케이스

        for(int q=0; q<T; q++){

            M = sc.nextInt(); //가로
            N = sc.nextInt(); //세로
            int K = sc.nextInt(); //배추개수

            arr = new int[M][N];
            visited = new boolean[M][N];
            
            for(int i=0; i<K; i++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                arr[a][b] = 1;
            }

            // for(int i=0; i<N; i++){
            //     for(int j=0; j<M; j++){
            //         System.out.print(arr[j][i]+" ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(!visited[j][i] && arr[j][i]==1){
                        find(j,i);
                        result++;
                    }
                }
            }
            sb.append(result +"\n");
            result=0;

        }
        System.out.print(sb);
        
    }     
}
