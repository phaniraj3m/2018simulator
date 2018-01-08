package org.usfirst.frc.team2175.simulator;

public class Robot
{
	int number;

	Team team;

	int color;
	double x_position, y_position;
	double speed;

	public Robot(double x, double y, Team team, int number)
	{
		x_position = x;
		y_position = y;
		speed = 0;
		this.color = team.color;
		this.team = team;

		auton();
	}

	private void auton()
	{
		team.score += 5 ; // assume all teams pass the line
	}

	
	// define possible moves
	
	// returns the amount of time to fetch the cube
	private double fetchCube()
	{
		return 0.0;
		
	}
	
	// returns the amount of time to drive that distance
	private double drive(double distance)
	{
		return 0.0;
		
	}

	// returns the amount of time to put the cube into the exchange - does not include driving time

	private double depositCubeToExchange()
	{
		return 0.0;
		
	}
	
	// returns the amount of time to put the cube onto the Scale - does not include driving time

	private double placeCubeOnScale()
	{
		return 0.0;
		
	}
	
	// returns the amount of time to put the cube onto the switch - does not include driving time

	private double placeCubeOnSwitch()
	{
		return 0.0;
	}
	
	
	private double playDefence(double duration)
	{
		return duration;
		
	}
}
