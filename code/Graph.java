import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This represents the data structure storing all the roads, nodes, and
 * segments, as well as some information on which nodes and segments should be
 * highlighted.
 * 
 * @author tony
 */
public class Graph {
	// map node IDs to Nodes.
	Map<Integer, Node> nodes = new HashMap<>();
	// map road IDs to Roads.
	Map<Integer, Road> roads;
	// just some collection of Segments.
	Collection<Segment> segments;
	Node highlightNode;
	Node[] highlightedNodes = new Node[2];
	boolean fromNode = true;
	boolean findRoute = false;
	Collection<Road> highlightedRoads = new HashSet<>();

	public Graph(File nodes, File roads, File segments, File polygons) {
		this.nodes = Parser.parseNodes(nodes, this);
		this.roads = Parser.parseRoads(roads, this);
		this.segments = Parser.parseSegments(segments, this);
	}

	public void draw(Graphics g, Dimension screen, Location origin, double scale) {
		// a compatibility wart on swing is that it has to give out Graphics
		// objects, but Graphics2D objects are nicer to work with. Luckily
		// they're a subclass, and swing always gives them out anyway, so we can
		// just do this.
		System.out.println(findRoute);
		Graphics2D g2 = (Graphics2D) g;

		// draw all the segments.
		g2.setColor(Mapper.SEGMENT_COLOUR);
		for (Segment s : segments)
			s.draw(g2, origin, scale);

		// draw the segments of all highlighted roads.
		g2.setColor(Mapper.HIGHLIGHT_COLOUR);
		g2.setStroke(new BasicStroke(3));
		for (Road road : highlightedRoads) {
			for (Segment seg : road.components) {
				seg.draw(g2, origin, scale);
			}
		}

		// draw all the nodes.
		g2.setColor(Mapper.NODE_COLOUR);
		for (Node n : nodes.values())
			n.draw(g2, screen, origin, scale);

		// draw the highlighted node, if it exists.
		if(!findRoute){
			if(highlightNode != null){
				g2.setColor(Mapper.HIGHLIGHTNODE_COLOUR);
				highlightNode.draw(g2,  screen, origin, scale);
			}
		}
		if (highlightedNodes != null) {
			for(int i = 0; i<highlightedNodes.length; i++){
				if(i==0){
					if(highlightedNodes[i]==null)
						break;
					g2.setColor(Mapper.HIGHLIGHTFROMNODE_COLOUR);
					highlightedNodes[i].draw(g2, screen, origin, scale);
				}
				if(i==1){
					if(highlightedNodes[i]==null)
						break;
					g2.setColor(Mapper.HIGHLIGHTTONODE_COLOUR);
					highlightedNodes[i].draw(g2, screen, origin, scale);
					findRoute = false;
				}
			}
		}
	}

	public boolean setRouteHighlight(Node node) {
		findRoute = true;
		
		if(fromNode){
			this.highlightedNodes[0] = node;	
			fromNode = false;
			return false;
		}
		else{
			this.highlightedNodes[1] = node;
			fromNode = true;
			astar();
			return true;
		}
	}
	
	public void setHighlight(Node node){
		this.highlightNode = node;
	}

	public void setHighlight(Collection<Road> roads) {
		this.highlightedRoads = roads;
	}
	
	public void clearHighlight(){
		this.highlightedNodes[0] = null;
		this.highlightedNodes[1] = null;
	}
	
	public void astar(){
		Node toNode = highlightedNodes[0];
		Node fromNode = highlightedNodes[1];
		
		
	}
}

// code for COMP261 assignments