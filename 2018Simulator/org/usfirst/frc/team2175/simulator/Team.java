package org.usfirst.frc.team2175.simulator;

import java.util.Random;

public class Team
{

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

		if (col == Game.BLUE)
		{

			robots[0] = new Robot(0.2 * fieldWidth, 0, this, 0);
			robots[1] = new Robot(0.5 * fieldWidth, 0, this, 1);
			robots[2] = new Robot(0.8 * fieldWidth, 0, this, 2);
		}
		else
		{
			robots[0] = new Robot(0.2 * fieldWidth, fieldLength, this, 0);
			robots[1] = new Robot(0.5 * fieldWidth, fieldLength, this, 1);
			robots[2] = new Robot(0.8 * fieldWidth, fieldLength, this, 2);

		}

	}

	public int getVaultScore()
	{
		return vault.score;

	}

	public void incrementScore(int amount)
	{
		score += amount;
	}

	public void move(int time)
	{

		if (time < 16)
		{
			// auton mode

		}
		else
		{
			
			for (int i = 0; i < robots.length; i++)
			{
				
				Robot robot = robots[i];
				robot.move(time);
			}
		}
	}
}
