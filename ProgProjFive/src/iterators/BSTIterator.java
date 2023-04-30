package iterators;

import java.util.Iterator;
import interfaces.BSTInterface.TraversalType;
import adts.BinarySearchTree;

public class BSTIterator<E extends Comparable<E>> implements Iterator<E> 
{
    protected BinarySearchTree<E> bst;
    protected TraversalType traversalType;

    public BSTIterator(BinarySearchTree<E> bst, TraversalType traversalType) 
    {
        this.bst = bst;
        this.traversalType = traversalType;
        bst.setTraversalType(traversalType);
    }

    @Override
    public boolean hasNext()
    {
        return !bst.isEmpty();
    }

    @Override
    public E next()
    {
        return bst.getNext(traversalType);
    }
}
