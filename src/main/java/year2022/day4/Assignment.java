package year2022.day4;

public class Assignment {

	private Long minimumSectionId;
	private Long maximumSectionId;
	
	public Assignment(Long minimumSectionId, Long maximumSectionId) {
		this.minimumSectionId = minimumSectionId;
		this.maximumSectionId = maximumSectionId;
	}

	public Long getMinimumSectionId() {
		return minimumSectionId;
	}

	public void setMinimumSectionId(Long minimumSectionId) {
		this.minimumSectionId = minimumSectionId;
	}

	public Long getMaximumSectionId() {
		return maximumSectionId;
	}

	public void setMaximumSectionId(Long maximumSectionId) {
		this.maximumSectionId = maximumSectionId;
	}

}
