package year2023.day2;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day2b {

//	private static final String FILENAME = "src/main/resources/2023/day2InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day2Input.txt";

	public static void main(String[] args) throws IOException {
		List<String> lines = readLines();
		
		List<Game> games = lines.stream()
				.map(Day2b::processGame)
				.collect(Collectors.toList());
		
		games.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));

		Long result = games.stream()
				.map(Game::getPower)
				.reduce(0L, Long::sum);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static List<String> readLines() throws IOException {
		return IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
	}

	private static Game processGame(String line) {
		String[] lineSplit = line.split(": ");
		
		Long id = Long.valueOf(lineSplit[0].replaceAll("Game ", ""));

		Game game = new Game(id);
		
		String reveals[] = lineSplit[1].split("; ");
		
		for(String reveal : reveals) {
			processReveal(reveal, game);
		}
		return game; 
	}

	private static void processReveal(String reveal, Game game) {
		String[] countAndColours = reveal.split(", ");
		
		for(String countAndColour : countAndColours) {
			ColourCount colourCount = processCountAndColour(countAndColour);
			game.addColourCount(colourCount);
		}
	}

	private static ColourCount processCountAndColour(String countAndColour) {
		String[] countAndColourSplit = countAndColour.split(" ");
		
		Long count = Long.valueOf(countAndColourSplit[0]);
		Colour colour = getColour(countAndColourSplit[1]);
		
		return new ColourCount(colour, count);
	}

	private static Colour getColour(String name) {
		Colour colour = null;
		
		if(Colour.RED.getName().equals(name)) {
			colour = Colour.RED;
		} else if(Colour.GREEN.getName().equals(name)) {
			colour = Colour.GREEN;
		} else if(Colour.BLUE.getName().equals(name)) {
			colour = Colour.BLUE;
		} else {
			throw new RuntimeException("unknown colour");
		}

		return colour;
	}

	private static boolean isPossible(Game game, List<ColourCount> colourCountLimits) {
		return colourCountLimits.stream()
				.allMatch(ccl -> isPossible(game, ccl));
	}

	private static boolean isPossible(Game game, ColourCount colourCountLimit) {
		ColourCount colourCount = game.getColourCountByColour(colourCountLimit.getColour());
		
		boolean possible = false;
		if(colourCount != null
				&& colourCount.getCount() <= colourCountLimit.getCount() 
				) {
			possible = true;
		}
		
		return possible;
	}

}
