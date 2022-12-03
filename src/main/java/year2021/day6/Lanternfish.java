package year2021.day6;

public class Lanternfish {

	private long timer;

	public Lanternfish(long timer) {
		this.timer = timer;
	}
	
	public Lanternfish decrement() {
		Lanternfish spawnedFish = null;
		timer--;
		if(timer < 0L) {
			timer = 6L;
			spawnedFish = new Lanternfish(8L);
		}
		return spawnedFish;
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

}
