package LinkedList;

public class PrintNthFromEnd extends Node{

	public PrintNthFromEnd(int d) {
		super(d);
		
	}
	
	public static int NoOfElementsInLinkedList(Node head){
		int length =0;
		if( head == null){
			System.out.println("Empty List");
		}
		while(head != null){
			length++;
			head = head.next;
		}
		return length;
	}
	
	public static void printKthElement(int index){
		int length = NoOfElementsInLinkedList(head) ;
		System.out.println(length);
		int count =0;
		while( head != null && count != length -index){
			head = head.next;
			count++;
		}
		System.out.println(" kth element "+head.data);
	}

	public static void main(String[] args) {
		head = new Node(10);
		head.next = new Node(20);
		head.next.next = new Node(30);
		NoOfElementsInLinkedList(head);
		printKthElement(3 );
	}

}
