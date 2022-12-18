package year2022.day16;

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
	private Long currentRate;
	private Set<Valve> valves;
	private Long totalPressureReleased;
	
	public ValveSystem() {
	}
	

	public ValveSystem(Long totalMinutes, Long currentMinute, Valve currentValve, Long currentRate, Set<Valve> valves,
			Long totalPressureReleased) {
		this.totalMinutes = totalMinutes;
		this.currentMinute = currentMinute;
		this.currentValve = currentValve;
		this.currentRate = currentRate;
		this.valves = valves;
		this.totalPressureReleased = totalPressureReleased;
	}

	@Override
	public int hashCode() {
		// you pick a hard-coded, randomly chosen, non-zero, odd number
		// ideally different for each class
		return new HashCodeBuilder(17, 37)
				.append(totalMinutes)
				.append(currentMinute)
				.append(currentValve)
				.append(currentRate)
				.append(valves)
				.append(totalPressureReleased)
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
				.append(currentRate, rhs.currentRate)
				.append(valves, rhs.valves)
				.append(totalPressureReleased, rhs.totalPressureReleased)
				.isEquals();
	}

	public ValveSystem copyValveSystem() {
		ValveSystem copy = new ValveSystem();
		copy.setTotalMinutes(getTotalMinutes());
		copy.setCurrentMinute(getCurrentMinute());
		copy.setCurrentValve(getCurrentValve());
		copy.setCurrentRate(getCurrentRate());
		copy.setValves(new HashSet<>(getValves()));
		copy.setTotalPressureReleased(getTotalPressureReleased());
		return copy;
	}
	
	public void addValve(Valve valve) {
		getValves().add(valve);
	}

	public void openValve(Valve valve) {
		setCurrentMinute(getCurrentMinute()+1);
		setTotalPressureReleased(getTotalPressureReleased() + getCurrentRate());
		setCurrentRate(getCurrentRate() + valve.getRate());
		addValve(valve);
	}
	
	public ValveSystem travelToValve(ValveLink valveLink) {
		ValveSystem newValveSystem = copyValveSystem();
		for(int i = 1; i < valveLink.getPath().getSize(); i++) {
			newValveSystem.setCurrentMinute(newValveSystem.getCurrentMinute() + 1);
			newValveSystem.setTotalPressureReleased(newValveSystem.getTotalPressureReleased() + newValveSystem.getCurrentRate());
			newValveSystem.setCurrentValve(valveLink.getPath().getValves().get(i));
			if(newValveSystem.getCurrentMinute().equals(newValveSystem.getTotalMinutes())) {
				//out of time
				break;
			}
		}

		return newValveSystem;
	}
	
	public void waitUntilEnd() {
		while( ! getCurrentMinute().equals(getTotalMinutes())) {
			setCurrentMinute(getCurrentMinute()+1);
			setTotalPressureReleased(getTotalPressureReleased() + getCurrentRate());			
		}
	}

	public Long getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(Long totalMinutes) {
		this.totalMinutes = totalMinutes;
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

	public Long getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(Long currentRate) {
		this.currentRate = currentRate;
	}

}
