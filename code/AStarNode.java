
public class AStarNode implements Comparable<AStarNode>{
	Node nextNode; 
	AStarNode preNode;
	Segment edge;
	double heuristic;
	double startCost;
	
	
	
	public AStarNode(Node nextNode, AStarNode preNode, double cost){
		
	}
	@Override
	public int compareTo(AStarNode comp) {
		return 0;
		
	}
	
}
