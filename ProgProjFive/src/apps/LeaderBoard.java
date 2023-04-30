package apps;

import adts.BinarySearchTree;
import interfaces.BSTInterface.TraversalType;

public class LeaderBoard {
  
	public static void main(String[] args) {
		
		BinarySearchTree<Golfer> golfers = new BinarySearchTree<Golfer>();
		
		golfers.add(new Golfer("Hideki", 74));
		golfers.add(new Golfer("Cheyenne", 73));
		golfers.add(new Golfer("Matt", 77));
		golfers.add(new Golfer("Bel�n", 76));
		golfers.add(new Golfer("Brooks", 75));
		golfers.add(new Golfer("Natalie", 68));
		golfers.add(new Golfer("Xander", 71));
		golfers.add(new Golfer("Lexi", 79));
		golfers.add(new Golfer("Rory", 70));
		
		int avg=0;
		int median=-1;
		int medianIndex = golfers.size()/2;
		golfers.setTraversalType(TraversalType.INORDER);
		for(int i=0; i < golfers.size();i++) {
			Golfer g = golfers.getNext(TraversalType.INORDER);
			System.out.println(g.getScore() + " " + g.getName());
			avg = avg + g.getScore();
			if(i==medianIndex) {
				median= g.getScore();
			}
		}
		
	
		System.out.println(avg/golfers.size());
		System.out.println(median);
		

	}
}