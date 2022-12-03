package year2020.day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day7Optimized {
	
//	private static final String FILENAME = "src/main/resources/2020/day7InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day7Input.txt";
	
	private static Set<String> knownColours = new HashSet<>();
	
	public static void main(String[] args) throws IOException {
		List<Bag> bags = readBags();
		
		bags.forEach(b -> System.out.println(ReflectionToStringBuilder.toString(b, ToStringStyle.MULTI_LINE_STYLE)));
		
		List resultBags = bags.stream()
				.filter(b -> bagContains(b, "shiny gold"))
				.collect(Collectors.toList());
		
		System.out.println(resultBags.size());
	}
	
	private static boolean bagContains(Bag bag, String colour) {
		if(knownColours.contains(colour)) {
			return true;
		}
		boolean found = getDistinctChildBags(bag).stream()
				.anyMatch(b -> childBagContains(b, colour));
		if(found) {
			knownColours.add(bag.getColour());
		}
		return found;
		
	}

	private static Set<Bag> getDistinctChildBags(Bag bag) {
		Set<String> colours = bag.getChildBags().stream()
				.map(Bag::getColour)
				.collect(Collectors.toSet());
		
		Set<Bag> bags = new HashSet<>();
		for(String colour : colours) {
			bags.add(bag.getChildBags().stream()
					.filter(b -> b.getColour().equals(colour))
					.findFirst().orElse(null));
			
		}
		
		return bags;
	}
	
	private static boolean childBagContains(Bag bag, String colour) {
		boolean found = false;
		if(bag.getColour().equals(colour)) {
			found = true;
			
		} else {
			found = getDistinctChildBags(bag).stream()
					.anyMatch(b -> childBagContains(b, colour));
			if(found) {
				knownColours.add(bag.getColour());
			}
		}
		
		return found;
	}

	private static List<Bag> readBags() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Bag> bags = new ArrayList<>();
		
		for(String line : lines) {
			processLine(line, bags);
		}

		return bags;
	}

	private static void processLine(String line, List<Bag> bags) {
		String[] splitString = line.split(" contain ");
		
		String parentColour = splitString[0].replace(" bags", "");
		
		Bag bag = getOrCreateBag(bags, parentColour);
		
		String contains = splitString[1];
		for(String childBagSplit : contains.split(",")) {
			childBagSplit = processChildBagSplit(bags, bag, childBagSplit);
		}
	}

	private static String processChildBagSplit(List<Bag> bags, Bag bag, String childBagSplit) {
		childBagSplit = childBagSplit.replace(" bags", "");
		childBagSplit = childBagSplit.replace(" bag", "");
		childBagSplit = childBagSplit.replace(".", "");
		Pattern pattern = Pattern.compile("(\\d)+ (.+$)");
		Matcher matcher = pattern.matcher(childBagSplit);
		if(matcher.find()) {
			int count = Integer.parseInt(matcher.group(1));
			String childColour = matcher.group(2);
			for(int i = 0; i < count; i++) {
				Bag childBag = getOrCreateBag(bags, childColour);
				bag.addChildBag(childBag);
			}
		}
		return childBagSplit;
	}

	private static Bag getOrCreateBag(List<Bag> bags, String colour) {
		Bag bag = getBag(bags, colour);
		if(bag == null) {
			bag = new Bag();
			bag.setColour(colour);
			bags.add(bag);
		}
		return bag;
	}

	private static Bag getBag(List<Bag> bags, String colour) {
		return bags.stream()
				.filter(b -> colour.equals(b.getColour()))
				.findFirst().orElse(null);
	}

}
