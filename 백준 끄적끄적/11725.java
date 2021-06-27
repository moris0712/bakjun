import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Integer>> arr;
    static boolean visited[];
    static ArrayList<ArrayList<Integer>> root;

    public static void tree (int start){

        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);

        while(!q.isEmpty()){
            int pop = q.poll();
            if(visited[pop]==false){
                visited[pop]=true;
                for(int a : arr.get(pop)){
                    q.add(a);
                    if(visited[a]==false) // 큐에서 꺼낸곳이 방문했던곳이면 루트, 방문했던곳이 아니면 자식
                        root.get(a).add(pop);
                }
            }
            
        }
        //System.out.println(root);
        for(int i=2; i<arr.size(); i++)
            sb.append(root.get(i).get(0)+"\n"); // root를 어레이리스트말고 배열로 저장했어도 됬을듯
        

    }

    public static void main(String[] args) throws IOException{

        int N = sc.nextInt();

        visited = new boolean[N+1];
        root = new ArrayList<>();
        arr = new ArrayList<>();

        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<Integer>());
            root.add(new ArrayList<Integer>());
        }

        for(int i=0; i<N-1; i++){
            int temp_a = sc.nextInt();
            int temp_b = sc.nextInt();
            arr.get(temp_a).add(temp_b);
            arr.get(temp_b).add(temp_a);
        }
        //System.out.println(arr);

        tree(1);
        System.out.print(sb);
        
    }
}




