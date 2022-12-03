package year2020.day7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day7b {
	
//	private static final String FILENAME = "src/main/resources/2020/day7InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2020/day7bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day7Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Bag> bags = readBags();
		
		bags.forEach(b -> System.out.println(ReflectionToStringBuilder.toString(b, ToStringStyle.MULTI_LINE_STYLE)));
		
		Bag shinyGoldBag = bags.stream()
				.filter(b -> b.getColour().equals("shiny gold"))
				.findFirst().orElse(null);
		
		int count = shinyGoldBag.getChildBags().stream()
				.mapToInt(Day7b::countBags)
				.sum();
		
		System.out.println(count);
	}
	
	private static int countBags(Bag bag) {
		int count = 1;
		return count + bag.getChildBags().stream()
				.mapToInt(Day7b::countBags)
				.sum();
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
