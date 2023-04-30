package interfaces;

public interface StackInterface<E> {

	void push(E element);  // add an element to the stack - always at the "top"
	
	E pop();   // remove and return the top of the stack
	
	E peek();  // return the top of the stack ... without removing
	
	boolean isEmpty();
	
	boolean isFull();
	
}
