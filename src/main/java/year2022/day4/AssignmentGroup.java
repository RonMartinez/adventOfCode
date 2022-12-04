package year2022.day4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AssignmentGroup {

	private List<Assignment> assignments;
	
	public void addAssignment(Assignment assignment) {
		getAssignments().add(assignment);
	}

	public Set<Assignment> getAssignmentsThatContainAllOthers() {
		return assignments.stream()
				.filter(a -> doesAssignmentContainAllOthers(a))
				.collect(Collectors.toSet());
	}

	protected boolean doesAssignmentContainAllOthers(Assignment assignment) {
		return assignments.stream()
				.filter(other -> ! assignment.equals(other))
				.allMatch(other -> doesAssignmentContainOther(assignment, other));
	}

	public Set<Assignment> getAssignmentsThatContainAnyOther() {
		return assignments.stream()
				.filter(a -> doesAssignmentContainAnyOther(a))
				.collect(Collectors.toSet());
	}

	protected boolean doesAssignmentContainAnyOther(Assignment assignment) {
		return assignments.stream()
				.filter(other -> ! assignment.equals(other))
				.anyMatch(other -> doesAssignmentContainOther(assignment, other));
	}

	public boolean doesAssignmentContainOther(Assignment assignment, Assignment other) {
		return assignment.getMinimumSectionId() <= other.getMinimumSectionId()
				&& assignment.getMaximumSectionId() >= other.getMaximumSectionId();
	}

	public Set<Assignment> getAssignmentsThatOverlapAllOthers() {
		return assignments.stream()
				.filter(a -> doesAssignmentOverlapAllOthers(a))
				.collect(Collectors.toSet());
	}

	protected boolean doesAssignmentOverlapAllOthers(Assignment assignment) {
		return assignments.stream()
				.filter(other -> ! assignment.equals(other))
				.allMatch(other -> doesAssignmentOverlapOther(assignment, other));
	}

	public Set<Assignment> getAssignmentsThatOverlapAnyOther() {
		return assignments.stream()
				.filter(a -> doesAssignmentOverlapAnyOther(a))
				.collect(Collectors.toSet());
	}

	protected boolean doesAssignmentOverlapAnyOther(Assignment assignment) {
		return assignments.stream()
				.filter(other -> ! assignment.equals(other))
				.anyMatch(other -> doesAssignmentOverlapOther(assignment, other));
	}

	public boolean doesAssignmentOverlapOther(Assignment assignment, Assignment other) {
		return (
						assignment.getMinimumSectionId() <= other.getMinimumSectionId()
						&& assignment.getMaximumSectionId() >= other.getMinimumSectionId()
				)
				|| (
						assignment.getMinimumSectionId() <= other.getMaximumSectionId()
						&& assignment.getMaximumSectionId() >= other.getMaximumSectionId()
				)
		;
	}

	public List<Assignment> getAssignments() {
		if(assignments == null) {
			assignments = new ArrayList<>();
		}
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}
	
}
