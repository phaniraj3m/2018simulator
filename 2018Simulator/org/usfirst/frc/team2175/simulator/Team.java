package org.usfirst.frc.team2175.simulator;

public class Team
{

	double fielddWidth = 100;
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

		if (col == GameSimulator.BLUE)
		{

			robots[0] = new Robot(0.2 * fielddWidth, 0, this,0);
			robots[1] = new Robot(0.5 * fielddWidth, 0, this,1);
			robots[2] = new Robot(0.8 * fielddWidth, 0, this,2);
		}
		else
		{
			robots[0] = new Robot(0.2 * fielddWidth, fieldLength, this,0);
			robots[1] = new Robot(0.5 * fielddWidth, fieldLength, this,1);
			robots[2] = new Robot(0.8 * fielddWidth, fieldLength, this,2);

		}

	}

	public void move()
	{

	}
}
