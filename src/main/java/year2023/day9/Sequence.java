package year2023.day9;

import java.util.ArrayList;
import java.util.List;

import util.ListHelper;

public class Sequence {

	private List<Long> values;
	private Sequence differenceSequence;
	
	public Sequence(List<Long> values) {
		this.values = values;
		this.differenceSequence = createDifferenceSequence();
		if(differenceSequence == null) {
			values.add(0L);
			values.add(0, 0L);
		} else {
			values.add(ListHelper.getLast(values) + ListHelper.getLast(differenceSequence.getValues()));
			values.add(0, values.get(0) - differenceSequence.getValues().get(0));
		}
	}
	
	private Sequence createDifferenceSequence() {
		Sequence differenceSequence = null;
		
		if( ! allZeroes() ) {
			List<Long> differenceSequenceValues = new ArrayList<>();

			Long lastValue = values.get(0);
			for(int i = 1; i < values.size(); i++) {
				Long currentValue = values.get(i);
				differenceSequenceValues.add(currentValue - lastValue);
				lastValue = currentValue;
			}
			differenceSequence = new Sequence(differenceSequenceValues);
		}
		
		return differenceSequence;
	}
	
	public boolean allZeroes() {
		return values.stream()
				.allMatch(v -> 0L == v);
	}


	public List<Long> getValues() {
		return values;
	}

	public void setValues(List<Long> values) {
		this.values = values;
	}

	public Sequence getDifferenceSequence() {
		return differenceSequence;
	}

	public void setDifferenceSequence(Sequence differenceSequence) {
		this.differenceSequence = differenceSequence;
	}


}