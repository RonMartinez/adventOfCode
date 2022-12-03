package year2020.day22;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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

public class Day22b {

//	private static final String FILENAME = "src/main/resources/2020/day22InputSample.txt";
//	private static final String FILENAME = "src/main/resources/2020/day22bInputSample.txt";
	private static final String FILENAME = "src/main/resources/2020/day22Input.txt";
	
	public static void main(String[] args) throws IOException {
		Set<Player> players = readPlayers();
		
		players.forEach(p -> System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.MULTI_LINE_STYLE)));
		
		Player winningPlayer = playGame(players);
		
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

	private static Player playGame(Set<Player> players) {
		Set<Map<Long, List<Long>>> previousHands = new HashSet<>();
		
		Set<Player> playersStillInGame = players.stream()
				.filter(p -> CollectionUtils.isNotEmpty(p.getCards()))
				.collect(Collectors.toSet());
		
		while(playersStillInGame.size() > 1) {
			Map<Long, List<Long>> hand = new HashMap<>();
			for(Player player : playersStillInGame) {
				hand.put(player.getId(), player.getCards());
			}
			if(previousHands.contains(hand)) {
				playersStillInGame = players.stream()
						.filter(p -> p.getId().equals(1L))
						.collect(Collectors.toSet());
			} else {
				previousHands.add(hand);

				playHand(playersStillInGame);
				
				playersStillInGame = players.stream()
						.filter(p -> CollectionUtils.isNotEmpty(p.getCards()))
						.collect(Collectors.toSet());
			}
		}
		
		players.forEach(p -> System.out.println(ReflectionToStringBuilder.toString(p, ToStringStyle.MULTI_LINE_STYLE)));
	
		Player winningPlayer = playersStillInGame.iterator().next();
		
		return winningPlayer;
	}

	private static void playHand(Set<Player> playersStillInGame) {
		Map<Player, Long> playedCards = new HashMap<>();
		for(Player player : playersStillInGame) {
			playedCards.put(player, player.getNextCard());
		}

		boolean shouldRecurse = playedCards.entrySet().stream()
				.allMatch(e -> e.getValue() <= e.getKey().getCards().size() );
		
		Player winningHandPlayer = null;
		if(shouldRecurse) {
			Set<Player> subPlayers = playersStillInGame.stream()
					.map(p -> createSubPlayer(p, playedCards.get(p)))
					.collect(Collectors.toSet());
			
			Player subWinningHandPlayer = playGame(new HashSet<>(subPlayers));
			
			winningHandPlayer = playersStillInGame.stream()
					.filter(p -> p.getId().equals(subWinningHandPlayer.getId()))
					.findFirst().orElse(null);
		} else {
			Entry<Player, Long> entry = playedCards.entrySet().stream()
					.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
					.findFirst().orElse(null);
			
			winningHandPlayer = entry.getKey();
		}

		addCardsToWinningHandPlayer(playedCards, winningHandPlayer);
	}

	private static void addCardsToWinningHandPlayer(Map<Player, Long> playedCards, Player winningHandPlayer) {
		winningHandPlayer.addCard(playedCards.get(winningHandPlayer));
		
		List<Long> sortedLeftOverPlayedCards = playedCards.keySet().stream()
				.filter(k -> ! k.equals(winningHandPlayer))
				.map(k -> playedCards.get(k))
				.sorted(Comparator.naturalOrder()) //trying to account for more than one player
				.collect(Collectors.toList());
		
		sortedLeftOverPlayedCards.forEach(c -> winningHandPlayer.addCard(c));
	}

	private static Player createSubPlayer(Player player, Long playedCard) {
		Player subPlayer = new Player();

		subPlayer.setId(player.getId());
		List<Long> subCards = player.getCards().stream()
				.collect(Collectors.toList());
		subPlayer.setCards(subCards.subList(0, playedCard.intValue()));
		
		return subPlayer;
	}

	private static Set<Player> readPlayers() throws IOException {
		List<String> lines = IOUtils.readLines(new FileInputStream(FILENAME), StandardCharsets.UTF_8);
		
		Set<Player> players = new HashSet<>();
		Player currentPlayer = null;
		for(String line : lines) {
			currentPlayer = processLine(line, currentPlayer, players);
		}
		
		return players;
	}

	private static Player processLine(String line, Player currentPlayer, Set<Player> players) {
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
