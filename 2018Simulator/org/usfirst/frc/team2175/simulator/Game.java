package org.usfirst.frc.team2175.simulator;

public class Game extends Thread
{

	Team blue, red;
	int time = 0;

	public Game(Team blue, Team red)
	{

		this.blue = blue;
		this.red = red;

	}

	public void run()
	{

		// this is the tick every second
		do
		{
			time++;
			red.move();
			blue.move();
			System.out.println(time);
			
		}
		while (time <150);
		completeGame();

	}
	
	private void completeGame()
	{
		
		System.out.println("Blue score = " + blue.score);
		System.out.println("Red score = " + red.score);
		System.exit(0);
	}

}
