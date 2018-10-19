package LinkedList;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LinkedListFunctions {

	public Node head = null;

/** method to check if a linked list is a Palindrome **/
	public  boolean isPalindrome( Node head){
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

	public Node listReverse(Node head ){
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
	
/** Method to print a linkedList **/
	
	public void printlist(){
		Node current = head;
		while( current!=null){
			System.out.println(current.data);
			current = current.next;
		}
	}
	
/** adding a Node from a doubly Linked list**/
	public void add( int data){
		Node n = new Node(data);
		if( head == null){
			head = n;
			head.next = null;
			head.previous = null;
		}

		else {
			Node current = head;
			while( current.next != null ){
				current = current.next;
			}
			current.next =n;
			n.previous = current;
			n.next = null;
		}
	}
/** method to delete a node in a singly linked list**/
	
	static Node deleteNode(Node head, int position) {
        Node current = head;
        Node last = head.next;
        int count =position-1;
        if( position == 0){
            head = head.next;
        }
        while( count>0){
            count = count-1;
            current = current.next;
            last = current.next;
        }
        current.next = last.next;
    return head;
}

/**deleting a Node from doubly Linked list**/
	
	public void deleteNodeDoublyLinkedList(int data){
		Node current = head;
		if( current == null){
			System.out.println("empty list");
		}
		while( current != null){
			if( current.data == data){
				current.previous.next = current.next;
				System.out.println("match found");
				break;
			}
			else
				current = current.next;
		}
	}
	
/** deleting a Node from a single Linked list **/
	public void deleteNodeSinglyLinkedList(int data){
		Node current = head;
		boolean isPresent = false;
		Node temp = new  Node(0);
		if( current == null){
			System.out.println("empty list");
		}
		while( current != null){
			if( current.data == data){
				temp.next = current.next;
				isPresent = true;
				break;
			}
			else if (current.data != data ){
				temp = current;
				current = current.next;
			}
		}
		
		System.out.println("Match Found? " + isPresent);
	}
	
/** method to append node at the tail of a linked list **/
	
		public Node appendAtTail(Node head, int data){
			Node n = new Node(data);
			Node current = head;
			if( head == null){
				head= n;
			}
			else{
			while(current.next !=null){
				current = current.next;
			}
			current.next =n;
			current = current.next;
			}
			return head;
		}
 /** method to append node at a given position in a linked list **/
		
	    Node insertNodeAtPosition(Node head, int data, int position) {
           Node newNode = new Node(data);
           Node current = head;
           Node last = head.next;
           int count = position-1;
           while( count>0){
               current = current.next;
               last = current.next;
               count = count-1;
               continue;
           }
       newNode.next = last;
       current.next = newNode;

       return head;

   }
	    
/** printing reverse linked list using stack**/
	    public void reversePrint(Node head) {
	        Node current = head;
	        Stack<Integer> sc= new Stack<Integer>();
	        while(current.next != null){
	        sc.push(current.data);
	        current = current.next;
	        }
	        while(!sc.isEmpty()){
	            System.out.println(sc.pop());
	        }

	    }



	public static void main(String args[]){
		LinkedListFunctions an = new LinkedListFunctions();
		an.add(1);
		an.add(2);
		an.add(3);
		an.add(2);
		an.add(1);
		an.appendAtTail(an.head, 5);
		
		
		an.insertNodeAtPosition(an.head,5,3);
		an.printlist();
		an.reversePrint(an.head);
		an.deleteNodeSinglyLinkedList(13);
		an.deleteNodeDoublyLinkedList(3);
		an.deleteNodeDoublyLinkedList(2);
		System.out.println("after deleting");
		an.printlist();
		System.out.println("Is the linked list a palindrome?");
		System.out.println(an.isPalindrome(an.head));
	}
}