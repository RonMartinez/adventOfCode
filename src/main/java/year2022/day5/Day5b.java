package year2022.day5;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day5b {

//	private static final String FILENAME = "src/main/resources/2022/day5InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day5Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<RearrangementProcedureStep> rearrangementProcedureSteps = readRearrangementProcedureSteps();
		
		Collections.reverse(rearrangementProcedureSteps);
		
		rearrangementProcedureSteps.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));

		List<CrateStack> crateStacks = readCrateStacks();
		
		crateStacks.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		rearrangementProcedureSteps.forEach(rps -> processRearrangementProcedureStep(rps, crateStacks));
		
		crateStacks.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		StringBuilder stringBuilder = new StringBuilder();
		for(CrateStack crateStack : crateStacks) {
			List<String> crates = crateStack.getCrates();
			stringBuilder.append(crates.get(crates.size() - 1));
		}
		
		System.out.println(stringBuilder.toString());
		
		System.out.println("done");
	}

	private static void processRearrangementProcedureStep(RearrangementProcedureStep rearrangementProcedureStep, List<CrateStack> crateStacks) {
		CrateStack sourceCrateStack = crateStacks.get(rearrangementProcedureStep.getSourceStack()-1);
		CrateStack destinationCrateStack = crateStacks.get(rearrangementProcedureStep.getDestinationStack()-1);
		
		List<String> sourceCrates = sourceCrateStack.getCrates();
		List<String> destinationCrates = destinationCrateStack.getCrates();
		
		List<String> cratesToMove = new ArrayList<>();
		for(int i = 0; i < rearrangementProcedureStep.getNumberOfCratesToMove(); i++) {
			String crate = sourceCrates.remove(sourceCrates.size() - 1);
			cratesToMove.add(crate);
		}
		Collections.reverse(cratesToMove);
		destinationCrates.addAll(cratesToMove);
	}

	private static List<RearrangementProcedureStep> readRearrangementProcedureSteps() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<RearrangementProcedureStep> rearrangementProcedureSteps = new ArrayList<>();
		
		ReverseListIterator<String> reverseListIterator = new ReverseListIterator<>(lines);

		while(reverseListIterator.hasNext()) {
			String line = reverseListIterator.next();
			if(StringUtils.isBlank(line)) {
				break;
			}
			rearrangementProcedureSteps.add(createRearrangementProcedureStep(line));
		}
		
		return rearrangementProcedureSteps;
	}

	private static RearrangementProcedureStep createRearrangementProcedureStep(String line) {
		line = line.replace("move ", "");
		line = line.replace("from ", "");
		line = line.replace("to ", "");
		String[] split = line.split(" ");

		return new RearrangementProcedureStep(
				Integer.valueOf(split[0]),
				Integer.valueOf(split[1]),
				Integer.valueOf(split[2])
				);
	}

	private static List<CrateStack> readCrateStacks() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		ReverseListIterator<String> reverseListIterator = new ReverseListIterator<>(lines);

		while(reverseListIterator.hasNext()) {
			if(StringUtils.isBlank(reverseListIterator.next())) {
				break;
			}
		}
		
		List<CrateStack> crateStacks = createCrateStacks(reverseListIterator.next());

		while(reverseListIterator.hasNext()) {
			addCrates(crateStacks, reverseListIterator.next());
		}

		return crateStacks;
	}

	private static void addCrates(List<CrateStack> crateStacks, String line) {
		int crateStackIndex = 0;
		for(int i = 1; i < line.length(); i=i+4) {
			String crate = String.valueOf(line.charAt(i));
			if(StringUtils.isNotBlank(crate)) {
				crateStacks.get(crateStackIndex).addCrate(crate);	
			}
			crateStackIndex++;
		}
	}

	private static List<CrateStack> createCrateStacks(String line) {
		line = line.trim();
		String[] split = line.split("\\s+");
		
		List<CrateStack> crateStacks = new ArrayList<>();
		for(int i = 0; i < split.length; i++) {
			crateStacks.add(new CrateStack());
		}
		
		return crateStacks;
	}

}
