package nodes;

public class BSTNode<E extends Comparable<E>> {

  protected E data;             // BST node payload
  protected BSTNode<E> left;    // reference to the left child node
  protected BSTNode<E> right;   // reference to the right child node

  public BSTNode(E data)  {
    this.data = data;
    left = null;
    right = null;
  }
 
  public void setData(E data) {
    this.data = data;
  }

  public E getData() {
    return data;
  }
 
  public void setLeft(BSTNode<E> link) {
    left = link;
  }

  public void setRight(BSTNode<E> link) {
    right = link;
  }

  public BSTNode<E> getLeft() {
    return left;
  }

  public BSTNode<E> getRight() {
    return right;
  }
  
}
 
 