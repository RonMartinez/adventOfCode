package year2022.day16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Path {
	
	public static final Comparator<Path> SIZE_COMPARATOR = Comparator.comparing(Path::getSize);

	private List<Valve> valves;
	
	public Path copyPath() {
		Path copy = new Path();
		getValves().forEach(copy::addValve);
		return copy;
	}
	
	public int getSize() {
		return getValves().size();
	}
	
	public void addValve(Valve valve) {
		getValves().add(valve);
	}

	
	public List<Valve> getValves() {
		if(valves == null) {
			valves = new ArrayList<>();
		}
		return valves;
	}

	public void setValves(List<Valve> valves) {
		this.valves = valves;
	}

}
