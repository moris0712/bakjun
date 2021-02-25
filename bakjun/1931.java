import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static ArrayList<Point> arr = new ArrayList<Point>();

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt(); //테스트케이스

        Point p = new Point();

        for(int i=0; i<N; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr.add(new Point(a,b));
        }
        //정렬
        Collections.sort(arr, new Comparator<Point>(){
            @Override
            public int compare(Point o1, Point o2){
                if(o1.y == o2.y){
                    if(o1.x < o2.x)
                        return -1;
                    else
                        return 1;
                }
                else if(o1.y < o2.y){
                    return -1;
                }
                else return 1;
            }
        });


        int count = 0;
        int front = arr.get(0).x;

        for(int i=0; i<arr.size(); i++){
            if(front<=arr.get(i).x){
                count++;
                front = arr.get(i).y;
            }
        }


        //ArrayList<Point> res = new ArrayList<Point>();

        // while(!arr.isEmpty()){
        //     front = arr.get(0).y;
        //     //res.add(new Point(arr.get(0).x , arr.get(0).y));
        //     arr.remove(0);
        //     for (int i = 0; i < arr.size(); i++) {
        //         if (front > arr.get(i).x) {
        //             arr.remove(i);
        //             i--;
        //         }
        //     }
        //     count++;
            
        // }
        System.out.println(count);
    }     
}
