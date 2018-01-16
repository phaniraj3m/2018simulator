package org.usfirst.frc.team2175.simulator;

import java.util.ArrayList;

public class Vault
{
	int score = 0;

	int incr = 5;

	String CUBE = "CUBE";

	// each arrayList represents the stack of 3 cubes

	private ArrayList<String> force = new ArrayList<String>(), boost = new ArrayList<String>(),
			levitate = new ArrayList<String>();

	private boolean forcePlayed = false, boostPlayed = false, levitatePlayed = false;

	public int addToLevitate()
	{
		if (levitate.size() < 3)
		{
			levitate.add(CUBE);
			score += incr;
		}
		else
		{
			return 0;
		}
		return levitate.size();
	}

	public int addToForce()
	{
		if (force.size() < 3)
		{
			force.add(CUBE);
			score += incr;
		}
		else
		{
			return 0;
		}
		return force.size();
	}

	public int addToBoost()
	{
		if (boost.size() < 3)
		{
			boost.add(CUBE);
			score += incr;
		}
		else
		{
			return 0;
		}
		return boost.size();
	}
}
