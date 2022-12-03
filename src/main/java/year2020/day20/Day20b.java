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

public class Day20b {

//	private static final String FILENAME = "src/main/resources/2020/day20InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day20Input.txt";
	
	private static List<String> messages = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		List<Tile> tiles = readTiles();
		
		tiles.forEach(t -> System.out.println(t.print()));
		
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
		
		
		List<List<Tile>> tileGrid = new ArrayList<>();
		List<Tile> currentRow = new ArrayList<>();
		
		//orient the first corner tile properly
		Tile firstCornerTile = cornerTiles.get(0);
		currentRow.add(firstCornerTile);

		Tile matchingTile1 = tileMap.get(firstCornerTile).get(0);
		Tile matchingTile2 = tileMap.get(firstCornerTile).get(1);
		orientMatchingBorderToTheRight(firstCornerTile, matchingTile1);
		if(matchingTile2.getBorders().contains(firstCornerTile.getBottomBorder())) {
			System.out.println("matchingTile2.getBorders().contains(firstCornerTile.getBottomBorder()): " + matchingTile2.getBorders().contains(firstCornerTile.getBottomBorder()));
		} else {
			orientMatchingBorderToTheRight(firstCornerTile, matchingTile2);
			System.out.println("matchingTile1.getBorders().contains(firstCornerTile.getBottomBorder()): " + matchingTile1.getBorders().contains(firstCornerTile.getBottomBorder()));
		}
		
		Tile lastTile = firstCornerTile;
		Tile nextTile = findTileThatMatchesRightBorder(lastTile, tiles);
		while(getTileGridSize(tileGrid) < tiles.size()) {
			orientMatchingBorderToTheLeft(nextTile, lastTile);
			currentRow.add(nextTile);
			
			lastTile = nextTile;
			nextTile = findTileThatMatchesRightBorder(lastTile, tiles);
			
			if(nextTile == null) {
				tileGrid.add(currentRow);

				lastTile = currentRow.get(0);
				nextTile = findTileThatMatchesBottomBorder(lastTile, tiles);
				
				if(nextTile != null) {
					orientMatchingBorderToTheTop(nextTile, lastTile);
					
					currentRow = new ArrayList<>();
					currentRow.add(nextTile);
					
					lastTile = nextTile;
					nextTile = findTileThatMatchesRightBorder(lastTile, tiles);
				}
			}
			
		}
		System.out.println("tiles.size(): " + tiles.size());
		System.out.println("getTileGridSize(tileGrid): " + getTileGridSize(tileGrid));
		
		long result = 1L;
		for(Tile cornerTile : cornerTiles) {
			result *= cornerTile.getId();
		}
		
		tiles.forEach(Tile::removeBorders);

		Tile actualImage = new Tile();

		for(List<Tile> listOfTiles : tileGrid) {
			for(int i = 0; i < listOfTiles.get(0).getRows().size(); i++) {
				Row row = new Row();
				for(Tile tile : listOfTiles) {
					tile.getRows().get(i).getColumns().forEach(s -> row.getColumns().add(s));
				}
				actualImage.getRows().add(row);
			}
		}
		
		System.out.println(actualImage.print());
		
		actualImage.rotateRight();
		
		System.out.println(actualImage.print());
		
		int monsterCount = 0;
		Tile seaMonster = createSeaMonster();
		monsterCount += countSeaMonsters(actualImage, seaMonster);

		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		actualImage.rotateRight();
		actualImage.flip();
		monsterCount += countSeaMonsters(actualImage, seaMonster);

		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		actualImage.rotateRight();
		monsterCount += countSeaMonsters(actualImage, seaMonster);
		
		long monsterHashCount = seaMonster.getRows().stream()
				.flatMap(r -> r.getColumns().stream())
				.filter(s -> s.equals("#"))
				.count();

		long actualImageHashCount = actualImage.getRows().stream()
				.flatMap(r -> r.getColumns().stream())
				.filter(s -> s.equals("#"))
				.count();
		
