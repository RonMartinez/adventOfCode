package year2022.day7;

import java.util.Comparator;

public interface AocFileSystemItem {
	
	public static final Comparator<AocFileSystemItem> SIZE_COMPARATOR = Comparator.comparing(AocFileSystemItem::getSize);

	AocDirectory getParentAocDirectory();
	void setParentAocDirectory(AocDirectory aocDirectory);
	String getName();
	Long getSize();
	boolean isAocDirectory();
	
}
