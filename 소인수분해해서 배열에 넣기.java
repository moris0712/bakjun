import java.util.*;

public class Main{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		int numnum= 2;

		Number[] number = new Number[numnum];

		String name_temp = "s_list";

		for(int i=0; i<numnum; i++){
			number[i] = new Number(sc.nextInt());
		}

		for(int k=0; k<numnum; k++){
			String gcd="";
			String temp="";

			while(number[k].num!=1){
				for(int i=2; i<=number[k].num; i++){
					if(number[k].num%i==0){
						number[k].num = number[k].num/i;
						temp = Integer.toString(i);
						gcd = gcd + temp;
						break;
					}
				}
			}

			int[] f_list = new int[gcd.length()];
			for(int i=0; i<gcd.length(); i++){
				f_list[i] = Integer.parseInt(gcd.substring(i,i+1));
			}
			for(int i=0; i<gcd.length(); i++){
				System.out.println(f_list[i]);
			}
		}



	}

	public static class Number{

		int num;

		public Number(int num){
			this.num = num;
		}
	}

}
                           

                           
