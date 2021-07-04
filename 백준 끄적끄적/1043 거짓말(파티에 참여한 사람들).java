import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static ArrayList<ArrayList<Integer>> arr;
    static boolean know[];

    public static void main(String[] args) throws IOException{

        int N = sc.nextInt(); // 사람 수
        int M = sc.nextInt(); // 파티 수

        int know_count = sc.nextInt();
        int host[] = new int[know_count]; // 최초로 거짓말을 알고있는 사람번호를 모아둔 배열

        know = new boolean [N+1]; // 해당 번호의 사람이 지민이의 거짓말을 알고있는지 T/F 
        
        for(int i=0; i<know_count; i++){
            int temp = sc.nextInt();
            //know[temp] = true;
            host[i] = temp;
        }

        arr = new ArrayList<>(); // 그래프는 어레이 리스트


        for(int i=0; i<=N; i++){
            arr.add(new ArrayList<>());
        }

        int [][] party = new int [M][]; // 이게되는지는 처음알았다..

        for(int i=0; i<M; i++){
            int join_people_count = sc.nextInt();

            party[i] = new int [join_people_count]; // 배열 party 행은 파티 수(M) 열은 해당 파티에 참여한 사람 번호    
            party[i][0] = sc.nextInt();  // 첫번째 파티에참여한 사람 번호

            for(int k=1; k<join_people_count; k++){
                party[i][k] = sc.nextInt(); // 두번째부터 파티에참여한 사람 번호

                for(int j=k; j>0; j--){                                  //  예를들어 파티에 참여한 사람이 4명이고 2 4 6 7번이라면 
                    if(!arr.get(party[i][k]).contains(party[i][j - 1]))  //  j가 1일때, 어레이 리스트에 2->4 , 4->2 추가
                        arr.get(party[i][k]).add(party[i][j-1]);         //  j가 2일때, 어레이 리스트에 4->6 , 6->4 , 2->6, 6->2 추가
                    if(!arr.get(party[i][j-1]).contains(party[i][k]))    //  j가 3일때, 어레이 리스트에 6->7 , 7->6 , 4->7, 7->4 , 2->7, 7->2 추가
                        arr.get(party[i][j-1]).add(party[i][k]);         //  if문을 사용한것은 중복 숫자가 들어갈수있기때문에 
                }
            }

            
        }
        // 여기까지 파티에 참여한 사람들끼리 서로 연결한 어레이리스트가 완성되었음

        Queue<Integer> q = new LinkedList<Integer>();

        for(int i=0; i<host.length; i++){
            q.add(host[i]); // 최초로 지민이의 허언증을 알고있는 사람을 큐에 추가
        }
        // System.out.println(q);
        while(!q.isEmpty()){ 
            int pop = q.poll();

            if(!know[pop]){  // 파티에 지민이의 허언증을 알고있는 사람이 있으면 파티에 참여한 사람모두 지민이의 허언증을 알게된다.
                know[pop] = true;
                for(int n : arr.get(pop) ){ 
                    q.add(n); 
                }
            }
        }

        int result =0;
        for(int i=0; i<M; i++){
            boolean lie_able = true;  // 지민이가 거짓말을 할수있다를 기본값으로 둔다.

            for(int k : party[i]){ // 파티에 참여한 사람모두를 지민이의 허언증을 아는지 검사한다.
                if(know[k]){ 
                    lie_able = false; // 참여한 사람중 한명이라도 지민이의 허언증을 알때 거짓말을 할수 없으므로 그 파티는 break로 빠져나간다.
                    break;
                }
            }

            if (lie_able) {
                result++;
            }
        }
        System.out.println(result);

        // System.out.println(Arrays.deepToString(party));

        // System.out.println(arr);
        // System.out.println(Arrays.toString(know));



    }
}




