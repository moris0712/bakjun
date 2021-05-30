import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt(); //테스트케이스
        for(int i=0; i<T; i++){
            int K = sc.nextInt();
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for(int j=0; j<K; j++){
                String a = sc.next();
                int b = sc.nextInt();

                if(a.equals("I")){
                    if(!map.containsKey(b)){
                        map.put(b,1);
                    }
                    else{
                        map.put(b,1+map.get(b));
                    }
                }
                else if(a.equals("D")){
                    if(map.isEmpty()){
                        continue;
                    }
                    if(b==1){
                        if(map.get(map.lastKey())-1==0)
                            map.remove(map.lastKey());
                        else
                            map.put(map.lastKey(),map.get(map.lastKey()-1));
                    }
                    if(b==-1){
                        if (map.get(map.firstKey()) - 1 == 0)
                            map.remove(map.firstKey());
                        else
                            map.put(map.firstKey(), map.get(map.firstKey() - 1));
                    }
                }
            }
            if (map.isEmpty())
                sb.append("EMPTY"+"\n");
            else
                sb.append(map.lastKey() + " " + map.firstKey()+"\n");
        }
        System.out.print(sb);
        
    }     
}

