
public class FibonacciSeries {

	/** using iteration**/
	public static int fibonacciFormula(int endElement){
		int firstElement=0, secondElement =1;
		int thirdElement=0;
		System.out.print("Using iteration method , the element is");
		for(int i = 2 ; i<=endElement;i++){
			thirdElement= 	firstElement +	secondElement;
			firstElement = secondElement;
			secondElement = thirdElement;
		}
		return thirdElement;
	}
	
	/** using array**/
	public static void fibonacciloop(int end, int[] a){
		for( int i =2; i<end;i++){
			a[i] =a[i-1]+a[i-2];	
		}
		printarray(a,end);
	}
	
	/**print array**/
	public static void printarray(int[] a, int end)
	{
		System.out.print("\n The fibonacci series using array is");
		for(int i =0;i<end;i++){
			System.out.print(a[i] + " ");
		}
	}

	/** using recursion**/
	
	public static int recfibonacci(int end){
		if( end < 0)
			return -1;
		else if( end==0){
			return 0;
		}
		else if( end==1){
			return 1;
		}	
		return recfibonacci(end-1)+recfibonacci(end-2);
	}
	
	
	public static int memonizationrecursive(int n){
		int f[] = new int[n+2];
		f[0]= 0;
		f[1] =1;
		for(int i =2 ;i<=n ;i ++){
		f[i]=f[i-1]+f[i-2];
		}
		return f[n];
	}
	
	
	public static void main(String[] args) {
		int[] a = {0,1,0,0,0,0,0,0,0,0,0};
		System.out.println(fibonacciFormula(10));
		fibonacciloop(10,a);
		int sum = recfibonacci(10);
		System.out.println(" \n the output using recursive is" + sum);
		System.out.println(" the value using memonizatio is " +memonizationrecursive(10));
	}

}
