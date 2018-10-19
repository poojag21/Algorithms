package LinkedList;

public class OneAway {

	public static boolean isOneAway(String name, String word){

		if(name.length() - word.length() == 0 ){
			int count =0;
			for(int i =0; i< name.length();i++){
				if((int)name.charAt(i) != (int)word.charAt(i)){
					count++;
				}
			}
			if( count > 1)
				return false;
			else return true;
		}	
		
		if(name.length() - word.length() == 1){
			int count = 1;
			for(int i =0; i< word.length()-1;i++){
				if((int)name.charAt(i) != (int)word.charAt(i)){
					if((int)name.charAt(i+1) != (int)word.charAt(i)){
					count++;
					}
				}
			}
			if( count >1)
				return false;
			else return true;
		}
		
		if(word.length() - name.length() == 1){
			int count = 1;
			for(int i =0; i< name.length()-1;i++){
				if((int)word.charAt(i) != (int)name.charAt(i)){
					if((int)word.charAt(i+1) != (int)name.charAt(i)){
					count++;
					}
				}
			}
			if( count >1)
				return false;
			else return true;
		}
		
		else
			return false;

	}

	public static void main(String args[]){
		String name = "PALES", word1 ="PLE", word2 ="RALE", word3="BAKE", word4 ="PALESS";
		System.out.println(isOneAway(name,word1));
		System.out.println(isOneAway(name,word2));
		System.out.println(isOneAway(name,word3));
		System.out.println(isOneAway(name,word4));


	}

}
