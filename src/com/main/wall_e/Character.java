package com.main.wall_e;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Character {

	private String name;
	private Point2D position;
	private int points = 1;
	private Set<Point2D> previousPositions = new HashSet<>();
	
	public Character(String name, double x, double y) {
		this.name = name;
		this.position = new Point2D.Double(x, y);
		recordPosition();
	}
	
	public void move(char direction) {
		if(direction == 'N') {
			this.position.setLocation(this.position.getX(), this.position.getY() - 1);
		}else if(direction == 'S') {
			this.position.setLocation(this.position.getX(), this.position.getY() + 1);
		}else if(direction == 'E') {
			this.position.setLocation(this.position.getX() + 1, this.position.getY());
		}else if(direction == 'O') {
			this.position.setLocation(this.position.getX() - 1, this.position.getY());
		}
		if(!hasBeenAtCurrentPosition()) {
			addPoint();
		}
		recordPosition();
	}
	
	public void readCommands(String commands) {
		for(int i = 0; i < commands.length(); i++) {
			move(commands.charAt(i));
		}
		printPoints();
	}
	
	public void recordPosition() {
		this.previousPositions.add(new Point2D.Double(this.position.getX(), this.position.getY()));
	}
	
	public void printAllPreviousPositions() {
		for(Point2D p : this.previousPositions) {
			printPosition(p);
		}
	}
	
	public void printPosition(Point2D p) {
		System.out.print("(" + (int)p.getX() + "," + (int)p.getY() + ") ");
	}
	
	public boolean hasBeenAtCurrentPosition() {
		for(Point2D p : previousPositions) {
			if(p.equals(this.position)) {
				return true;
			}
		}
		return false;
	}
	
	public void addPoint() {
		this.points++;
	}
	
	public void printPoints() {
		System.out.println("Wall-E has " + this.points + " points!");
	}
	
	public Set<Point2D> getAllPreviousPositions(){
		return this.previousPositions;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
}
