package year2022.day12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Path {
	
	public static final Comparator<Path> SIZE_COMPARATOR = Comparator.comparing(Path::getSize);

	private List<Node> nodes;
	
	public int getSize() {
		return getNodes().size();
	}
	
	public void addNode(Node node) {
		getNodes().add(node);
	}

	
	public List<Node> getNodes() {
		if(nodes == null) {
			nodes = new ArrayList<>();
		}
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

}
