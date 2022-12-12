package year2022.day12;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private Long height;
	private Character character;
	private NodeRow nodeRow;
	private Set<Node> neighbourNodes;
	
	public void addNeighbourNode(Node node) {
		getNeighbourNodes().add(node);
	}

	public Node(Long height, Character character) {
		this.height = height;
		this.character = character;
	}

	public Long getHeight() {
		return height;
	}
	
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public NodeRow getNodeRow() {
		return nodeRow;
	}

	public void setNodeRow(NodeRow nodeRow) {
		this.nodeRow = nodeRow;
	}

	public Set<Node> getNeighbourNodes() {
		if(neighbourNodes == null) {
			neighbourNodes = new HashSet<>();
		}
		return neighbourNodes;
	}

	public void setNeighbourNodes(Set<Node> neighbourNodes) {
		this.neighbourNodes = neighbourNodes;
	}

}
