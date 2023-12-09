package year2023.day8;

public class Element {

	private String name;
	private Element leftElement;
	private Element rightElement;
	
	public Element(String name) {
		this.name = name; 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Element getLeftElement() {
		return leftElement;
	}

	public void setLeftElement(Element leftElement) {
		this.leftElement = leftElement;
	}

	public Element getRightElement() {
		return rightElement;
	}

	public void setRightElement(Element rightElement) {
		this.rightElement = rightElement;
	}

}