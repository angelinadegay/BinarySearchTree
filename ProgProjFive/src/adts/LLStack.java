package adts;

import interfaces.StackInterface;
import nodes.LLNode;

public class LLStack<E> implements StackInterface<E> {
	
	LLNode<E> top;

	@Override
	public void push(E element) {
		LLNode<E> newNode = new LLNode(element);
		newNode.setNext(top);
		top = newNode;
	}

	@Override
	public E pop() {
		E temp = top.getData();
		top = top.getNext();
		return temp;
	}

	@Override
	public E peek() {
		return top.getData();
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public boolean isFull() {
		return false;
	}
	
	public String toString() {
		StringBuilder stackStr = new StringBuilder("\n---------\n");
		LLNode<E> ptr = top;
		while (ptr != null) {
			stackStr.append(ptr.getData() + "\n");
			ptr = ptr.getNext();
		}
		return stackStr.toString();
	}

}
