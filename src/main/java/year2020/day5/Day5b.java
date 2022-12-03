package year2020.day5;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day5b {
	
//	private static final String FILENAME = "src/main/resources/2020/day5InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day5Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<BoardingPass> boardingPasses = readBoardingPasses();
		
		List<BoardingPass> sortedBoardingPasses = boardingPasses.stream()
				.sorted(Comparator.comparing(BoardingPass::getSeatId))
				.collect(Collectors.toList());
		
		BoardingPass currentBoardingPass = boardingPasses.get(0);
		boolean found = false;
		for(int i = 1; i < sortedBoardingPasses.size() && ! found; i++) {
			BoardingPass nextBoardingPass = sortedBoardingPasses.get(i);
			if(nextBoardingPass.getSeatId() - currentBoardingPass.getSeatId() > 1) {
				found = true;
			} else {
				currentBoardingPass = nextBoardingPass;
			}
		}
		
		System.out.println(currentBoardingPass.getSeatId() + 1);
	}
	
	private static List<BoardingPass> readBoardingPasses() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		return lines.stream()
				.map(Day5b::createBoardingPass)
				.collect(Collectors.toList());
	}

	private static BoardingPass createBoardingPass(String line) {
		BoardingPass boardingPass = new BoardingPass();
		boardingPass.setBinarySpacePartitioning(line);
		
		int row = calculateRow(line.substring(0, 7));
		int column = calculateColumn(line.substring(7));
		int seatId = row * 8 + column;
		
		boardingPass.setRow(row);
		boardingPass.setColumn(column);
		boardingPass.setSeatId(seatId);
		
		return boardingPass;
	}

	private static int calculateRow(String substring) {
		Partition partition = new Partition(0, 127);
		for(char character : substring.toCharArray()) {
			partition = createPartition(character == 'B', partition);
		}
//		System.out.println(partition.getStart());
		return partition.getStart();
	}

	private static Partition createPartition(boolean upper, Partition partition) {
		int start = partition.getStart();
		int end = partition.getEnd();
		
		int middle = (start + end) / 2;
		if (upper) {
			return new Partition(middle + 1, end);
		} else {
			return new Partition(start, middle - 1);
		}
	}

	private static int calculateColumn(String substring) {
		Partition partition = new Partition(0, 7);
		for(char character : substring.toCharArray()) {
			partition = createPartition(character == 'R', partition);
		}
//		System.out.println(partition.getStart());
		return partition.getStart();
	}


}
