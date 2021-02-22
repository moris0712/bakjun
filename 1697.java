import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static boolean[] visit;

    static Queue<Point> q = new LinkedList<Point>();

    public static int find(int n, int k){

        int time =0;
        int temp;
        int result=0;
        Point p = new Point();
        q.add(new Point(n,time));
        while(!q.isEmpty()){
            p = q.poll();
            temp = p.x;
            time = p.y;
            for(int i=0; i<3; i++){
                n = temp;
                if(i==0){
                    if(n<k)
                        n=n+1;
                }
                else if(i==1){
                    n=n-1;
                }
                else{
                    if(n<k && n*2<= 100000)
                        n=2*n;
                }
                if(n>=0){
                    if(!visit[n]){
                        visit[n] = true;
                        if(n==k){
                            time++;
                            result = time;
                            break;
                        }
                        q.add(new Point(n,time+1));
                    }
                }
            }
        }
        return result;       

    }

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        visit = new boolean[100001];
        visit[N]=true;

        System.out.println(find(N,K));
    }     
}
