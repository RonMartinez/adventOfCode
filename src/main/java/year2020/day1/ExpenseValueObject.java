package year2020.day1;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class ExpenseValueObject {
	
	private List<Integer> entries;

	public ExpenseValueObject(List<Integer> entries) {
		this.entries = entries;
	}
	
	public Integer sum() {
		Integer sum = 0;
		for(Integer entry : entries) {
			sum += entry;
		}
		return sum;	
	}

	public Integer product() {
		Integer product = null;
		if(CollectionUtils.isNotEmpty(entries)) {
			product = 1;
			for(Integer entry : entries) {
				product *= entry;
			}
		}
		return product;	
	}
	
	/* Getters / Setters */

	public List<Integer> getEntries() {
		return entries;
	}

	public void setEntries(List<Integer> entries) {
		this.entries = entries;
	}
	
}
