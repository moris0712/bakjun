import java.util.*;
import java.io.*;

public class Main {

    static int arr[];
    static int N;
    static int M;
    static int print_arr[];
    static boolean visited[];

    public static void sequence(int my_pos, int depth) {

        if(depth == M){
            for(int val: print_arr)
                System.out.print(val + " ");
            System.out.println();
            return;
        }        

        int past = -1; // 다음 depth로 갈때는 과거값 초기화
        for(int i=my_pos; i<N; i++){
            print_arr[depth] = arr[i];
            if(!visited[i] && past!=arr[i]){
                sequence(i, depth+1);
                past = arr[i]; // for문 돌때만 past 값 업데이트
            }
        }

    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        visited = new boolean[N];
        print_arr = new int[M];


        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        Arrays.sort(arr);

        sequence(0, 0);

    }
}
