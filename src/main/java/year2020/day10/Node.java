package year2020.day10;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private long number;
	private List<Node> childNodes;
	private Node parentNode;
	
	public void addChildNode(Node childNode) {
		getChildNodes().add(childNode);
		childNode.setParentNode(this);
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<Node> getChildNodes() {
		if(childNodes == null) {
			childNodes = new ArrayList<>();
		}
		return childNodes;
	}

	public void setChildNodes(List<Node> childNodes) {
		this.childNodes = childNodes;
	}

	public Node getParentNode() {
		return parentNode;
	}

	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}

}
