package org.usfirst.frc.team2175.simulator;

import java.util.Random;

public class Team
{

	
	public static double RED_X = 40;
	public static double BLUE_X = 870;
	
	public static double RED_Y_0 = 80;
	public static double RED_Y_1 = 250;
	public static double RED_Y_2 = 380;
	
	public static double BLUE_Y_0 = 85;
	public static double BLUE_Y_1 = 160;
	public static double BLUE_Y_2 = 380;
	
	
	Random r = new Random();

	Vault vault = new Vault();

	double fieldWidth = 100;
	double fieldLength = 200;

	// definition of board x = width, y = length

	// blue top left is 0,0
	// red bottom left is width,0

	// blue top right is 0,length
	// right bottom right = width,length

	Robot[] robots = new Robot[3];

	int score = 0;

	int color;

	public Team(int col)
	{
		color = col;

		initialize();

	}

	public void initialize()
	{
		if (color == Game.RED)
		{

			robots[0] = new Robot(RED_X,RED_Y_0, this, 0);
			robots[1] = new Robot(RED_X,RED_Y_1,this, 1);
			robots[2] = new Robot(RED_X,RED_Y_2, this, 2);
			
			robots[0].setSpeed(0.3);
			robots[1].setSpeed(0.3);
			robots[2].setSpeed(0.3);
		}
		else
		{
			robots[0] = new Robot(BLUE_X, BLUE_Y_0, this, 0);
			robots[1] = new Robot(BLUE_X, BLUE_Y_1, this, 1);
			robots[2] = new Robot(BLUE_X, BLUE_Y_2, this, 2);
			robots[0].setSpeed(0.3);
			robots[1].setSpeed(0.3);
			robots[2].setSpeed(0.3);

		}
		score = 0;
		vault.initialize();
	}

	public int getVaultScore()
	{
		return vault.score;

	}
	
	public int getForceCount()
	{
		return vault.getForceCount();
	}
	public int getBoostCount()
	{
		return vault.getBoostCount();
	}
	public int getLevitateCount()
	{
		return vault.getLevitateCount();
	}

	public void incrementScore(int amount)
	{
		score += amount;
	}

	public void move(double gameTime, double incr)
	{
		for (int i = 0; i < robots.length; i++)
		{
			
			Robot robot = robots[i];
			robot.move(gameTime,incr);
		}
		
	}

	public Robot[] getRobots()
	{
		return robots;
	}
}
