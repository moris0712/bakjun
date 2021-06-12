import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int coin[] = new int [N];
        int count =0;

        for(int i=0; i<N; i++){
            coin[i] = sc.nextInt();
        }
        //System.out.println(Arrays.toString(coin));
        Arrays.sort(coin);
        //System.out.println(Arrays.toString(coin));

        int remains = K;
        for(int i=N-1; i>=0; i--){
            int share = remains / coin[i];
            //System.out.println("share: " + share);
            remains = remains - share * coin[i];
            //System.out.println("remains: " + remains);
            count += share; 
        }
        System.out.println(count);
    }

}
