package year2022.day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day7 {

//	private static final String FILENAME = "src/main/resources/2022/day7InputSample.txt";
	private static final String FILENAME = "src/main/resources/2022/day7Input.txt";

	public static final String COMMAND = "$";
	public static final String CHANGE_DIRECTORY = "cd";
	public static final String LIST = "ls";
	public static final String DIRECTORY = "dir";
	public static final String PARENT_DIRECTORY = "..";
	public static final String ROOT_DIRECTORY = "/";
	
	public static final Long SIZE_LIMIT = 100_000L;
	
	public static void main(String[] args) throws IOException {
		AocDirectory rootDirectory = readAocFileSystemItems();
		
		Set<AocDirectory> allDirectories = rootDirectory.getAllDirectories();
		
		Set<AocDirectory> smallDirectories = allDirectories.stream()
				.filter(ad -> ad.getSize().compareTo(SIZE_LIMIT) <= 0)
				.collect(Collectors.toSet());

		Long totalSizeOfSmallDirectories = smallDirectories.stream()
				.map(AocDirectory::getSize)
				.reduce(0L, Long::sum);
		
		System.out.println(totalSizeOfSmallDirectories);
		
		System.out.println("done");
	}

	private static AocDirectory readAocFileSystemItems() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		//bootstrap to root directory
		AocDirectory rootDirectory = new AocDirectory("/");
		
		AocDirectory currentDirectory = rootDirectory;
		ListIterator<String> iterator = lines.listIterator();
		while(iterator.hasNext()) {
			currentDirectory = processCommand(currentDirectory, iterator, rootDirectory);
		}
		
		return rootDirectory;
	}

	protected static AocDirectory processCommand(AocDirectory currentDirectory, ListIterator<String> iterator, AocDirectory rootDirectory) {
		String line = iterator.next();
		if( ! line.startsWith(COMMAND)) {
			throw new RuntimeException("expecting command");
		}
		
		String command = line.replace(COMMAND + " ", "");
		if(command.startsWith(CHANGE_DIRECTORY)) {
			currentDirectory = processChangeDirectoryCommand(currentDirectory, command, rootDirectory);
		} else if (command.startsWith(LIST)) {
			processListCommand(currentDirectory, iterator);
		} else {
			throw new RuntimeException("unsupported command");
		}

		return currentDirectory;
	}

	protected static void processListCommand(AocDirectory currentDirectory, ListIterator<String> iterator) {
		boolean commandFound = false;
		while( ! commandFound
				&& iterator.hasNext()
				) {
			commandFound = processAocFileSystemListing(currentDirectory, iterator);
		}
	}

	protected static boolean processAocFileSystemListing(AocDirectory currentDirectory, ListIterator<String> iterator) {
		boolean commandFound = false;
		
		String aocFileSystemItemString = iterator.next();
		if(aocFileSystemItemString.startsWith(COMMAND)) {
			commandFound = true;
			iterator.previous();
		} else if(aocFileSystemItemString.startsWith(DIRECTORY)) {
			String directoryName = aocFileSystemItemString.replace(DIRECTORY + " ", "");
			AocDirectory aocDirectory = new AocDirectory(directoryName);
			currentDirectory.addAocFileSystemItem(aocDirectory);
		} else {
			//should probably detect it's a file, i.e. starts with a size followed by a name, but whatever
			String[] fileParts = aocFileSystemItemString.split(" ");
			AocFile aocFile = new AocFile(fileParts[1], Long.valueOf(fileParts[0]));
			currentDirectory.addAocFileSystemItem(aocFile);
		}
		
		return commandFound;
	}

	protected static AocDirectory processChangeDirectoryCommand(AocDirectory currentDirectory, String command, AocDirectory rootDirectory) {
		String directoryName = command.replace(CHANGE_DIRECTORY + " ", "");
		if(directoryName.equals(ROOT_DIRECTORY)) {
			currentDirectory = rootDirectory;
		} else if(directoryName.equals(PARENT_DIRECTORY)) {
			currentDirectory = currentDirectory.getParentAocDirectory();
		} else {
			currentDirectory = currentDirectory.getAocDirectory(directoryName);	
		}
		return currentDirectory;
	}
	
}
