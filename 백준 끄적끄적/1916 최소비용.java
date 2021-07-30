import java.util.*;
import java.io.*;

public class aaa {

    static class Point implements Comparable<Point>{

        int dest;
        int cost;

        public Point(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
        
        public int compareTo(Point p) { // cost 기준으로 우선순위 비교
            return this.cost - p.cost;
        }

        public String toString() {
            return "{" + dest + " , " + cost + "}  ";
        }
    }

    static ArrayList<ArrayList<Point>> arr = new ArrayList<ArrayList<Point>>();
    static boolean visited[]; 

    public static void DFS(int start, int dest){

        PriorityQueue<Point> q = new PriorityQueue<>();

        q.add(new Point(start,0));

        while(!q.isEmpty()){

            Point p = q.poll();

            if(p.dest==dest){
                System.out.println(p.cost);
                break;
            }

            if(!visited[p.dest]){
                visited[p.dest] = true;
                for( Point linked : arr.get(p.dest) ){
                    q.add(new Point(linked.dest,p.cost + linked.cost));
                }
            }
        }

    }
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<Point>());
        }
        for(int i=0; i<M; i++){
            int start = sc.nextInt();
            int dest = sc.nextInt();
            int cost = sc.nextInt();
            arr.get(start).add(new Point(dest,cost));
        }
        int ans_start = sc.nextInt();
        int ans_dest = sc.nextInt();

        DFS(ans_start,ans_dest);
        // System.out.println(arr);
    }

}
