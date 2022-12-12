package year2022.day12;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day12b {

//	private static final String FILENAME = "src/main/resources/2022/day12InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day12Input.txt";
	
	public static final Long OFFSET = -9L;
	public static final Character START_CHARACTER = Character.valueOf('S');
	public static final Character END_CHARACTER = Character.valueOf('E');
	public static final Character LOWEST_ELEVATION_CHARACTER = 'a';
	public static final Character HIGHEST_ELEVATION_CHARACTER = 'z';
	public static final Long TRAVERSIBLE_HEIGHT_DIFFERENCE = 1L;
	public static final Long STARTING_HEIGHT = 1L;

	public static void main(String[] args) throws IOException {
		NodeGrid nodeGrid = readNodeGrid();
		
		addNeighbourNodes(nodeGrid);
		
		nodeGrid.getNodeRows().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		nodeGrid.getNodes().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Node endNode = nodeGrid.getNodes().stream()
				.filter(t -> END_CHARACTER.equals(t.getCharacter()))
				.findFirst().orElse(null);
		
		Set<Node> startNodes = nodeGrid.getNodes().stream()
				.filter(t -> STARTING_HEIGHT.equals(t.getHeight()))
				.collect(Collectors.toSet());
		
		List<Path> shortestPaths = new ArrayList<>();
		for(Node startNode : startNodes) {
			Path shortestPath = findShortestPath(startNode, endNode);
			if(shortestPath != null) {
				shortestPaths.add(shortestPath);
			}
		}
		
		Path shortestPath = shortestPaths.stream()
				.sorted(Path.SIZE_COMPARATOR)
				.findFirst().orElse(null);

		System.out.println(shortestPath.getSize()-1);

		System.out.println("done");
	}
	
	private static Path findShortestPath(Node startNode, Node endNode) {
		Path shortestPath = null;
		
		Set<Node> visited = new HashSet<>();

		LinkedList<Path> queue = new LinkedList<>();

		Path startPath = new Path();
		startPath.addNode(startNode);

		visited.add(startNode);
		queue.add(startPath);
		while ( ! queue.isEmpty()) {
			Path path = queue.poll();
			
			List<Node> nodes = path.getNodes();
			
			Node node = nodes.get(nodes.size()-1); 
			if(node.equals(endNode)) {
				shortestPath = path;
			} else {
				for(Node unvisitedTraversibleNode : getUnvisitedTraversibleNodes(node, visited)) {
					Path copyPath = copyPath(path);
					copyPath.addNode(unvisitedTraversibleNode);
					
					visited.add(unvisitedTraversibleNode);
					queue.add(copyPath);
				}
			}
		}
		
		return shortestPath;
	}
	
	private static Set<Node> getUnvisitedTraversibleNodes(Node node, Set<Node> visited) {
		return node.getNeighbourNodes().stream()
				.filter(nn -> canTraverse(node, nn))
				.filter(nn -> ! visited.contains(nn))
				.collect(Collectors.toSet());
	}

	private static Path copyPath(Path path) {
		Path copy = new Path();
		path.getNodes().forEach(copy::addNode);
		return copy;
	}

	private static boolean canTraverse(Node startNode, Node neighbourNode) {
		return (neighbourNode.getHeight() - startNode.getHeight()) <= TRAVERSIBLE_HEIGHT_DIFFERENCE;
	}

	private static void addNeighbourNodes(NodeGrid nodeGrid) {
		addLeftRightNeighbourNodes(nodeGrid);
		addTopDownNeighbourNodes(nodeGrid);
	}

	private static void addTopDownNeighbourNodes(NodeGrid nodeGrid) {
		for(List<Node> nodeColumn : nodeGrid.getNodeColumns()) {
			addNeighbourNodes(nodeColumn);
		}
	}

	private static void addLeftRightNeighbourNodes(NodeGrid nodeGrid) {
		for(NodeRow nodeRow : nodeGrid.getNodeRows()) {
			addNeighbourNodes(nodeRow.getNodes());
		}
	}

	private static void addNeighbourNodes(List<Node> nodes) {
		for(int i = 0 ; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if(i > 0) {
				Node previousNode = nodes.get(i-1);
				node.addNeighbourNode(previousNode);
			}
			if(i < nodes.size()-1) {
				Node nextNode = nodes.get(i+1);
				node.addNeighbourNode(nextNode);
			}
		}
	}

	private static NodeGrid readNodeGrid() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		NodeGrid nodeGrid = new NodeGrid();
		
		lines.forEach(l -> nodeGrid.addNodeRow(createNodeRow(l)));
		
		return nodeGrid;
	}
	
	private static NodeRow createNodeRow(String line) {
		NodeRow nodeRow = new NodeRow();
		
		for(char c : line.toCharArray()) {
			nodeRow.addNode(createNode(c));
		}
		
		return nodeRow;
	}
	
	private static Node createNode(char c) {
		Character heightCharacter = c;
		if(START_CHARACTER.equals(c)) {
			heightCharacter = LOWEST_ELEVATION_CHARACTER;
		} else if(END_CHARACTER.equals(c)) {
			heightCharacter = HIGHEST_ELEVATION_CHARACTER;
		}
		
		long height = Character.getNumericValue(heightCharacter) + OFFSET;
		
		return new Node(height, c);
	}

}
