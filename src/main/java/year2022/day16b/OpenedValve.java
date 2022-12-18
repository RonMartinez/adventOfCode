package year2022.day16b;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class OpenedValve {

	private Long minutedOpened;
	private Valve valve;
	private ValveSystem valveSystem;
	
	public OpenedValve(Long minutedOpened, Valve valve) {
		this.minutedOpened = minutedOpened;
		this.valve = valve;
	}
	
	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37)
				.append(minutedOpened)
				.append(valve)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		OpenedValve rhs = (OpenedValve) obj;
		return new EqualsBuilder()
				.append(minutedOpened, rhs.minutedOpened)
				.append(valve, rhs.valve)
				.isEquals();
	}
	
	public Long getPressureReleased( ) {
		return (getValveSystem().getTotalMinutes() - getMinutedOpened()) * getValve().getRate();
	}

	public Long getMinutedOpened() {
		return minutedOpened;
	}

	public void setMinutedOpened(Long minutedOpened) {
		this.minutedOpened = minutedOpened;
	}

	public Valve getValve() {
		return valve;
	}

	public void setValve(Valve valve) {
		this.valve = valve;
	}

	public ValveSystem getValveSystem() {
		return valveSystem;
	}

	public void setValveSystem(ValveSystem valveSystem) {
		this.valveSystem = valveSystem;
	}

}
