package year2022.day16newAttempt;

import java.util.HashSet;
import java.util.Set;

public class Valve {

	private String name;
	private Long rate;
	private Set<Valve> valves;
	private Set<ValveLink> valveLinks;
	
	public boolean isZeroRate() {
		return rate.equals(0L);
	}
	
	public void addValveLink(ValveLink valveLink) {
		getValveLinks().add(valveLink);
	}
	public void addValve(Valve valve) {
		getValves().add(valve);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getRate() {
		return rate;
	}

	public void setRate(Long rate) {
		this.rate = rate;
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

	public Set<ValveLink> getValveLinks() {
		if(valveLinks == null) {
			valveLinks = new HashSet<>();
		}
		return valveLinks;
	}

	public void setValveLinks(Set<ValveLink> valveLinks) {
		this.valveLinks = valveLinks;
	}

}
