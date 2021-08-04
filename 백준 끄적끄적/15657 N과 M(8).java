import java.util.*;
import java.io.*;

public class Main {

    static int arr[];
    static int N;
    static int M;
    static int print_arr[];

    public static void sequence(int my_pos, int depth) {

        if (depth == M) {
            for (int val : print_arr)
                System.out.print(val + " ");
            System.out.println();
            return;
        }

        for (int i = my_pos; i < N; i++) {
            print_arr[depth] = arr[i];
            sequence(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        arr = new int[N];
        print_arr = new int[M];

        for (int i = 0; i < N; i++)
            arr[i] = sc.nextInt();

        Arrays.sort(arr);

        sequence(0, 0);

    }
}
