package LinkedList;

public class PalindromeLinkedlist {



	public static boolean isPalindrome( Node head){
		Node first = head;
		Node last = head;
		int count = 0;
		int j=0;

		while( last.next != null){			
			last =last.next;
			count++;
		}


		for(int i = 0; i< count/2; i++ ){
			if( last.data == first.data){
				last = last.previous;
				first = first.next;
				j++;
			}
		}

		if( j == count/2)
			return true;
		else
			return false;

	}

	public static Node listReverse(Node head ){
		Node current = null;
		while(head != null){
			Node head1 = new Node(head.data);
			head1.next = current;
			if (current!=null){
				current.previous = head1;
			}
			current = head1;
			head = head.next;
		}
		
		return current;
	}
	
	public static void main(String[] args) {
		AppendNode list  = new AppendNode();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(2);
		list.add(1);
		boolean isPalindrom = isPalindrome(list.head);

		System.out.println("Is given list palindrome? " + isPalindrom);
		Node rerversedList =listReverse(list.head);
		
		AppendNode reverseLinkedList = new AppendNode();
		reverseLinkedList.head = rerversedList;
	}

}
