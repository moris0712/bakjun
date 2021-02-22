import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static int[][] arr;
    static int color;
    static int sum_0=0;
    static int sum_1=0;

    public static void make(int start_x, int end_x, int start_y, int end_y, int len){

        if(!check(start_x,end_x,start_y,end_y,len)){
            make(start_x,start_x+len/2,start_y,start_y+len/2,len/2);
            make(start_x+len/2,start_x+len,start_y,start_y+len/2,len/2);
            make(start_x,start_x+len/2,start_y+len/2,start_y+len,len/2);
            make(start_x+len/2,start_x+len,start_y+len/2,start_y+len,len/2);
        }
        else{
            if(arr[start_x][start_y]==1)
                sum_1++;
            else
                sum_0++;
        }
    }

    public static boolean check(int start_x, int end_x, int start_y, int end_y, int len){

        int color = arr[start_x][start_y];

        for(int i=start_x; i<end_x; i++){
            for(int k=start_y; k<end_y; k++){
                if(arr[i][k] != color)
                    return false;
            }
        }

        return true;
    }
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        arr = new int [N][N];

        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                arr[i][k] = sc.nextInt();
            }
        }

        if(!check(0,N,0,N,N)){
            make(0,N/2,0,N/2,N/2);
            make(N/2,N,0,N/2,N/2);
            make(0,N/2,N/2,N,N/2);
            make(N/2,N,N/2,N,N/2);
        }

        System.out.println(sum_0);
        System.out.println(sum_1);

        //System.out.println(Arrays.deepToString(arr));
    }     
}
