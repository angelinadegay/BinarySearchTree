package apps;

import adts.*;
import interfaces.BSTInterface.TraversalType;

public class AlphaBravoCharlie {

	public static void main(String[] args) {
		
		BinarySearchTree<String> abcTree =
				new BinarySearchTree<String>();
		
		abcTree.add("November");
		abcTree.add("Bravo");
		abcTree.add("Sierra");
		abcTree.add("Alpha");
		abcTree.add("Echo");
		abcTree.add("Romeo");
		abcTree.add("Tango");
		abcTree.add("India");
		abcTree.add("Yankee");
		
		System.out.println("PREORDER: ");
		abcTree.setTraversalType(TraversalType.PREORDER);
		for(int i=0; i < abcTree.size();i++) {
			System.out.println(abcTree.getNext(TraversalType.PREORDER));
		}
		
		// etc.
		System.out.println("");
		System.out.println("INORDER: ");
		abcTree.setTraversalType(TraversalType.INORDER);
		for(int i=0; i < abcTree.size();i++) {
			System.out.println(abcTree.getNext(TraversalType.INORDER));
		}
		
		System.out.println("");
		System.out.println("POSTORDER: ");
		abcTree.setTraversalType(TraversalType.POSTORDER);
		for(int i=0; i < abcTree.size();i++) {
			System.out.println(abcTree.getNext(TraversalType.POSTORDER));
		}
		System.out.println(abcTree.treeHeight());
		
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		 bst.add(4);
		    bst.add(2);
		    bst.add(1);
		    bst.add(3);
		    bst.add(6);
		    bst.add(5);
		    bst.add(7);

		bst.setTraversalType(TraversalType.INORDER);
		for (Integer value : bst) {
		    System.out.println(value);
		}

	}

}
