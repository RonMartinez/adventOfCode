package year2023.day5;

import java.util.ArrayList;
import java.util.List;

public class Range {

	private Long start;
	private Long end;
	
	public Range(Long start, Long end) {
		this.start = start;
		this.end = end;
	}
	
	public String toString() {
		return start + " - " + end;
	}

	public List<Range> remove(Range range) {
		List<Range> result = new ArrayList<>();

		if(isIntersecting(range)) {
			Range intersection = intersection(range);
			if(start < intersection.getStart()) {
				result.add(new Range(start, intersection.getStart()));
			}
			if(end > intersection.getEnd()) {
				result.add(new Range(intersection.getEnd(), end));
			}
		} else {
			result.add(this);
		}
		
		return result;
	}

	public Range intersection(Range range) {
		Range intersection = null;
		if(isIntersecting(range)) {
			intersection = new Range(Math.max(start, range.getStart()), Math.min(end, range.getEnd()));
		}
		return intersection;
	}

	public boolean isIntersecting(Range range) {
		return start < range.getEnd()
			&& range.start < end;
	}

	public boolean containsValue(Long value) {
		return start <= value
				&& value < end;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}
	
	
}