package year2022.day16b;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ValveSystem {
	
	public static final Comparator<ValveSystem> TOTAL_PRESSURE_RELEASED_COMPARATOR = Comparator.comparing(ValveSystem::getTotalPressureReleased);

	private Long totalMinutes;
	private Long currentMinute;
	private Valve currentValve;
	private Valve currentElephantValve;
	private Set<OpenedValve> openedValves;
	private Set<Valve> valves;
	private Long totalPressureReleased;
	private Long valveNonZeroRateCount;
	
	public ValveSystem() {
	}

	public ValveSystem(Long totalMinutes, Long totalPressureReleased, Long valveNonZeroRateCount) {
		this.totalMinutes = totalMinutes;
		this.totalPressureReleased = 0L;
		this.valveNonZeroRateCount = valveNonZeroRateCount;
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37)
				.append(totalMinutes)
				.append(currentMinute)
				.append(currentValve)
				.append(currentElephantValve)
				.append(openedValves)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) { return false; }
		if (obj == this) { return true; }
		if (obj.getClass() != getClass()) {
			return false;
		}
		ValveSystem rhs = (ValveSystem) obj;
		return new EqualsBuilder()
				.append(totalMinutes, rhs.totalMinutes)
				.append(currentMinute, rhs.currentMinute)
				.append(currentValve, rhs.currentValve)
				.append(currentElephantValve, rhs.currentElephantValve)
				.append(openedValves, rhs.openedValves)
				.isEquals();
	}

	public ValveSystem copyValveSystem() {
		ValveSystem copy = new ValveSystem();
		copy.setTotalMinutes(getTotalMinutes());
		copy.setCurrentValve(getCurrentValve());
		copy.setCurrentElephantValve(getCurrentElephantValve());
		copy.setOpenedValves(new HashSet<>(getOpenedValves()));
		copy.setTotalPressureReleased(getTotalPressureReleased());
		copy.setValveNonZeroRateCount(getValveNonZeroRateCount());
		return copy;
	}

	public OpenedValve getOpenedValveByValve(Valve valve) {
		return getOpenedValves().stream()
				.filter(ov -> ov.getValve().equals(valve))
				.findFirst().orElse(null);
	}

	public void addOpenedValve(Valve valve) {
		if(getOpenedValves().stream()
				.noneMatch(ov -> ov.getValve().equals(valve))
				) {
			OpenedValve openedValve = new OpenedValve(currentMinute, valve);
			openedValve.setValveSystem(this);
			getOpenedValves().add(openedValve);
			getValves().add(valve);
			setTotalPressureReleased(getTotalPressureReleased() + openedValve.getPressureReleased());
		}
	}

	public Long getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(Long totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public Set<OpenedValve> getOpenedValves() {
		if(openedValves == null) {
			openedValves = new HashSet<>();
		}
		return openedValves;
	}

	public void setOpenedValves(Set<OpenedValve> openedValves) {
		this.openedValves = openedValves;
	}

	public Long getTotalPressureReleased() {
		return totalPressureReleased;
	}

	public void setTotalPressureReleased(Long totalPressureReleased) {
		this.totalPressureReleased = totalPressureReleased;
	}

	public Valve getCurrentValve() {
		return currentValve;
	}

	public void setCurrentValve(Valve currentValve) {
		this.currentValve = currentValve;
	}

	public Long getCurrentMinute() {
		return currentMinute;
	}

	public void setCurrentMinute(Long currentMinute) {
		this.currentMinute = currentMinute;
	}

	public Set<Valve> getValves() {
		if(valves == null) {
			valves = new HashSet<>();
		}
		return valves;
	}

	public void setValves(Set<Valve> valves) {
		this.valves = valves;
	}

	public Long getValveNonZeroRateCount() {
		return valveNonZeroRateCount;
	}

	public void setValveNonZeroRateCount(Long valveNonZeroRateCount) {
		this.valveNonZeroRateCount = valveNonZeroRateCount;
	}

	public Valve getCurrentElephantValve() {
		return currentElephantValve;
	}

	public void setCurrentElephantValve(Valve currentElephantValve) {
		this.currentElephantValve = currentElephantValve;
	}

}
