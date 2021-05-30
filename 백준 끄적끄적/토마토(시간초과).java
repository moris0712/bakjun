//7576번 토마토 시간초과

import java.util.*;

public class Main{

    public static boolean contain(int target, int[][] keys){

        int start = 0;
        int end = keys[0].length*keys.length-1;

        while(start<=end) {
            int mid = (start+end)/2;
            int midX = mid/keys[0].length;
            int midY = mid%keys[0].length;
            if(keys[midX][midY] == target) {
                return true;
            }
            if(keys[midX][midY] < target) {
                start = mid + 1;
            } 
            else {
                end = mid - 1;
            }
        }
        return false;
    }



    public static void change(int[][] keys){

        ArrayList <Integer> hor = new ArrayList<Integer> ();
        ArrayList <Integer> ver = new ArrayList<Integer> ();

        for (int i = 0; i < keys[0].length; i++){
            for(int k = 0; k < keys.length; k++){
                if(keys[k][i]==1){
                    hor.add(k);
                    ver.add(i);
                }
            }
        }

        for (int i=0; i < hor.size(); i++){
            if( hor.get(i)-1>=0 && keys[hor.get(i)-1][ver.get(i)]!=-1)//keys.length = 6
                keys[hor.get(i)-1][ver.get(i)]=1;     //keys[0].length = 4
            if( ver.get(i)-1>=0 && keys[hor.get(i)][ver.get(i)-1]!=-1)
                keys[hor.get(i)][ver.get(i)-1]=1;
            if( hor.get(i)+1<=keys.length-1 && keys[hor.get(i)+1][ver.get(i)]!=-1){
                keys[hor.get(i)+1][ver.get(i)]=1;
            }
            if( ver.get(i)+1<=keys[0].length-1 && keys[hor.get(i)][ver.get(i)+1]!=-1)
                keys[hor.get(i)][ver.get(i)+1]=1;
        }

    }
 

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int hor = sc.nextInt();
        int ver = sc.nextInt();
        int time =0;

        int[][] box = new int[hor][ver];

        for(int i=0; i<ver; i++){
            for(int k=0; k<hor; k++){
                box[k][i] = sc.nextInt();
            }
        }



        while(contain(0,box)==true){
            change(box);
            time++;

            // System.out.println("");
            // for(int i=0; i<ver; i++){
            //     for(int k=0; k<hor; k++){
            //         System.out.print(box[k][i]+" ");
            //     }
            //     System.out.println("");
            // }

            if(time>hor * ver){
                time=-1;
                break;
            }
        }
        System.out.println(time);

        
        
    }
}