import java.util.*;
import java.io.*;

public class kkk {

    public static class Point{

        int my_pos;
        int count;

        public Point(int my_pos, int count){
            this.my_pos = my_pos;
            this.count = count;
        }

        public String toString() {
            return "{" + my_pos + " , " + count + "}  ";
        }
    }

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); 
        int K = sc.nextInt();

        find(N,K);
    }     

    public static void find(int N, int K ){

        Queue<Point> q = new LinkedList<Point>();
        boolean visited[] = new boolean[200001];
        int ans = -1;
        int how_many = 0;

        q.add(new Point(N,0));
        
        while(!q.isEmpty()){

            Point p = q.poll();
            visited[p.my_pos] = true;

            if(p.my_pos == K){
                if(how_many==0)
                    ans = p.count;
                if(ans==p.count)
                    how_many++;
            }
            if(ans!=-1 && ans<p.count)
                break;

            if (p.my_pos-1>=0 && !visited[p.my_pos - 1])
                q.add(new Point(p.my_pos-1 , p.count+1));

            if (p.my_pos+1<=100001 && !visited[p.my_pos + 1]  )
                q.add(new Point(p.my_pos+1 , p.count+1));

            if (p.my_pos * 2 <= 200001 && !visited[p.my_pos * 2] )
                q.add(new Point(p.my_pos*2 , p.count+1));
            
                

            // System.out.println(" " + p.my_pos + " " + p.count);

        }
        System.out.println(ans);
        System.out.println(how_many);
    } 
}

