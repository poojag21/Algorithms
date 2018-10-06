import java.util.Scanner;

public class BoothsAlgorithm {
	/** method to convert decimal to binary**/
	public int[] decimalTobinary( int dec){
		int[] binary = new int[8];
		int len = 7;
		if( dec  < 0){
			dec = dec+256;
			while( dec>0){
				binary[len--] = dec%2;
				dec = dec/2;
			}
		}
		else {

			while(dec >0){
				binary[len--] = dec%2;
				dec = dec/2;
			}
		}
		return binary;
	}
	/** method to convert binary to decimal**/
	public void binarytodecimal( int[] multiplier, int m , int q){
		int sum = 0;
		for( int i =15;i>=0;i--){
			int r = (int) (multiplier[i] *  (Math.pow(2,15-i)));     
			sum= sum+r;
		}
		if(m<0 && q<0){
			sum =  (65536+sum);
		}
		
		if(m<0 || q<0){
			sum = - (65536-sum);
		}
		System.out.println("\n");
		System.out.println("The result is");
		System.out.print(sum);
	}

	/** method to add two binary numbers**/
	public void addbinary(int[] a , int[] b){
		int carry=0;
		for( int i= 15; i>=0 ; i--){
			int z = a[i]+b[i] + carry;
			a[i] = z%2;
			carry = z/2; 		 
		}
	}

	/** method to print binary**/
	public void printbinary(int[] bin){
		System.out.println();
		for(int i = 0 ; i< 16; i++){
			if( i==8)
				System.out.print("  ");
			System.out.print( bin[i]);
		}
	}
	/** method to right shift the resultant multiplier**/
	public void rightshiftmultiplier(int[] multiplier)
	{ 
		for (int i = 15; i >0 ; i--)
			multiplier[i] = multiplier[i - 1]; 
	}

	/** method to multiply multiplicand with multiplier **/
	public void multiplybinary( int m , int q, int z){
		int[] m1 = decimalTobinary(m);
		int[] m2 = decimalTobinary(-m);
		int[] q1 = decimalTobinary(q);

		int[] multiplicand = new int[16];
		int[] cmultiplicand = new int[16];
		int[] multiplier = new int[16];
		for (int i =0 ; i < 8; i++)
		{
			multiplicand[i] = m1[i];
			cmultiplicand[i] = m2[i];
			multiplier[i+8 ] = q1[i];
		}
		System.out.println("The Multiplicand is ");
		printbinary(multiplicand);
		System.out.println();
		System.out.println ("The Complement of Multiplicand is ");
		printbinary(cmultiplicand);
		System.out.println();
		System.out.print ("The Multiplier is ");
		printbinary(multiplier);
		for( int i = 0 ; i < 8; i++){
			if (multiplier[15]==1 && z==0)
				addbinary(multiplier,cmultiplicand);
			else if (multiplier[15]==0 && z==1)
				addbinary(multiplier,multiplicand);	
			z = multiplier[15];
			rightshiftmultiplier(multiplier);
			printbinary(multiplier);
		}
	
		System.out.println("\n The final answer is");
		printbinary(multiplier);
		binarytodecimal(multiplier,m,q);
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		BoothsAlgorithm ba = new BoothsAlgorithm();
		System.out.println(" Enter the decimal multiplicand");
		int m = scan.nextInt();
		System.out.println(" Enter the decimal multiplier");
		int q = scan.nextInt();
		ba.multiplybinary(m,q,0);
	}

}
