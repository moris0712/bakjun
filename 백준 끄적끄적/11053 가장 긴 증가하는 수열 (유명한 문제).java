import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // 수열 순서를 바꾸면 안되고 있는상태에서 {10, 21, 1, 10, 20, 30, 40, 50, 60}
        // ans => 1, 10, 20, 30, 40, 50, 60   len : 7
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int sub [] = new int [N]; // 숫자가 저장되는 배열
        int sub_max_count [] = new int [N]; // 최대 부분수열의 길이가 저장되는 배열
        for(int i=0; i<N; i++){
            sub[i] = sc.nextInt();
            sub_max_count[i] = 1; // 최대 부분수열의 길이배열 1로 초기화
        }

        for(int i=1; i<N; i++){
            int max_count = sub_max_count[0]; // 1
            for(int k=0; k<i; k++){
                if(sub[k] < sub[i]){ 
                    max_count = Math.max(max_count, sub_max_count[k]+1);
                    // 처음부터 i번째 까지 다시검사하여 i번째보다 낮은 숫자한에서 
                    // 부분수열의 길이가 가장큰것과 i번째 부분수열길이 + 1 를 비교해서 최대값을 가져간다.
                }
            }
            sub_max_count[i] = max_count;
            
        }

        int max = sub_max_count[0];
        for(int i : sub_max_count){
            max = Math.max(max,i);
        }
        // System.out.println(Arrays.toString(sub));
        // System.out.println(Arrays.toString(sub_max_count));
        System.out.println(max);


        

    
        

        
    }
}
