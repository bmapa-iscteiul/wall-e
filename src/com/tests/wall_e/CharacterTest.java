package com.tests.wall_e;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.awt.geom.Point2D;

import org.junit.jupiter.api.Test;

import com.main.wall_e.Character;

class CharacterTest {

	Character character = new Character("Test-e", 0, 0);
	
	@Test
	void testMove() {
		char moveDirection = 'S';
		character.move(moveDirection);
		// (0,0) + 'S' = (0,0) + (0,1) = (0,1)
		Point2D pointAfterMove = new Point2D.Double(0,1);
		assertEquals(character.getPosition(), pointAfterMove);
	}

	@Test
	void testReadCommands() {
		String commandLine = "S N P               S N OOOOOO";
		// (0,0) + (0,1) + (0,-1) + (0,1) + (0,-1) + 6*(-1, 0) = (-6, 0)
		character.readCommands(commandLine);
		Point2D pointAfterCommands = new Point2D.Double(-6, 0);
		assertEquals(character.getPosition(), pointAfterCommands);
	}

	@Test
	void testRecordPosition() {
		character.move('N');
		Point2D[] allPreviousPositions = new Point2D[2];
		allPreviousPositions[0] = new Point2D.Double(0,0);
		allPreviousPositions[1] = new Point2D.Double(0,-1);
		assertArrayEquals(character.getAllPreviousPositions().toArray(), allPreviousPositions);
	}

	@Test
	void testHasBeenAtCurrentPosition() {
		//current position is (0,0)
		assertTrue(character.hasBeenAtCurrentPosition());
	}

}
