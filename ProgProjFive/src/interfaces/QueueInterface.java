package interfaces;

public interface QueueInterface<E> {

	void enqueue(E element);  // add an element to the queue - always at the end of the queue
	
	E dequeue();   // remove and return the front of the queue
	
	boolean isEmpty();
	
	boolean isFull();
	
}
