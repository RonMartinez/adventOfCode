package year2023.day6;

import java.util.ArrayList;
import java.util.List;

public class Race {

	private Long time;
	private Long distance;
	
	public Race(Long time, Long distance) {
		this.time = time;
		this.distance = distance;
	}
	
	public List<Long> findWinningHoldTimes() {
		List<Long> winningHoldTimes = new ArrayList<>();
		
		long milliseconds = 0;
		
		while( milliseconds < time) {
			long speed = (long) Math.pow(Double.valueOf(milliseconds), Double.valueOf(1)); 
			long timeTravelled = time - milliseconds;
			long distanceTravelled = speed * timeTravelled;
			if(distanceTravelled > distance) {
				winningHoldTimes.add(milliseconds);
			}
			milliseconds++;
		}
		
		return winningHoldTimes;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}
	
}