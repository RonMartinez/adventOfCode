package year2023.day5;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class Day5 {

//	private static final String FILENAME = "src/main/resources/2023/day5InputSample.txt";
	private static final String FILENAME = "src/main/resources/2023/day5Input.txt";
	
	private static List<Long> seedNumbers;
	private static SourceCategoryDestinationCategoryMap seedToSoilMap = new SourceCategoryDestinationCategoryMap(Category.SEED, Category.SOIL);
	private static SourceCategoryDestinationCategoryMap soilToFertlizerMap = new SourceCategoryDestinationCategoryMap(Category.SOIL, Category.FERTILIZER);
	private static SourceCategoryDestinationCategoryMap fertilizerToWaterMap = new SourceCategoryDestinationCategoryMap(Category.FERTILIZER, Category.WATER);
	private static SourceCategoryDestinationCategoryMap waterToLightMap = new SourceCategoryDestinationCategoryMap(Category.WATER, Category.LIGHT);
	private static SourceCategoryDestinationCategoryMap lightToTemperatureMap = new SourceCategoryDestinationCategoryMap(Category.LIGHT, Category.TEMPERATURE);
	private static SourceCategoryDestinationCategoryMap temperatureToHumidityMap = new SourceCategoryDestinationCategoryMap(Category.TEMPERATURE, Category.HUMIDITY);
	private static SourceCategoryDestinationCategoryMap humidityToLocationMap = new SourceCategoryDestinationCategoryMap(Category.HUMIDITY, Category.LOCATION);

	public static void main(String[] args) throws IOException {
		readLines();
		
		List<Long> locationNumbers = new ArrayList<>();
		for(Long seedNumber : seedNumbers) {
			Long soilNumber = seedToSoilMap.findDestinationNumber(seedNumber);
			Long fertilizerNumber = soilToFertlizerMap.findDestinationNumber(soilNumber);
			Long waterNumber = fertilizerToWaterMap.findDestinationNumber(fertilizerNumber);
			Long lightNumber = waterToLightMap.findDestinationNumber(waterNumber);
			Long temperatureNumber = lightToTemperatureMap.findDestinationNumber(lightNumber);
			Long humidityNumber = temperatureToHumidityMap.findDestinationNumber(temperatureNumber);
			Long locationNumber = humidityToLocationMap.findDestinationNumber(humidityNumber);
			
			locationNumbers.add(locationNumber);
		}
		
		locationNumbers.sort(Comparator.naturalOrder());
		
		Long result = locationNumbers.get(0);
		
		System.out.println(result);
		
		System.out.println("done");
	}

	private static void readLines() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		String seedsLine = lines.get(0);
		seedsLine = seedsLine.replace("seeds: ", "");
		seedNumbers = Arrays.stream(seedsLine.split(" "))
				.map(Long::valueOf)
				.collect(Collectors.toList());
		
		SourceCategoryDestinationCategoryMap currentMap = null;
		for(int i = 2; i < lines.size(); i++) {
			String line = lines.get(i);
			if(StringUtils.isNotBlank(line)) {
				if(line.matches(".*map\\:")) {
					currentMap = determineCurrentMap(line);
				} else {
					List<Long> mapNumbers = Arrays.stream(line.split(" "))
							.map(Long::valueOf)
							.collect(Collectors.toList());
					
					MapRange mapRange = new MapRange(mapNumbers.get(1), mapNumbers.get(0), mapNumbers.get(2));
					currentMap.addMapRange(mapRange);
				}
			}
		}
	}

	private static SourceCategoryDestinationCategoryMap determineCurrentMap(String line) {
		SourceCategoryDestinationCategoryMap currentMap = null;
		
		if("seed-to-soil map:".equals(line)) {
			currentMap = seedToSoilMap;
		} else if("soil-to-fertilizer map:".equals(line)) {
			currentMap = soilToFertlizerMap;
		} else if("fertilizer-to-water map:".equals(line)) {
			currentMap = fertilizerToWaterMap;
		} else if("water-to-light map:".equals(line)) {
			currentMap = waterToLightMap;
		} else if("light-to-temperature map:".equals(line)) {
			currentMap = lightToTemperatureMap;
		} else if("temperature-to-humidity map:".equals(line)) {
			currentMap = temperatureToHumidityMap;
		} else if("humidity-to-location map:".equals(line)) {
			currentMap = humidityToLocationMap;
		} else {
			throw new RuntimeException("unknown map");
		}

		return currentMap;
	}

}
;