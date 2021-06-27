import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();

    static class Point { // A가 int형 범위 벗어날수있어서 long타입으로 재정의
        long A;
        int count;

        public Point(long A, int count){
            this.A = A;
            this.count = count;
        }
    }

    public static void change(int A, int B){

        Queue<Point> q = new LinkedList<Point>();
        Boolean able=false;
        q.add(new Point(A,1));

        while(!q.isEmpty()){
            Point p = q.poll();
            long pop = p.A;
            if (pop == B) { // A 가 B 가 되었으면 출력
                System.out.println(p.count);
                able = true;
                break;
            }
            if( pop * 2 <= B){ //  A * 2가 B보다 작으면 q에 추가
                q.add(new Point(pop * 2,p.count+1));
            }
            if ( pop * 10 + 1 <= B){ // A * 10 + 1 가 B보다 작으면 q에 추가
                q.add(new Point(pop * 10 + 1,p.count+1));
            }
            //System.out.println(q);
        }
        if(able!=true)
            System.out.println(-1);
    }
    public static void main(String[] args) throws IOException{

        int A = sc.nextInt();
        int B = sc.nextInt();

        change(A, B);
        
    }
}




