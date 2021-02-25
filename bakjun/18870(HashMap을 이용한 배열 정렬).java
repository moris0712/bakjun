import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt(); //테스트케이스
        int[] arr = new int[N];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
            
        for(int i=0; i<N; i++){
            int temp = sc.nextInt();
            arr[i] = temp;
        }
        int[] arr_copy = arr.clone();
        Arrays.sort(arr_copy);

        int order =0;
        for(int n : arr_copy){
            if(!map.containsKey(n)){
                map.put(n,order++);
            }
        }

        for(int n : arr){
            sb.append(map.get(n)+" ");
        }
        System.out.println(sb);
    }     
}

