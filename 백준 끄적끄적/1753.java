import java.util.*;
import java.io.*;

public class bbb {

    static ArrayList<ArrayList<Node>> arr;
    static int visited [];
    static int weight [];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();
        int K = sc.nextInt();

        arr = new ArrayList<>();
        visited = new int [V+1];
        weight = new int [V+1];
        Arrays.fill(weight, -1);

        for(int i=0; i<=V; i++){
            arr.add (new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            int start = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            arr.get(start).add(new Node(dest,weight));
        }

        BFS(K);
    }


    public static void BFS(int start){
        
        //Queue<Node> q = new LinkedList<>();
        PriorityQueue<Node> q = new PriorityQueue<>(); // 우선순위 큐를 써야 weight짧은것부터 계산하려 하는듯
        q.add(new Node(start,0));
        weight[start]=0;

        while(!q.isEmpty()){
            Node pop = q.poll();
            
            if(visited[pop.dest]==0){
                visited[pop.dest] = 1;
                for(Node nd : arr.get(pop.dest) ){ // arr.get(pop.dest)에 있는 노드들을 nd로 가져옴
                    //System.out.println(nd.dest+"     "+ weight[nd.dest] +"     " + (weight[pop.dest]+nd.weight));
                    if(weight[nd.dest]==-1 || weight[nd.dest] > weight[pop.dest]+nd.weight){
                        // 다음 목적지인 nd.dest 까지의 최소거리보다 > (현재 pop.dest의 목적지까지의 최소거리 + pop.dest 까지의 거리)가 작으면 값 대체 
                        weight[nd.dest] = weight[pop.dest]+nd.weight;
                        q.add(new Node(nd.dest, weight[nd.dest]));
                    }
                        
                }
            }
        }
        //System.out.println(Arrays.toString(weight));
        for(int i=1; i<weight.length; i++){
            int temp = weight[i]; 
            if(weight[i]==-1){
                sb.append("INF");
            }
            else
                sb.append(temp);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }


    static class Node implements Comparable<Node>{

        int dest, weight;

        Node(int dest, int weight){
            this.dest = dest;
            this.weight = weight;
        }
        
        public int compareTo(Node o) { // weight 기준으로 우선순위 비교
            return this.weight - o.weight;
        }
    }
}
// 6 6 
// 1 
// 1 2 1 
// 2 5 1 
// 5 6 1 
// 1 3 10 
// 3 4 5 
// 6 3 1