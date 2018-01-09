package org.usfirst.frc.team2175.simulator;

import java.util.Random;

public class Balance
{
	int owner = 0;
	
	int color;
	

	Random r = new Random();

	public int getOwner()
	{
		return owner;
	}

	public void setOwner(int owner)
	{
		this.owner = owner;
	}

	public void update()
	{
		// this should look at the cubes on the balance, and change owner based
		// on the cubes
		// instead, we are going to randomly change ownership for now

		int ownerChange = r.nextInt(100);

		// here are the rules for now
		// if change < 60, no change
		// if change between 60 and 80, owner = blue
		// if change > 80 owner = red

		if (ownerChange > 60 && ownerChange < 80)
		{
			owner = Game.BLUE;
		}
		else if (ownerChange > 80 && ownerChange < 100)
		{
			owner = Game.RED;
		}
		
		// don't allow switches to change ownership if wrong color
		
		if (color != 0) // this means its a switch
		{
			if (color != owner) owner = 0;
		}

	}

}
