import java.util.Scanner;

public class RestoringBinaryDivision {

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

	/** Method to print binary number**/
	public void printbinary(int[] bin){
		for(int i = 0 ; i< 8; i++){
			if( i==4)
				System.out.print("  ");
			System.out.print( bin[i]);
		}
	}

	/** Method to print binary number**/
	public void printbinary16(int[] bin){
		for(int i = 0 ; i< 16; i++){
			if( i==8)
				System.out.print("  ");
			System.out.print( bin[i]);
		}
	}

	/** method to add two binary numbers**/
	public int[] addbinary(int[] a , int[] b){
		int carry=0;
		for( int i= 7; i>=0 ; i--){
			int z = a[i]+b[i] + carry;
			a[i] = z%2;
			carry = z/2; 		 
		}
		return a;
	}

	/** method to convert binary to decimal**/
	public int binarytodecimal( int[] binaryValue){
		int sum = 0;
		for( int i =7;i>=0;i--){
			int r = (int) (binaryValue[i] *  (Math.pow(2,7-i)));     
			sum= sum+r;
		}

		return sum;
	}

	/** method to left shift the resultant multiplier**/
	public void leftshiftdividend(int[] dividend)
	{ 
		for (int i = 0; i <=14 ; i++){
			dividend[i] = dividend[i+1];
		}
		dividend[15] =0;

	}
	/** division operation on divisor and dividend**/
	public void division(int dividend,int divisor){

		boolean isDivisorNegative = false;
		boolean isDividendNegative = false;


		if(dividend< 0 && divisor <0){ 
			isDivisorNegative =true;
			isDividendNegative = true;
		}

		else if(dividend< 0 && divisor > 0){ 
			isDividendNegative = true;
		}

		else if(dividend > 0 && divisor < 0){ 
			isDivisorNegative = true;
		}

		if(dividend < 0){
			dividend = -dividend;
		}

		int[] Q1 = decimalTobinary(dividend);

		if(divisor<0){
			divisor = -divisor;
		}
		int[] M1 = decimalTobinary(-divisor);
		int[] D = decimalTobinary(divisor);
		
		int[] A = new int[8];
		int[] M = new int[8];
		int[] Q = new int[16];
		int[] Qnew = new int[8];
		int[] Qnew1 = new int[8];
		int[] R = new int[8];
		int[] M2 = new int[8];

		int numberOfOperation = 8;

		for (int i =0 ; i < 8; i++)
		{
			M[i] = M1[i];
			Q[i+8] = Q1[i];
			Qnew[i] = Q1[i];
			M2[i] = D[i];
		}
		System.out.print("\n The binary value of the Divisor is ");
		printbinary(M2);
		
		System.out.print( "\n The initial binary value of the M is " );
		printbinary(M);
		
		System.out.print( "\n The initial binary value of the Q is " );
		printbinary(Qnew);
	
		System.out.print( "\n The initial binary value of the A is " );
		printbinary(A);
		

		while(numberOfOperation>0){
			leftshiftdividend(Q);
			System.out.println();
			for(int i =0; i< 8; i++){
				A[i] = Q[i];
			}			

			int[] Anew = addbinary(Q,M);
			System.out.println( "  ");
			if(Anew[0] == 1){
				for(int i =0; i< 8; i++){
					Q[i] = A[i];
				}
				Q[15]=0;
			}
			else{
				Q[15]= 1;
			}
			printbinary16(Q);
			numberOfOperation--;
		}

		System.out.println(" is the final value in register A and Q respectively");
		
		for( int i =0; i< 8;i++){
			A[i]=Q[i];
		}
		int remainder =binarytodecimal(A);

		for( int i =8; i< 16;i++){
			Qnew[i-8] = Q[i];
		}

		int Quotient= binarytodecimal(Qnew);

		if(isDivisorNegative == false && isDividendNegative == false){

			System.out.print("\n The Quotient is the value in Q which is equal to  ");
			printbinary(Qnew);

			System.out.print("\n The Remainder is the value in A which is equal to  ");
			printbinary(A);
			System.out.println("\n The Output of the operation is " + Quotient+"R"+remainder);
		}


		else if(isDivisorNegative == true && isDividendNegative == false){
			
			System.out.println("\n As dividend is positive and divisor is negative, the value of Quotient is 2's complement of the value of Q");
			System.out.print("\n The Quotient is the value in Q which is equal to  ");
			int[] N = decimalTobinary(-Quotient);
			for( int i =0; i< 8;i++){
				Qnew1[i] = N[i];
			}
			printbinary(Qnew1);
			int Quotientnew= Quotient * -1;
			
			System.out.print("\n The Remainder is the value in A which is equal to  ");
			printbinary(A);
			
			System.out.println("\n The output of the operation is " + Quotientnew+"R"+remainder);
		}

		else if(isDivisorNegative == true && isDividendNegative == true){
			System.out.println("\n As both dividend and divisor are negative, the value of remainder is 2's complement of the value of A");
			System.out.print("\n The Quotient is the value in Q which is equal to  ");
			printbinary(Qnew);
			
			System.out.print("\n The Remainder is the value in A which is equal to  ");
			int[] O = decimalTobinary( -remainder);
			for(int i = 0; i< 8; i++){
				R[i] =O[i]; 
			}
			int remaindernew = remainder * -1;
			printbinary(R);
			System.out.println("\n The output of the operation is " + Quotient+"R"+remaindernew);
		}
		else if(isDivisorNegative == false && isDividendNegative == true){
			
			System.out.println("\n As dividend is negative and divisor is positive, the value of Quotient and Remainder is 2's complement of the value of Q and A");

			System.out.print("\n The Quotient is the value in Q which is equal to  ");
			int[] N = decimalTobinary(-Quotient);
			for( int i =0; i< 8;i++){
				Qnew1[i] = N[i];
			}
			printbinary(Qnew1);
			int Quotientnew= Quotient * -1;

			int[] O = decimalTobinary( -remainder);
			for(int i = 0; i< 8; i++){
				R[i] =O[i]; 
			}
			System.out.print("\n The Remainder is the value in A which is equal to  ");
			printbinary(R);
			int remaindernew = remainder * -1;
			
			System.out.println("\n The output of the operation is" + Quotientnew+"R"+remaindernew);
		}

	}	   

	public static void main(String args[]){
		RestoringBinaryDivision restore = new RestoringBinaryDivision();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the dividend");
		int dividend = scan.nextInt();
		System.out.println("Please enter the divisor");
		int divisor = scan.nextInt();
		System.out.println( "The dividend is " + dividend + " and the divisor is " +divisor);		
		restore.division(dividend,divisor);
		scan.close();
	}

}
