package year2020.day22;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Day22 {

//	private static final String FILENAME = "src/main/resources/2020/day22InputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day22Input.txt";
	
	public static void main(String[] args) throws IOException {
		List<Player> players = readPlayers();
		
		players.forEach(p -> System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.MULTI_LINE_STYLE)));
		
		Set<Player> playersStillInGame = players.stream()
				.filter(p -> CollectionUtils.isNotEmpty(p.getCards()))
				.collect(Collectors.toSet());
		while(playersStillInGame.size() > 1) {
			Map<Player, Long> playedCards = new HashMap<>();
			for(Player player : playersStillInGame) {
				playedCards.put(player, player.getNextCard());
			}

			Entry<Player, Long> entry = playedCards.entrySet().stream()
					.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
					.findFirst().orElse(null);
			
			Player winningHandPlayer = entry.getKey();
			winningHandPlayer.addCard(playedCards.get(winningHandPlayer));
			
			List<Long> sortedLeftOverPlayedCards = playedCards.keySet().stream()
					.filter(k -> ! k.equals(winningHandPlayer))
					.map(k -> playedCards.get(k))
					.sorted(Comparator.naturalOrder()) //trying to account for more than one player
					.collect(Collectors.toList());
			
			sortedLeftOverPlayedCards.forEach(c -> winningHandPlayer.addCard(c));
			
			playersStillInGame = players.stream()
					.filter(p -> CollectionUtils.isNotEmpty(p.getCards()))
					.collect(Collectors.toSet());
		}
		
		players.forEach(p -> System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.MULTI_LINE_STYLE)));

		Player winningPlayer = playersStillInGame.iterator().next();
		
		int score = 0;
		int value = 1;
		for(int i = winningPlayer.getCards().size() - 1; i >= 0; i--) {
			Long card = winningPlayer.getCards().get(i);
			
			Long cardValue = card * value;
			
			score += cardValue;
			
			value++;
		}

		System.out.println(score);
	}

	private static List<Player> readPlayers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		List<Player> players = new ArrayList<>();
		Player currentPlayer = null;
		for(String line : lines) {
			currentPlayer = processLine(line, currentPlayer, players);
		}
		
		return players;
	}

	private static Player processLine(String line, Player currentPlayer, List<Player> players) {
		if(StringUtils.isNotBlank(line)) {
			if(line.matches("Player.*")) {
				currentPlayer = new Player();
				String idString = line;
				idString = idString.replaceAll("Player ", "");
				idString = idString.replaceAll(":", "");
				Long id = Long.parseLong(idString);
				currentPlayer.setId(id);
				players.add(currentPlayer);
			} else {
				Long card = Long.parseLong(line);
				currentPlayer.addCard(card);
			}
		}
		return currentPlayer;
	}

}
