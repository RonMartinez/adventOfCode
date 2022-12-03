package year2020.day20;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day20 {

//	private static final String FILENAME = "src/main/resources/2020/day20InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day20Input.txt";
	
	private static List<String> messages = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		List<Tile> tiles = readTiles();
		
		tiles.forEach(t -> System.out.println(ReflectionToStringBuilder.toString(t, ToStringStyle.MULTI_LINE_STYLE)));
		
		Map<Tile, List<Tile>> tileMap = new HashMap<>();
		
		for(Tile tile : tiles) {
			tileMap.put(tile, findBorderMatches(tile, tiles));
		}
		
		tileMap.keySet().forEach(t -> {
			System.out.print(t.getId() + " (");
			tileMap.get(t).forEach(m -> System.out.print(m.getId() + ", "));
			System.out.println(")");
		});
		
		List<Tile> cornerTiles = tileMap.keySet().stream()
				.filter(k -> tileMap.get(k).size() == 2)
				.collect(Collectors.toList());
		
		long result = 1L;
		for(Tile cornerTile : cornerTiles) {
			result *= cornerTile.getId();
		}
				
		System.out.println(result);
	}

	private static List<Tile> findBorderMatches(Tile tile, List<Tile> tiles) {
		return tiles.stream()
				.filter(t -> isBorderMatch(t, tile))
				.collect(Collectors.toList());
	}

	private static boolean isBorderMatch(Tile tile1, Tile tile2) {
		boolean borderMatch = false;
		if( ! tile1.equals(tile2)) {
			borderMatch = ! Collections.disjoint(tile1.getBorders(), tile2.getBorders());
		}

		return borderMatch;
	}

	private static List<Tile> readTiles() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Tile> tiles = new ArrayList<>();
		int i = 0;
		Tile tile = null;
		while(i < lines.size()) {
			String line = lines.get(i);
			if(line.startsWith("Tile ")) {
				tile = new Tile();
				String idString = line.replaceAll("Tile ", "");
				idString = idString.replaceAll(":", "");
				tile.setId(Long.parseLong(idString));
				tiles.add(tile);
			} else if(StringUtils.isNotBlank(line)) {
				Row row = new Row();
				for(char character : line.toCharArray()) {
					row.getColumns().add(String.valueOf(character));
				}
				tile.getRows().add(row);
			}
			i++;
		}
		
		return tiles;
	}

}
