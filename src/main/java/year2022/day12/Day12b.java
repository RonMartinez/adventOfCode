package year2022.day12;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

	public static void main(String[] args) throws IOException {
		NodeGrid nodeGrid = readNodeGrid();
		
		addNeighbourNodes(nodeGrid);
		
		nodeGrid.getNodeRows().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		nodeGrid.getNodes().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Node endNode = nodeGrid.getNodes().stream()
				.filter(t -> END_CHARACTER.equals(t.getCharacter()))
				.findFirst().orElse(null);
		
		Set<Node> startNodes = nodeGrid.getNodes().stream()
				.filter(t -> t.getHeight() == 1L)
				.collect(Collectors.toSet());
		
		Path shortestPath = null;
		
		Set<Node> examinedStartNodes = new HashSet<>();
		for(Node startNode : startNodes) {
			System.out.println(examinedStartNodes.size());
			if( ! examinedStartNodes.contains(startNode)) {
				examinedStartNodes.add(startNode);
				Path path = findShortestPath(startNode, endNode, examinedStartNodes);
				if(path != null) {
					path.getNodes().forEach(n -> System.out.print(n.getCharacter()));
					System.out.println();
					
					examinedStartNodes.addAll(path.getNodes().stream()
							.filter(n -> n.getHeight() == 1L)
							.collect(Collectors.toSet()));
					
					for(int i = path.getNodes().size()-1; i >= 0; i--) {
						Node node = path.getNodes().get(i);
						if(startNodes.contains(node)) {
							Path potentialShortestPath = new Path();
							potentialShortestPath.setNodes(new ArrayList<>(path.getNodes().subList(i, path.getNodes().size())));
							potentialShortestPath.getNodes().forEach(n -> System.out.print(n.getCharacter()));
							System.out.println();

							if(shortestPath == null
									|| potentialShortestPath.getSize() < shortestPath.getSize()) {
								shortestPath = potentialShortestPath;
							}
							
							break;
						}
					}
				}
			} else {
				System.out.println("skipping");
			}
		}

		System.out.println(shortestPath.getSize()-1);

		System.out.println("done");
	}
	
	private static Path findShortestPath(Node startNode, Node endNode, Set<Node> examinedStartNodes) {
		List<Path> paths = new ArrayList<>();
		Path path = new Path();
		path.addNode(startNode);
		
		HashMap<Node, Path> visitedNodePathMap = new HashMap<>();
		
		traverseToEndNode(startNode, endNode, path, paths, visitedNodePathMap, examinedStartNodes);
		
		return paths.stream()
				.sorted(Path.SIZE_COMPARATOR)
				.findFirst().orElse(null);
	}

	private static void traverseToEndNode(Node startNode, Node endNode, Path path, List<Path> paths, HashMap<Node, Path> visitedNodePathMap, Set<Node> examinedStartNodes) {
//		if(startNode.getHeight() == 1L) {
//			examinedStartNodes.add(startNode);
//		}
		
		if(startNode.equals(endNode)) {
			paths.add(path);
		} else {
			Set<Node> traversibleNeighbourNodes = startNode.getNeighbourNodes().stream()
					.filter(nn -> canTraverse(startNode, nn))
					.filter(nn -> ! path.getNodes().contains(nn))
					.collect(Collectors.toSet());
			for(Node neighbourNode : traversibleNeighbourNodes) {
				Path newPath = new Path();
				path.getNodes().forEach(newPath::addNode);
				newPath.addNode(neighbourNode);
				
				Path existingPath = visitedNodePathMap.get(neighbourNode);
				
				if(existingPath == null
						|| newPath.getSize() < existingPath.getSize()) {
					visitedNodePathMap.put(neighbourNode, newPath);
					traverseToEndNode(neighbourNode, endNode, newPath, paths, visitedNodePathMap, examinedStartNodes);	
				}
				
				
			}
		}
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
