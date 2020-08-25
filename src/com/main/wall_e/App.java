package com.main.wall_e;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class App extends JFrame {

	private static Character character;
	private static JFrame mainFrame = new JFrame("Wall-E");
	private static JPanel panel = new JPanel();
	
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int SQUARE_SIZE = 25;
	private static final int CHARACTER_WIDTH = 24;
	private static final int CHARACTER_HEIGHT = 24;
	private static final int SQUARE_CONTENT_PADDING = 1;
	
	public App (){
	    getContentPane().add(panel);
	    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public void paint(Graphics g) {
		panel.paintComponents(g);
	    Graphics2D g2 = (Graphics2D) g;
	    drawGrid(g2);
	    drawCharacter(g2);
	    drawTrashBalls(g2);
	}
	
	public static void main(String[] args) {
		App app = new App();
		app.setVisible(true);
		character = new Character("Wall-E", 0, 0);
		String commands = "";
		while(!commands.equals("#")) {
			Scanner input = new Scanner(System.in);
			System.out.println("Type directions for Wall-E to go");
			
			commands = input.nextLine();
			character.readCommands(commands);	
			panel.repaint();	
			app.repaint();
		}
	}
	
	public void drawGrid(Graphics2D g) {
		for(int i = 0; i < WINDOW_WIDTH; i=i+SQUARE_SIZE) {
	    	Line2D line = new Line2D.Float(i, 0, i, WINDOW_HEIGHT);
	    	g.draw(line);
	    }
	    
	    for(int i = 0; i < WINDOW_HEIGHT; i=i+SQUARE_SIZE) {
	    	Line2D line = new Line2D.Float(0,i, WINDOW_WIDTH, i);
	    	g.draw(line);
	    }
	}
	
	public void drawCharacter(Graphics2D g) {
		 int x = (int)character.getPosition().getX();
		 int y = (int)character.getPosition().getY();
		    
		 //g.fillRect( SQUARE_CONTENT_PADDING + (WINDOW_WIDTH/2) + x * SQUARE_SIZE , SQUARE_CONTENT_PADDING + (WINDOW_HEIGHT/2) + y* SQUARE_SIZE, CHARACTER_WIDTH , CHARACTER_HEIGHT);
		 g.fillRect(SQUARE_CONTENT_PADDING + (WINDOW_WIDTH/2), SQUARE_CONTENT_PADDING + (WINDOW_HEIGHT/2), CHARACTER_WIDTH , CHARACTER_HEIGHT);
		 
	}
	
	public void drawTrashBalls(Graphics2D g) {
		for(int i = 0; i < WINDOW_WIDTH; i=i+SQUARE_SIZE) {
			for(int j = 0; j < WINDOW_HEIGHT; j=j+SQUARE_SIZE) {
				int x_point = (i - WINDOW_WIDTH/2) /SQUARE_SIZE + (int)character.getPosition().getX(); 
				int y_point = (j - WINDOW_HEIGHT/2) /SQUARE_SIZE + (int)character.getPosition().getY(); 
				Point2D point = new Point2D.Double((double) x_point, (double) y_point);
				if(!character.getAllPreviousPositions().contains(point)) {
					g.setColor(Color.RED);
					g.fillRect(i + SQUARE_CONTENT_PADDING, j + SQUARE_CONTENT_PADDING, CHARACTER_WIDTH, CHARACTER_HEIGHT);
				}else if(character.getAllPreviousPositions().contains(point) && !point.equals(character.getPosition())) {
					g.setColor(Color.WHITE);
					g.fillRect(i + SQUARE_CONTENT_PADDING, j + SQUARE_CONTENT_PADDING, CHARACTER_WIDTH, CHARACTER_HEIGHT);
					
				}
			}
		}
	}
	
	
}
