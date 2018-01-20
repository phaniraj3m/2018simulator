package org.usfirst.frc.team2175.simulator;

import java.util.Random;

public class Robot
{
	
	Random r = new Random();
	
	Vault vault ;
	
	
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
		vault = team.vault;
		
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
	
	public void move(int time)
	{

		if (time < 16)
		{
			// auton mode

		}
		else
		{
			// add to vault once in a while

			int vaultScore = r.nextInt(1000);

			// above 997 - levitate
			// 994-997 boost
			// 991-994 force

			if (vaultScore > 980)
			{
				int levitate = vault.addToLevitate();
				if (levitate == 3)
				{
					team.incrementScore(5);
					team.incrementScore(30); // free climb
					vault.setLevitatePlayed(true);
					

				}
				else if (levitate != 0)
				{
					team.incrementScore(5);
				}
			}
			else if (vaultScore > 970)
			{
				vault.addToBoost();
				team.incrementScore(5);
			}
			else if (vaultScore > 960)
			{
				vault.addToForce();
				team.incrementScore(5);
			}
		}

	}
}
