package year2020.day24;

import java.util.HashSet;
import java.util.Set;

public class Tile {
	
	public static Set<Tile> TILES = new HashSet<>();
	
	private Colour colour = Colour.WHITE;
	private Tile east;
	private Tile southEast;
	private Tile southWest;
	private Tile west;
	private Tile northWest;
	private Tile northEast;
	
	public Tile() {
		TILES.add(this); //cheater Ron
	}

	public Tile getOrCreateEast() {
		Tile tile = getEast();
		if(tile == null) {
			tile = new Tile();
			linkEast(tile);
		}
		return tile;
	}

	public Tile getOrCreateSouthEast() {
		Tile tile = getSouthEast();
		if(tile == null) {
			tile = new Tile();
			linkSouthEast(tile);
		}
		return tile;
	}

	public Tile getOrCreateSouthWest() {
		Tile tile = getSouthWest();
		if(tile == null) {
			tile = new Tile();
			linkSouthWest(tile);
		}
		return tile;
	}

	public Tile getOrCreateWest() {
		Tile tile = getWest();
		if(tile == null) {
			tile = new Tile();
			linkWest(tile);
		}
		return tile;
	}

	public Tile getOrCreateNorthWest() {
		Tile tile = getNorthWest();
		if(tile == null) {
			tile = new Tile();
			linkNorthWest(tile);
		}
		return tile;
	}

	public Tile getOrCreateNorthEast() {
		Tile tile = getNorthEast();
		if(tile == null) {
			tile = new Tile();
			linkNorthEast(tile);
		}
		return tile;
	}

	public void linkEast(Tile tile) {
		setEast(tile);
		tile.setWest(this);
	}

	public void linkSouthEast(Tile tile) {
		setSouthEast(tile);
		tile.setNorthWest(this);
	}

	public void linkSouthWest(Tile tile) {
		setSouthWest(tile);
		tile.setNorthEast(this);
	}

	public void linkWest(Tile tile) {
		setWest(tile);
		tile.setEast(this);
	}

	public void linkNorthWest(Tile tile) {
		setNorthWest(tile);
		tile.setSouthEast(this);
	}

	public void linkNorthEast(Tile tile) {
		setNorthEast(tile);
		tile.setSouthWest(this);
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public Tile getEast() {
		return east;
	}

	public void setEast(Tile east) {
		this.east = east;
	}

	public Tile getSouthEast() {
		return southEast;
	}

	public void setSouthEast(Tile southEast) {
		this.southEast = southEast;
	}

	public Tile getSouthWest() {
		return southWest;
	}

	public void setSouthWest(Tile southWest) {
		this.southWest = southWest;
	}

	public Tile getWest() {
		return west;
	}

	public void setWest(Tile west) {
		this.west = west;
	}

	public Tile getNorthWest() {
		return northWest;
	}

	public void setNorthWest(Tile northWest) {
		this.northWest = northWest;
	}

	public Tile getNorthEast() {
		return northEast;
	}

	public void setNorthEast(Tile northEast) {
		this.northEast = northEast;
	}


}
