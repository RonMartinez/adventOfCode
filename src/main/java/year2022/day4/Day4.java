package year2022.day4;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day4 {

//	private static final String FILENAME = "src/main/resources/2022/day4InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day4Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<AssignmentGroup> assignmentGroups = readAssignmentGroups();
		
		assignmentGroups.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Set<AssignmentGroup> assignmentGroupsThatContainAnyOther =  assignmentGroups.stream()
				.filter(ag -> ! ag.getAssignmentsThatContainAnyOther().isEmpty())
				.collect(Collectors.toSet());
		
		System.out.println(assignmentGroupsThatContainAnyOther.size());
		
		System.out.println("done");
	}

	private static List<AssignmentGroup> readAssignmentGroups() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);

		return lines.stream()
				.map(line -> createAssignmentGroup(line))
				.collect(Collectors.toList());
	}

	private static AssignmentGroup createAssignmentGroup(String line) {
		AssignmentGroup assignmentGroup = new AssignmentGroup();
		
		String[] assignments = line.split(",");
		
		for(String assignmentString : assignments) {
			assignmentGroup.addAssignment(createAssignment(assignmentString));
		}
		
		return assignmentGroup;
	}
	
	private static Assignment createAssignment(String assignmentString) {
		String[] sections = assignmentString.split("-");
		
		return new Assignment(Long.valueOf(sections[0]), Long.valueOf(sections[1]));
	}

}
