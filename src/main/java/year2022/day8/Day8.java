package year2022.day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day8 {

//	private static final String FILENAME = "src/main/resources/2022/day8InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day8Input.txt";

	public static void main(String[] args) throws IOException {
		TreeGrid treeGrid = readTreeGrid();
		
		treeGrid.getTreeRows().forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		treeGrid.getTreeRows().stream()
				.flatMap(tr -> tr.getTrees().stream())
				.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));

		long visibleCount = treeGrid.getTreeRows().stream()
				.flatMap(tr -> tr.getTrees().stream())
				.filter(Tree::isVisible)
				.count();

		System.out.println(visibleCount);
		
		System.out.println("done");
	}

	private static TreeGrid readTreeGrid() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		TreeGrid treeGrid = new TreeGrid();
		
		lines.forEach(l -> treeGrid.addTreeRow(createTreeRow(l)));
		
		processVisibleFromTopAndBottom(treeGrid);
		
		return treeGrid;
	}
	
	private static void processVisibleFromTopAndBottom(TreeGrid treeGrid) {
		//assume the treeGrid has a row and that all rows are same length 
		int rowSize = treeGrid.getTreeRows().iterator().next().getTrees().size();
		
		for(int i = 0; i < rowSize; i++) {
			List<Tree> topToBottomTrees = treeGrid.getColumnTrees(i);
			setVisible(topToBottomTrees, Tree::setVisibleFromTop);
			setVisible(reverseList(topToBottomTrees), Tree::setVisibleFromBottom);
		}
	}
	
	private static TreeRow createTreeRow(String line) {
		TreeRow treeRow = new TreeRow();
		
		for(char c : line.toCharArray()) {
			treeRow.addTree(createTree(c));
		}
		
		processVisibleFromLeftAndRight(treeRow);
		
		return treeRow;
	}
	
	private static void setVisible(List<Tree> trees, BiConsumer<Tree, Boolean> setter) {
		Long maxHeight = -1L;
		for(int i = 0; i < trees.size(); i++) {
			Tree currentTree = trees.get(i);
			Long currentHeight = currentTree.getHeight();
			if(currentHeight > maxHeight) {
				setter.accept(currentTree, true);
				maxHeight = currentHeight; 
			}
		}
	}
	

	private static void processVisibleFromLeftAndRight(TreeRow treeRow) {
		List<Tree> leftToRightTrees = treeRow.getTrees();
		setVisible(leftToRightTrees, Tree::setVisibleFromLeft);
		setVisible(reverseList(leftToRightTrees), Tree::setVisibleFromRight);
	}

	private static <T> List<T> reverseList(List<T> list) {
		List<T> reverse = new ArrayList<>();
		for(int i = list.size()-1; i >= 0; i--) {
			reverse.add(list.get(i));
		}
		return reverse;
	}

	private static Tree createTree(char c) {
		return new Tree(Long.valueOf(Character.toString(c)));
	}

}
