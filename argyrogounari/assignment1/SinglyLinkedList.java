
public class SinglyLinkedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size = 0;
	
	public SinglyLinkedList() {};
	
	public void findK(int k) {
		
		if (k>this.size-1){
			System.out.println("You can onlu choose a k between 0 and "+ (this.size-1) + ".");
		} else {
			Node<T> answer = this.head;
			for(int i = this.size-1; k<i;k++){
				answer = answer.getNext();
				System.out.println("Answer: "+ answer.getData());
			}
			System.out.println(answer.getData());
		}
		
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addHead(T data) {
		this.head = new Node<>(data, this.head);
		if(this.isEmpty()){
			this.tail = this.head;
		} 
		this.size++;
	}
	
	public void addTail(T data) {
		Node<T> newNode = new Node<>(data, null);
		if(this.isEmpty()){
			this.head = newNode;
		} else {
			this.tail.setNext(newNode);
		}
		this.tail = newNode;
		this.size++;
	}
	
}
