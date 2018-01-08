package org.usfirst.frc.team2175.simulator;

public class GameSimulator
{
		
	public static void main(String[] args)
	{
		new GameSimulator();
	}

	public GameSimulator()
	{
		// lets simulate a game, in its own thread
		
		Game game = new Game(); 
		game.start();
		
		
	}


}
