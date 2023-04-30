
package adts;

import java.util.ArrayList;
import java.util.Iterator;

import interfaces.*;
import iterators.BSTIterator;
import nodes.BSTNode;


public class BinarySearchTree<T extends Comparable<T>> 
             implements BSTInterface<T>, Iterable<T> {
	
	protected BSTNode<T> root;   
	
	boolean found;   // used by remove
	
	T[] rebalanceArray;  // for rebalancing the tree
	int rebalanceIndex;  //           "
	
	// for current traversal logic
	protected ExtendedLLQ<T> inOrderQ = new ExtendedLLQ<>();
	protected ExtendedLLQ<T> preOrderQ = new ExtendedLLQ<>();
	protected ExtendedLLQ<T> postOrderQ = new ExtendedLLQ<>();
	protected ExtendedLLQ<T> revOrderQ = new ExtendedLLQ<>();
	
	private TraversalType defaultTraversalType = TraversalType.INORDER;
	
	
	public BinarySearchTree() {
		root = null;
	}
	class ExtendedLLQ<T> extends LLQ<T> {
	    public boolean isEmpty() {
	        return super.front == null;
	    }
	}
	
	
	public void add (T element) {
		root = recAdd(element, root);
    }
	
	private BSTNode<T> recAdd(T element, BSTNode<T> tree) {
		if (tree == null) {
			tree = new BSTNode<T>(element);
		}
		else {
			if (element.compareTo(tree.getData()) <= 0) {
				tree.setLeft(recAdd(element, tree.getLeft()));  // add to left subtree
				
			}
			else {
				tree.setRight(recAdd(element, tree.getRight()));  // add to right subtree
		    }
		}
		return tree;
	}
	
	
	public boolean remove (T element) {
		root = recRemove(element, root);
		return found;
	}
	  
	private BSTNode<T> recRemove(T element, BSTNode<T> tree) {
		if (tree == null) {
			found = false;
		}
		else {
			if (element.compareTo(tree.getData()) < 0) {
				tree.setLeft(recRemove(element, tree.getLeft()));
			}
			else {
				if (element.compareTo(tree.getData()) > 0) {
					tree.setRight(recRemove(element, tree.getRight()));
				}
				else {
					tree = removeNode(tree);
					found = true;
				}
			}
		}
		return tree;
	}

	private BSTNode<T> removeNode(BSTNode<T> tree) {
		
		T payload;
			  
		if (tree.getLeft() == null) {
			return tree.getRight();
		}
		else {
			if (tree.getRight() == null) {
				return tree.getLeft();
			}
			else {
				payload = getPredecessor(tree.getLeft());
				tree.setData(payload);
				tree.setLeft(recRemove(payload, tree.getLeft()));
				return tree;
			}
		}
	}

	private T getPredecessor(BSTNode<T> tree) {
		while (tree.getRight() != null) {
			tree = tree.getRight();
		}
		return tree.getData();
	}

	  
	public int size() {
		return recSize(root);
	}
	
	private int recSize(BSTNode<T> tree) {
		if (tree == null) {
			return 0;
		}
		else {
			return recSize(tree.getLeft()) + recSize(tree.getRight()) + 1;
		}
	}
	
	// this implementation of a size operation demonstrates that
	// it is possible to visit all the nodes of the tree without recursion
	public int size2() {
		int count = 0;
		if (root != null) {
			LLStack<BSTNode<T>> hold = new LLStack<BSTNode<T>>();
			BSTNode<T> currNode;
			hold.push(root);
			while (!hold.isEmpty()) {
				currNode = hold.peek();
				hold.pop();
				count++;
				if (currNode.getLeft() != null) {
					hold.push(currNode.getLeft());
				}
				if (currNode.getRight() != null) {
					hold.push(currNode.getRight());
				}
			}
		}
		return count;
	}

	  
	public boolean isEmpty() {
		return (root == null);
	}
	
	
	public boolean contains (T element) {
		return recContains(element, root);
	}
	
	private boolean recContains(T element, BSTNode<T> tree) {
		if (tree == null) {
			return false;
		}
		else {
	    	if (element.compareTo(tree.getData()) < 0) {
	    		return recContains(element, tree.getLeft());  // search left subtree
	    	}
	        else {
	        	if (element.compareTo(tree.getData()) > 0) {
	        		return recContains(element, tree.getRight());  // search right subtree
	        	}
	            else {
	                return true;  // element.compareTo(tree, the subtree's root) == 0
	            }
	        }
		}
	}

	
	public T get(T element) {
		return recGet(element, root);
	}
	
	private T recGet(T element, BSTNode<T> tree) {
		if (tree == null) {
			return null;
		}
		else {
			if (element.compareTo(tree.getData()) < 0) {
				return recGet(element, tree.getLeft());  // get from left subtree
			}
			else {
				if (element.compareTo(tree.getData()) > 0) {
					return recGet(element, tree.getRight());  // get from right subtree
				}
				else {
					return tree.getData();  // element is found!
				}
			}
		}
	}
	
	
	// methods that follow support operations that
	// do not appear in BSTInterface.java
	
	
	public int treeHeight() {
		return recNodeHeight(root);
	}
	
	private int recNodeHeight(BSTNode<T> tree) {
		if (tree == null) {
			return -1;
		}
		else {
			if (tree.getLeft() == null && tree.getRight() == null) {
				return 0;
			}
			else {
				return 1 + Math.max(recNodeHeight(tree.getLeft()), recNodeHeight(tree.getRight()));
			}
		}
	}
	public int treeHeight2() {
		   int height = -1;
		   if (root == null) {
		        return height;
		    }
		    LLQ<BSTNode<T>> queue = new LLQ<BSTNode<T>>();
		    queue.enqueue(root);
		    int currentNode = 1;
		    int nextNode = 0;
		    while (!queue.isEmpty()) {
		    	 BSTNode<T> current = queue.dequeue();
		    		currentNode--;
		    	
		            if (current.getLeft() != null) {
		                queue.enqueue(current.getLeft());
		                nextNode++;
		            }
		            if (current.getRight() != null) {
		                queue.enqueue(current.getRight());
		                nextNode++;
		            }
		            if(currentNode==0) {
		            	currentNode=nextNode;
		            	nextNode=0;
		            	
		            	height++;
		            }
		        }
		       
		    
		    return height;
	}
	

	

	public T min() {
	    return mini(root);
	}

	private T mini(BSTNode<T> node) {
	    if (node == null) {
	        return null;
	    } else if (node.getLeft() == null) {
	        return node.getData();
	    } else {
	        return mini(node.getLeft());
	    }
	}
	
	public T max() {
	    if (root == null) {
	        return null;
	    }
	    return maxi(root).getData();
	}

	private BSTNode<T> maxi(BSTNode<T> node) {
	    if (node.getRight() == null) {
	        return node;
	    }
	    return maxi(node.getRight());
	}

	public T median() 
	{
	ArrayList<T> nodes = new ArrayList<>();
	int median;
	if(recSize(root) % 2 ==0) {
		median = recSize(root)/2-1;
	}
	else {
		median = recSize(root)/2;
	}
	inOrder(root);
	while(!inOrderQ.isEmpty())
	{
		nodes.add(inOrderQ.dequeue());
		
	}
	return nodes.get(median);
}
	
	public void rebalance() {
		rebalanceArray = (T[]) new Comparable[size()];
		rebalanceIndex = -1;
		fillRebalanceArray(root);
		root = null;
		recRebalance(0, rebalanceArray.length - 1);
	}
	
	private void fillRebalanceArray(BSTNode<T> tree) {
		if (tree != null) {
			fillRebalanceArray(tree.getLeft());
			rebalanceArray[++rebalanceIndex] = tree.getData();
			fillRebalanceArray(tree.getRight());
		}
	}
	
	private void recRebalance(int first, int last) {
		if (first <= last) {
			int mid = first + (last - first) / 2;
			root = recAdd(rebalanceArray[mid], root);
			recRebalance(first, mid-1);
			recRebalance(mid+1, last);
		}
	}
	
	
	

	private void inOrder(BSTNode<T> tree) 
	{
		if (tree != null) {
			inOrder(tree.getLeft());
			inOrderQ.enqueue(tree.getData());
			inOrder(tree.getRight());
		}
	}
	
	// populate preOrderQ with tree elements based on pre-order traversal
	private void preOrder(BSTNode<T> tree) 
	{
		if (tree != null) {
			preOrderQ.enqueue(tree.getData());
			preOrder(tree.getLeft());
			preOrder(tree.getRight());
		}
	}
	
	// populate postOrderQ with tree elements based on post-order traversal
	private void postOrder(BSTNode<T> tree)
	{
		if (tree != null) {
			postOrder(tree.getLeft());
			postOrder(tree.getRight());
			postOrderQ.enqueue(tree.getData());
		}
	}
	private void revOrder(BSTNode<T> tree)
	{
	    if (tree != null) {
	        revOrder(tree.getRight());
	        inOrderQ.enqueue(tree.getData());
	        revOrder(tree.getLeft());
	    }
	}
	
	public void setTraversalType(TraversalType orderType) 
	{
	    this.defaultTraversalType = orderType;
	    switch (orderType) 
	    {
	        case INORDER:
	            inOrderQ = new ExtendedLLQ<T>();
	            inOrder(root);
	            break;
	        case PREORDER:
	            preOrderQ = new ExtendedLLQ<T>();
	            preOrder(root);
	            break;
	        case POSTORDER:
	            postOrderQ = new ExtendedLLQ<T>();
	            postOrder(root);
	            break;
	        case REVORDER:
	            inOrderQ = new ExtendedLLQ<T>();
	            revOrder(root);
	            break;
	    }
	}

	public T peekNext(TraversalType orderType) {
	    switch (orderType) {
	        case INORDER:
	            return inOrderQ.isEmpty() ? null : inOrderQ.front.getData();
	        case PREORDER:
	            return preOrderQ.isEmpty() ? null : preOrderQ.front.getData();
	        case POSTORDER:
	            return postOrderQ.isEmpty() ? null : postOrderQ.front.getData();
	        case REVORDER:
	            return revOrderQ.isEmpty() ? null : revOrderQ.front.getData();
	        default:
	            return null;
	    }
	}

	
	
	

	public T getNext(TraversalType orderType) {
	    switch (orderType) {
	        case INORDER:
	            return inOrderQ.isEmpty() ? null : inOrderQ.dequeue();
	        case PREORDER:
	            return preOrderQ.isEmpty() ? null : preOrderQ.dequeue();
	        case POSTORDER:
	            return postOrderQ.isEmpty() ? null : postOrderQ.dequeue();
	        case REVORDER:
	            return revOrderQ.isEmpty() ? null : revOrderQ.dequeue();
	        default:
	            return null;
	    }
	}
	public void setDefaultTraversalType(TraversalType traversalType) {
	    this.defaultTraversalType = traversalType;
	}
	
	public Iterator<T> iterator() {
	    return new BSTIterator<>(this, defaultTraversalType);
	}

  
}
