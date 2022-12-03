package year2021.day6;

public class LanternfishGroup {

	private long timer;
	private long count;

	public LanternfishGroup(long timer, long count) {
		this.timer = timer;
		this.count = count;
	}
	
	public long decrement() {
		long spawnedCount = 0;
		timer--;
		if(timer < 0L) {
			timer = 6L;
			spawnedCount = count;
		}
		return spawnedCount;
	}

	public void incrementCount(long increment) {
		count += increment;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
