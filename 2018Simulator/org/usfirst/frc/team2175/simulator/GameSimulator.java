package org.usfirst.frc.team2175.simulator;

public class GameSimulator
{
	
	
	int time = 0 ;
	



	public static void main(String[] args)
	{
		new GameSimulator();
	}

	public GameSimulator()
	{
		// lets simulate some strategy

		
		Game game = new Game(); 
		game.start();
		
		
	}


}
