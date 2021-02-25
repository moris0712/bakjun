import java.util.*;
import java.io.*;
import java.awt.*;
public class Main {

    static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt(); //테스트케이스

        for(int i=0; i<N; i++){
            int temp = sc.nextInt();
            if(temp>0)
                queue.add(temp);
            else if(temp==0){
                if(queue.isEmpty()){
                    sb.append(0+"\n");
                }
                else{
                    sb.append(queue.peek()+"\n");
                    queue.poll();
                }
            }
        }
        System.out.print(sb);

    }     
}

