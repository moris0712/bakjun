import java.util.*;
import java.io.*;

public class ccc {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int N,M ;

    public static void sequence(int my_pos, int depth){

        if(depth == M){
            for(int val : arr){
                System.out.print(val+" ");
            }
            System.out.println();
            return;
        }


        for(int i=my_pos; i<=N; i++){
            arr[depth] = i;
            sequence(i + 1,depth + 1);
        }



    }
    public static void main(String[] args) throws IOException{

        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int [M];

        sequence(1,0);
        
    }
}

// N=4

// arr[0] = 1 seq(2,1)  ->  arr[1] = 2 seq(3,2)  ->  arr[2] = 3 seq(4,3)  -> arr[3] = 4 seq(5,4)     == 1 2 , 1 3 , 1 2 3 , 1 2 3 4
//                                                   arr[2] = 4 seq(5,3)                             == 1 2 4
//                      ->  arr[1] = 3 seq(4,2)  ->  arr[2] = 4 seq(5,3)                             == 1 3 4
//                      ->  arr[1] = 4 seq(5,2)                                                      == 1 4

// arr[0] = 2 seq(3,1)
// arr[0] = 3 seq(4,1)
// arr[0] = 4 seq(5,1)

