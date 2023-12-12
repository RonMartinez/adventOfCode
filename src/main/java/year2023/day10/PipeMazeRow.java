package year2023.day10;

import java.util.List;

public class PipeMazeRow {

	private List<PipeMazeConnector> pipeMazeColumns;
	
	public PipeMazeRow(List<PipeMazeConnector> pipeMazeColumns) {
		this.pipeMazeColumns = pipeMazeColumns;
	}
	
	public String outputPipeMazeRow() {
		StringBuilder sb = new StringBuilder();
		for(PipeMazeConnector pipeMazeColumn : pipeMazeColumns) {
			sb.append(pipeMazeColumn.getCharacter());
		}
		return sb.toString();
	}

	public PipeMazeConnector getPipeMazeConnector(int x) {
		PipeMazeConnector pipeMazeConnector = null;
		
		if(x >= 0
				&& x < pipeMazeColumns.size()
				) {
			pipeMazeConnector = pipeMazeColumns.get(x);
		}
		
		return pipeMazeConnector;
	}

	public Long getXCoordinate(String character) {
		Long xCoordinate = null;
		for(int i = 0; i < pipeMazeColumns.size(); i++) {
			PipeMazeConnector column = pipeMazeColumns.get(i);
			if(column.getCharacter().equals(character)) {
				xCoordinate = (long) i;
				break;
			}
		}
		return xCoordinate;
	}

	public List<PipeMazeConnector> getPipeMazeColumns() {
		return pipeMazeColumns;
	}

	public void setPipeMazeColumns(List<PipeMazeConnector> pipeMazeColumns) {
		this.pipeMazeColumns = pipeMazeColumns;
	}

}