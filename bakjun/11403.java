import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    static boolean visited[];
    static Queue<Integer> q = new LinkedList<Integer>();
    static ArrayList<ArrayList<Integer>> arr;
    static int start;
    static int [][] result;

    public static void find (int n,int target){

        for(int i=0; i<arr.get(n).size(); i++){
            if(!visited[arr.get(n).get(i)]){
                visited[arr.get(n).get(i)]=true;
                if(arr.get(n).get(i)==target){
                    result[start-1][arr.get(n).get(i)-1] = 1;
                    break;
                }
                else
                    find(arr.get(n).get(i),target);
            }
        }

    } 
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        arr = new ArrayList<>();

        for(int i=0; i<=n; i++)
            arr.add(new ArrayList<Integer>());

        result = new int[n][n];

        for(int i=1; i<=n; i++){
            for(int k=1; k<=n; k++){
                int a = sc.nextInt();
                if(a==1){
                    arr.get(i).add(k);
                }
            }
        }
        visited = new boolean[n+1];

        for(int i=1; i<=n; i++){
            for(int k=1; k<=n; k++){
                visited = new boolean[n+1];
                start = i;
                find(i,k);
            }
        }

        for(int i=0; i<n; i++){
            for(int k=0; k<n; k++){
                System.out.print(result[i][k]+" ");
            }
            System.out.println();
        }
    }     
}
