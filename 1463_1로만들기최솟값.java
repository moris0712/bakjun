import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static Queue<Point> q = new LinkedList<Point>();
    static boolean visit[];
    static int time=0;

    // public static class Point{
    //     int x;
    //     int y;

    //     public void Point(int x,int y){
    //         this.x = x;
    //         this.y = y;
    //     }
    // }

    public static int make_1(int n, int target){

        int depth =0 ;
        q.add(new Point(n,depth));

        Point p = new Point(n,depth);
        while(n!=target){
            //System.out.println(q);
            p = q.poll();
            int temp = p.x;
            depth = p.y;
            //System.out.println("temp: "+temp);
            //System.out.println("visit: "+Arrays.toString(visit));
            for(int i=0; i<3; i++){
                n = temp;
                if(i==0){
                    if(n%3==0)
                        n = n/3;
                }
                else if(i==1){
                    if(n%2==0)
                        n = n/2;
                }
                else{
                    if(n>1)
                        n = n-1;
                }
                if(n>0){
                    if(!visit[n]){
                        visit[n] = true;
                        if(n==target){
                            depth++;
                            time = depth;
                            break;
                        }
                        //System.out.println("depth: "+depth);
                        q.add(new Point(n,depth+1));
                    }
                }
            }
        }
        return depth;
    }

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();


        visit = new boolean [n+1];

        visit[n] = true;
        System.out.println(make_1(n,1));

    }     
}