		System.out.println("monsterCount: " + monsterCount);
		System.out.println("monsterHashCount: " + monsterHashCount);
		System.out.println("monsterCount*monsterHashCount: " + monsterCount*monsterHashCount);
		System.out.println("actualImageHashCount-monsterCount*monsterHashCount : " + (actualImageHashCount - monsterCount*monsterHashCount) );
	}

	private static int countSeaMonsters(Tile actualImage, Tile seaMonster) {
		int monsterCount = 0;
		for(int y = 0; y < actualImage.getRows().size(); y++) {
			for(int x = 0; x < actualImage.getRows().get(y).getColumns().size(); x++) {
				Tile subTile = actualImage.getSubTile(x,
						y,
						seaMonster.getRows().get(0).getColumns().size(),
						seaMonster.getRows().size()
						);
				
				if(subTile != null) {
					System.out.println(subTile.print());
					if(matches(subTile, seaMonster)) {
						monsterCount++;	
					}
				}
			}
		}
		return monsterCount;
	}
	
	private static boolean matches(Tile subTile, Tile seaMonster) {
		boolean match = true;
		for(int y = 0; y < seaMonster.getRows().size(); y++) {
			for(int x = 0; x < seaMonster.getRows().get(0).getColumns().size(); x++) {
				String seaMonsterCharacter = seaMonster.getRows().get(y).getColumns().get(x);
				String subTileCharacter = subTile.getRows().get(y).getColumns().get(x);
				if(seaMonsterCharacter.equals("#")
						&& ! subTileCharacter.equals("#")
						) {
					return false;
				}
			}
		}
		
		return match;
	}

	private static Tile createSeaMonster() {
		Tile seaMonster = new Tile();
		
		Row row1 = new Row();
		for(char character : "                  # ".toCharArray()) {
			row1.getColumns().add(String.valueOf(character));
		}
		Row row2 = new Row();
		for(char character : "#    ##    ##    ###".toCharArray()) {
			row2.getColumns().add(String.valueOf(character));
		}
		Row row3 = new Row();
		for(char character : " #  #  #  #  #  #   ".toCharArray()) {
			row3.getColumns().add(String.valueOf(character));
		}
		seaMonster.getRows().add(row1);
		seaMonster.getRows().add(row2);
		seaMonster.getRows().add(row3);
		
		return seaMonster;
	}
	
	private static long getTileGridSize(List<List<Tile>> tileGrid) {
		return tileGrid.stream()
				.flatMap(tg -> tg.stream())
				.count();
	}

	private static Tile findTileThatMatchesRightBorder(Tile tile, List<Tile> tiles) {
		return tiles.stream()
				.filter(t -> ! t.equals(tile))
				.filter(t -> t.getBorders().contains(tile.getRightBorder()))
				.findFirst().orElse(null);
	}

	private static Tile findTileThatMatchesBottomBorder(Tile tile, List<Tile> tiles) {
		return tiles.stream()
				.filter(t -> ! t.equals(tile))
				.filter(t -> t.getBorders().contains(tile.getBottomBorder()))
				.findFirst().orElse(null);
	}

	private static void orientMatchingBorderToTheTop(Tile tile1, Tile tile2) {
		if(tile2.getActualBorders().contains(tile1.getTopBorder())) {
			//already in the right spot
		} else if(tile2.getActualBorders().contains(tile1.getRightBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getBottomBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.flip();
		} else if(tile2.getActualBorders().contains(tile1.getLeftBorder())) {
			tile1.rotateRight();
			tile1.flip();
		} else if(tile2.getActualBorders().contains(tile1.getReverseTopBorder())) {
			tile1.flip();
		} else if(tile2.getActualBorders().contains(tile1.getReverseRightBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.flip();
		} else if(tile2.getActualBorders().contains(tile1.getReverseBottomBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getReverseLeftBorder())) {
			tile1.rotateRight();
		}
	}

	private static void orientMatchingBorderToTheLeft(Tile tile1, Tile tile2) {
		orientMatchingBorderToTheRight(tile1, tile2);
		tile1.flip();
	}

	private static void orientMatchingBorderToTheRight(Tile tile1, Tile tile2) {
		if(tile2.getActualBorders().contains(tile1.getTopBorder())) {
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getRightBorder())) {
			//already in the right spot
		} else if(tile2.getActualBorders().contains(tile1.getBottomBorder())) {
			tile1.flip();
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getLeftBorder())) {
			tile1.flip();
		} else if(tile2.getActualBorders().contains(tile1.getReverseTopBorder())) {
			tile1.flip();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getReverseRightBorder())) {
			tile1.flip();
			tile1.rotateRight();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getReverseBottomBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
			tile1.rotateRight();
		} else if(tile2.getActualBorders().contains(tile1.getReverseLeftBorder())) {
			tile1.rotateRight();
			tile1.rotateRight();
		}
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
