import java.util.*;
import java.io.*;
import java.awt.*;

public class Main {

    static ArrayList<ArrayList<Integer>> arr;
    static Queue<Integer> q;
    static int BFS_visited [];
    static int DFS_visited[];
    static StringBuilder sb = new StringBuilder();
    static StringBuilder sb2 = new StringBuilder();
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        

        int N = sc.nextInt();
        int M = sc.nextInt();
        int V = sc.nextInt();

        arr = new ArrayList<>();

        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            int point1 = sc.nextInt();
            int point2 = sc.nextInt();
            arr.get(point1).add(point2);
            arr.get(point2).add(point1);
            //양 방향이므로 서로 넣어준다.
        }
        sc.close();

        BFS_visited = new int[N + 1];
        BFS_visited[0] = 1; // 0번은 없으므로 상관없 
        DFS_visited = new int[N + 1];
        DFS_visited[0] = 1;
       

        for(int i=0; i<=N; i++)
            Collections.sort(arr.get(i)); // 작은것부터 가니까 arraylist정렬
        
        DFS(V);
        BFS(V);

        System.out.println(sb2.toString());
        System.out.println(sb.toString());

    }

    public static void DFS(int start){
        sb2.append(start + " ");
        DFS_visited[start] = 1;
        for(int i=0; i<arr.get(start).size(); i++){ 
            if(DFS_visited[arr.get(start).get(i)] ==0)
                DFS(arr.get(start).get(i)); // 해당 노드와 이어져있는 다른 노드번호를 가져와방문하지 않았던 노드번호를 재귀함수에 넣는다.
        }
    }

    public static void BFS(int start){
        q = new LinkedList<>();
        int pop = start;
        sb.append(pop + " ");
        q.add(pop);

        while(!q.isEmpty()){
            //System.out.println("visited:  " +Arrays.toString(BFS_visited));
            if(BFS_visited[pop]==0){
                for (int i = 0; i < arr.get(pop).size(); i++) {
                    if (BFS_visited[arr.get(pop).get(i)] == 0)
                        q.add(arr.get(pop).get(i)); // 방문하지 않았던 노드번호를 q에 하나씩 넣는다.
                }
            }
            //System.out.println(q);
            BFS_visited[pop] = 1;
            if (q.size() != 0)
                pop = q.poll(); 
            if( BFS_visited[pop] == 0)
                sb.append(pop + " ");

            

            

        }

        

        
    }

 

}
