package org.usfirst.frc.team2175.simulator;

import java.util.Random;

import org.usfirst.frc.team2175.simulator.distance.Movements;
import org.usfirst.frc.team2175.simulator.distance.Vertex;

public class Robot
{

	Random r = new Random();

	Vault vault;

	int number;

	Team team;

	int color;
	double x_position, y_position;
	double speed; // in inches / second

	public Robot(double x, double y, Team team, int number)
	{
		x_position = x;
		y_position = y;
		speed = 0;
		this.number = number;
		this.color = team.color;
		this.team = team;
		vault = team.vault;

		auton();
	}

	private void auton()
	{
		team.score += 5; // assume all teams pass the line
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

	// returns the amount of time to put the cube into the exchange - does not
	// include driving time

	private double depositCubeToExchange()
	{
		return 0.0;

	}

	// returns the amount of time to put the cube onto the Scale - does not
	// include driving time

	private double placeCubeOnScale()
	{
		return 0.0;

	}

	// returns the amount of time to put the cube onto the switch - does not
	// include driving time

	private double placeCubeOnSwitch()
	{
		return 0.0;
	}

	private double playDefence(double duration)
	{
		return duration;

	}

	public void move(double gameTime)
	{

		if (gameTime < 16)
		{
			if (color == Game.BLUE)
				x_position -= (1.1 + number);
			else
				x_position += (2.2 - number);

		}
		else
		{
			// add to vault once in a while
			double[] target = new double[2];

			if (color == Game.RED)
			{
				// try moving robot red 0

				// target = 600,40

				switch (number) {
				case 0: // portal 1
					target[0] = 600;
					target[1] = 40;
					break;
				case 1: // red scale
					target[0] = 260;
					target[1] = 120;
					break;
				case 2: // portal 2
					target[0] = 600;
					target[1] = 320;
					break;
				}
			}
			
			if (color == Game.BLUE)
			{
				// try moving robot blue

				switch (number) {
				case 0: // portal 1
					target[0] = 20;
					target[1] = 310;
					break;
				case 1: // blue scale
					target[0] = 330;
					target[1] = 120;
					break;
				case 2: // portal 2
					target[0] = 20;
					target[1] = 40;
					break;
				}
			}

			Movements m = Movements.getInstance();
			double[] trans = convertPixelsToInches(x_position, y_position);
			double path[][] = m.getPath(trans[0], trans[1], target[0], target[1], color);
			// System.out.println("initial pixel " + x_position + ":" +
			// y_position);
			// System.out.println("initial inch " + trans[0] + ":" + trans[1]);

			int div = 1000;

			if (path != null)
			{
				for (int i = 0; i < 100; i++)
				{
					int off = (path.length / 12 + i + path.length / div) % path.length;
					double x_targ = path[off][0];
					double y_targ = path[off][1];
					Vertex[] v = m.findZone(m.getRedNodes(), x_targ, y_targ, 30, 30);
					if (v[0] != null)
					{
						trans = convertInchesToPixels(x_targ, y_targ);

						x_position = speed *(trans[0] - x_position) + x_position;
						y_position = speed * (trans[1] - y_position) + y_position;
						// System.out.println(
						// "next inch " + x_targ + ":" + y_targ);
						// System.out.println(gameTime +
						// " next pixel " + x_position + ":" + y_position);
						break;
					}
				}

			}

		}
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

	public double getX_position()
	{
		return x_position;
	}

	public double getY_position()
	{
		return y_position;
	}

	public double[] convertPixelsToInches(double x, double y)
	{
		return new double[] { 648 * (x - 180) / 900, 324 - (y - 40) * 324 / 440 };
	}

	public double[] convertInchesToPixels(double x, double y)
	{
		return new double[] { 900 * x / 648.0 + 180, 40 - 440 * (y - 324) / 324.0 };
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
}
