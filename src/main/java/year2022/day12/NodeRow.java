package year2022.day12;

import java.util.ArrayList;
import java.util.List;

public class NodeRow {
	
	private List<Node> nodes;
	private NodeGrid nodeGrid;
	
	public void addNode(Node node) {
		node.setNodeRow(this);
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

	public NodeGrid getNodeGrid() {
		return nodeGrid;
	}

	public void setNodeGrid(NodeGrid nodeGrid) {
		this.nodeGrid = nodeGrid;
	}

}
