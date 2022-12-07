package year2022.day7;

public class AocFile implements AocFileSystemItem {

	private String name;
	private Long size;
	private AocDirectory parentAocDirectory;

	public AocFile(String name, Long size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public boolean isAocDirectory() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public AocDirectory getParentAocDirectory() {
		return parentAocDirectory;
	}

	public void setParentAocDirectory(AocDirectory parentAocDirectory) {
		this.parentAocDirectory = parentAocDirectory;
	}

}
