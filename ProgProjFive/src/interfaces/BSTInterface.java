/* ----------------------------------------------------------------------------
* BSTInterface.java
*
* Tree objects of classes that implement this interface are unbounded and allow
* duplicate elements, but do not allow null elements.
*
* In-order, pre-order and post-order traversals are supported.
* 
* ---------------------------------------------------------------------------*/ 

package interfaces;

public interface BSTInterface<T extends Comparable<T>> {
	
	public static enum TraversalType {
		INORDER, PREORDER, POSTORDER, REVORDER
	}

  void add(T element);
	  
  boolean remove(T element);
  // remove the first item found in the tree such that item.compareTo(element) == 0
  // if no such item exists, return false
 
  boolean isEmpty();
  
  int size();
  
  boolean contains(T element);
  // return true if there is an item in the tree such that item.compareTo(element) == 0
  // otherwise return false
    
  T get(T element);
  // return the first item in the tree such that item.compareTo(element) == 0
  // if no such item exists, return null
  
  // methods that supported traversal that will no longer be needed 
  /* /
  int reset(TraversalType orderType); 

  T getNext (TraversalType orderType);
  /*
   * preconditions
   *  - the BST is not empty
   *  - the BST has been reset for orderType
   *  - the BST has not been modified since the most recent reset
   *  - application code is responsible for not iterating beyond the end of the tree
   *
   * Returns the element at the current position on the BST for the specified traversal type
   * and advances the value of the current position. 
   *
   /**/

}