import java.util.*;
import java.io.*;
import java.awt.*;
 
public class Main {

    public static int factorial(int n){

        int[] arr = new int [2];

        int i=2;
        int temp = n+1;

        while(1<=n){
            n = temp - 1;
            i=2;
            while(i<=n){
                if(n%i==0){
                    n = n/i;
                    if(i==2){
                        arr[0]++;
                    }
                    else if(i==5){
                        arr[1]++;
                    }
                }
                else
                    i++;
            }
            temp=temp-1;
        }
        //System.out.println(arr[0]+" "+arr[1]);
        return Math.min(arr[0],arr[1]);
    }
    public static void main(String[] args) throws IOException{

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt(); //테스트케이스

        if(N==0)
            System.out.println(0);
        else
            System.out.println(factorial(N));
    
        
    }     
}
