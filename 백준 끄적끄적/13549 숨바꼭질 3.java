import java.util.*;
import java.io.*;

public class Main {

    static class Point implements Comparable<Point>{

        int pos;
        int count;

        public Point(int pos, int count) {
            this.pos = pos;
            this.count = count;
        }

        public int compareTo(Point p) { // count 기준으로 우선순위 비교
            return this.count - p.count;
        }

        public String toString(){
            return "{"+pos+" , "+count+"}  ";
        }
    }

    static boolean[] visited = new boolean[100001]; // 우선순위 큐 이므로 visited가 true 인것은 count가 가장 작을때임

    public static void hide_and_seek(int N, int K){
        
        PriorityQueue<Point> q = new PriorityQueue<>();
        //Queue <Point> q = new LinkedList<Point>();

        q.add(new Point(N,0));

        while(!q.isEmpty()){
  
            Point p = q.poll();
            int here = p.pos;
            int sec = p.count;

            visited[here] = true;

            if(here==K){
                System.out.println(sec);
                break;
            }

            if (here - 1 >= 0 && here - 1 <= 100000 && !visited[here - 1])
                q.add(new Point(here - 1, sec + 1));

            if( here+1 <= K && here+1 <= 100000 && !visited[here + 1])
                q.add(new Point(here + 1 , sec + 1));

            if( here*2 < 2*K && here*2 <= 100000 && !visited[here * 2])
                q.add(new Point(here * 2, sec));


            //System.out.println(q.toString());

        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int K = in.nextInt();

        hide_and_seek(N,K);
    }
}