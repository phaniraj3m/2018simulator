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
		
		for (int i = 0; i < 100; i++) {

			
			try {
				Game game = new Game(); 
				game.run();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		
		
	}


}
