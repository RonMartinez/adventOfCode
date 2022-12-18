package year2022.day16;

public class ValveLink {

	private Path path;
	private Valve valve;
	
	public ValveLink(Path path, Valve valve) {
		this.path = path;
		this.valve = valve;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public Valve getValve() {
		return valve;
	}

	public void setValve(Valve valve) {
		this.valve = valve;
	}

}
