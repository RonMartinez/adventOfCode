package year2022.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AocDirectory implements AocFileSystemItem {
	
	private String name;
	private List<AocFileSystemItem> aocFileSystemItems;
	private AocDirectory parentAocDirectory;

	public AocDirectory(String name) {
		this.name = name;
	}
	
	public void addAocFileSystemItem(AocFileSystemItem aocFileSystemItem) {
		aocFileSystemItem.setParentAocDirectory(this);
		getAocFileSystemItems().add(aocFileSystemItem);
	}
	
	public Set<AocDirectory> getAllDirectories() {
		Set<AocDirectory> allDirectories = getAocDirectories();
		Set<AocDirectory> subDirectories = getAocDirectories().stream()
				.flatMap(ad -> ad.getAllDirectories().stream())
				.collect(Collectors.toSet());
		allDirectories.addAll(subDirectories);
		return allDirectories;
		
	}
	
	public AocDirectory getAocDirectory(String name) {
		return getAocDirectories().stream()
				.filter(ad -> ad.getName().equals(name))
				.findFirst().orElse(null);
	}

	public Set<AocDirectory> getAocDirectories() {
		return getAocFileSystemItems().stream()
				.filter(AocFileSystemItem::isAocDirectory)
				.map(afsi -> (AocDirectory) afsi)
				.collect(Collectors.toSet());
	}

	public Set<AocFile> getAocFiles() {
		return getAocFileSystemItems().stream()
				.filter(afsi -> !afsi.isAocDirectory())
				.map(afsi -> (AocFile) afsi)
				.collect(Collectors.toSet());
	}

	@Override
	public Long getSize() {
		return getAocFileSystemItems().stream()
				.map(AocFileSystemItem::getSize)
				.reduce(0L, Long::sum);
	}

	@Override
	public boolean isAocDirectory() {
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AocFileSystemItem> getAocFileSystemItems() {
		if(aocFileSystemItems == null) {
			aocFileSystemItems = new ArrayList<>();
		}
		return aocFileSystemItems;
	}

	public void setAocFileSystemItems(List<AocFileSystemItem> aocFileSystemItems) {
		this.aocFileSystemItems = aocFileSystemItems;
	}

	public AocDirectory getParentAocDirectory() {
		return parentAocDirectory;
	}

	public void setParentAocDirectory(AocDirectory parentAocDirectory) {
		this.parentAocDirectory = parentAocDirectory;
	}

}
