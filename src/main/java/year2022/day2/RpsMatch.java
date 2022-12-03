package year2022.day2;

public class RpsMatch {

	private RpsShape rpsShape1;
	private RpsShape rpsShape2;
	private Long outcomeScore;
	private Long totalScore;
	
	public RpsMatch(RpsShape rpsShape1, RpsShape rpsShape2) {
		this.rpsShape1 = rpsShape1;
		this.rpsShape2 = rpsShape2;
		this.outcomeScore = calculateOutcomeScore();
		this.totalScore = calculateTotalScore();
	}

	private Long calculateOutcomeScore() {
		Long outcomeScore = 0L;
		if(rpsShape1.equals(rpsShape2)) {
			outcomeScore = 3L;
		} else if(RpsShape.ROCK.equals(rpsShape1)
				&& RpsShape.PAPER.equals(rpsShape2)
				) {
			outcomeScore = 6L;
		} else if(RpsShape.PAPER.equals(rpsShape1)
				&& RpsShape.SCISSORS.equals(rpsShape2)
				) {
			outcomeScore = 6L;
		} else if(RpsShape.SCISSORS.equals(rpsShape1)
				&& RpsShape.ROCK.equals(rpsShape2)
				) {
			outcomeScore = 6L;
		}
		
		return outcomeScore;
	}

	private Long calculateTotalScore() {
		return rpsShape2.getValue() + getOutcomeScore();
	}

	public RpsShape getRpsShape1() {
		return rpsShape1;
	}

	public void setRpsShape1(RpsShape rpsShape1) {
		this.rpsShape1 = rpsShape1;
	}

	public RpsShape getRpsShape2() {
		return rpsShape2;
	}

	public void setRpsShape2(RpsShape rpsShape2) {
		this.rpsShape2 = rpsShape2;
	}

	public Long getOutcomeScore() {
		return outcomeScore;
	}

	public void setOutcomeScore(Long outcomeScore) {
		this.outcomeScore = outcomeScore;
	}

	public Long getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Long totalScore) {
		this.totalScore = totalScore;
	}

}
