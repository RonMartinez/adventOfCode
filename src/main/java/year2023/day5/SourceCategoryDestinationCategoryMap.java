package year2023.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SourceCategoryDestinationCategoryMap {

	private Category sourceCategory;
	private Category destinationCategory;
	private List<MapRange> mapRanges;
	
	public SourceCategoryDestinationCategoryMap(Category sourceCategory, Category destinationCategory) {
		this.sourceCategory = sourceCategory;
		this.destinationCategory = destinationCategory;
	}

	public List<Range> findDestinationRanges(List<Range> ranges) {
		System.out.println("input ranges");
		ranges.forEach(r -> System.out.println(r.toString()));
		
		System.out.println("map ranges");
		mapRanges.stream()
				.forEach(mr -> System.out.println(mr.toString()));
		
		List<Range> destinationRanges = new ArrayList<>();
		
		List<Range> overlappingRanges = new ArrayList<>();
		for(MapRange mapRange : mapRanges) {
			for(Range range : ranges) {
				Range intersection = mapRange.getSourceRange().intersection(range);
				if(intersection != null) {
					overlappingRanges.add(intersection);
				}
			}
		}
		System.out.println("overlapping ranges");
		overlappingRanges.forEach(r -> System.out.println(r.toString()));
		
		List<Range> nonOverlappingRanges = new ArrayList<>();
		for(Range range : ranges) {
			nonOverlappingRanges.addAll(findNonOverlappingRanges(overlappingRanges, range));
		}
		for(Range range : ranges) {
			boolean foundIntersection = false;
			for(Range overlappingRange : overlappingRanges) {
				if(range.isIntersecting(overlappingRange)) {
					foundIntersection = true;
					break;
				}
			}
			if( ! foundIntersection) {
				nonOverlappingRanges.add(range);
			}
		}

		System.out.println("nonOverlapping ranges");
		nonOverlappingRanges.forEach(r -> System.out.println(r.toString()));
		
		for(Range overlappingRange : overlappingRanges) {
			MapRange mapRange = mapRanges.stream()
					.filter(mr -> mr.getSourceRange().isIntersecting(overlappingRange))
					.findFirst().orElse(null);
			
			if(mapRange == null) {
				throw new RuntimeException("should have found an overlap");
			}
			
			if(mapRange.getSourceRange().getStart() > mapRange.getDestinationRange().getStart()) {
				Long start = overlappingRange.getStart() - (mapRange.getSourceRange().getStart() - mapRange.getDestinationRange().getStart());
				Long end = overlappingRange.getEnd() - (mapRange.getSourceRange().getStart() - mapRange.getDestinationRange().getStart());
				destinationRanges.add(new Range(start, end));	
			} else {
				Long start = overlappingRange.getStart() + (mapRange.getDestinationRange().getStart() - mapRange.getSourceRange().getStart());
				Long end = overlappingRange.getEnd() + (mapRange.getDestinationRange().getStart() - mapRange.getSourceRange().getStart());
				destinationRanges.add(new Range(start, end));	
			}
		}
		
		destinationRanges.addAll(nonOverlappingRanges);
		System.out.println("destinationRanges ranges");
		destinationRanges.forEach(r -> System.out.println(r.toString()));
		
		return destinationRanges;
	}

	private List<Range> findNonOverlappingRanges(List<Range> overlappingRanges, Range range) {
		List<Range> nonOverlappingRanges = new ArrayList<>();
		
		for(Range overlappingRange : overlappingRanges) {
			if(range.isIntersecting(overlappingRange)) {
				List<Range> subOverlappingRanges = overlappingRanges.stream()
						.filter(or -> ! overlappingRange.equals(or))
						.collect(Collectors.toList());
				
				Long beforeStart = range.getStart();
				Long beforeEnd = overlappingRange.getStart();
				if(beforeStart < beforeEnd) {
					Range beforeRange = new Range(beforeStart, beforeEnd);
					if(overlappingRanges.stream()
							.allMatch(or -> or.intersection(beforeRange) == null)) {
						nonOverlappingRanges.add(beforeRange);
					}
//					if(findNonOverlappingRanges(overlappingRanges, beforeRange).isEmpty()) {
//						nonOverlappingRanges.add(beforeRange);;							
//					}
				}

				Long afterStart = overlappingRange.getEnd();
				Long afterEnd = range.getEnd();
				if(afterStart < afterEnd) {
					Range afterRange = new Range(afterStart, afterEnd);
					if(overlappingRanges.stream()
							.allMatch(or -> or.intersection(afterRange) == null)) {
						nonOverlappingRanges.add(afterRange);
					}
//					if(findNonOverlappingRanges(overlappingRanges, afterRange).isEmpty()) {
//						nonOverlappingRanges.add(afterRange);;	
//					}
				}
			}
		}
		
		return nonOverlappingRanges;
	}

	public Long findDestinationNumber(Long sourceNumber) {
		Long destinationNumber = sourceNumber;
		
		MapRange mapRange = mapRanges.stream()
				.filter(mr -> mr.getSourceRange().containsValue(sourceNumber))
				.findFirst().orElse(null);
		
		if(mapRange != null) {
			destinationNumber = mapRange.convertValueFromSourceToDestination(sourceNumber);
		}
		
		return destinationNumber;
	}
	
	public void addMapRange(MapRange mapRange) {
		getMapRanges().add(mapRange);
	}

	public Category getSourceCategory() {
		return sourceCategory;
	}

	public void setSourceCategory(Category sourceCategory) {
		this.sourceCategory = sourceCategory;
	}

	public Category getDestinationCategory() {
		return destinationCategory;
	}

	public void setDestinationCategory(Category destinationCategory) {
		this.destinationCategory = destinationCategory;
	}

	public List<MapRange> getMapRanges() {
		if(mapRanges == null) {
			mapRanges = new ArrayList<>();
		}
		return mapRanges;
	}

	public void setMapRanges(List<MapRange> mapRanges) {
		this.mapRanges = mapRanges;
	}

}
