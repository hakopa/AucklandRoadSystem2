import java.util.HashSet;
import java.util.PriorityQueue;

public class AStarSearch {

	public void findRoute(Node start, Node goal){
		//the fringe
		PriorityQueue<AStarNode> fringe = new PriorityQueue<>();
		//set of nodes that have been visited
		//was going to add a variable in each node but then i would have to reset the variable in every node each time
		//all nodes are unvisited
		HashSet<AStarNode> visited = new HashSet<>();
		
		//fringe starts with single element
		fringe.offer(new AStarNode(start, null, 0));
		
		
	}

}
