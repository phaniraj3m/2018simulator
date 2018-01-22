package org.usfirst.frc.team2175.simulator;

public class Game extends Thread
{
	
	/*
	 * Features to be added
	 * 
	 * Support defining capabilities for each robot, and choosing a strategy for how each robot should play. 
	 * Support for putting a cube into the Vault
	 * Support triggering of the Force, Boost, Levitate buttons
	 * Support Climbing
	 * Support Defence(and define what this means - trying to remove ownership of other teams switch, holding ownership of our switch/scale, etc)
	 * Support the speed of the robot and distance using the Dijsktra algorithms
	 * 
	 * Add a user interface
	 * Use this as a simulation tool to determine strategies, and what parameters affect scoring the most
	 * Determining optimal strategies for what to do with a cube in the vault.
	 * Allow entering data from scouting team to determine strategies in a match
	 * 
	 * 
	 */
	public static final int BLUE = 1, RED  = 2;
	private String[] COLORS = new String [] {"None","Blue","Red"};
	
	Team blueTeam, redTeam;
	Robot[] robotsBlue, robotsRed;

	Scale scale;

	Switch blueSwitch, redSwitch;
	


	double time = 0;
	
	
	public Game()
	{
		initialize();
		
	}

	// this is the method that actually runs when Thread.start() is called
	
	public void run()
	{


		
		// this is the tick every second
		do
		{
			time++;
			onTick(time);
			
		}
		while (time <150);
		completeGame();

	}

	public void onTick(double gameTime)
	{
		time = gameTime;
		
		redTeam.move(gameTime);
		blueTeam.move(gameTime);
		updateScores();
		System.out.print(time+ "\t" + blueTeam.score + "\t" + redTeam.score + "\t");
		System.out.print(COLORS[scale.owner] + "\t" + COLORS[blueSwitch.owner] + "\t" + COLORS[redSwitch.owner]);
		System.out.println("\t" + blueTeam.getVaultScore() + "\t" +  redTeam.getVaultScore());
	}
	
	private void completeGame()
	{

		if ( blueTeam.score > redTeam.score)
		{
			System.out.println("Blue wins by a score of " + blueTeam.score + " to " +  redTeam.score);
		}
		else 
		{
			System.out.println("Red wins by a score of " + redTeam.score + " to " +  blueTeam.score);
		}
		
	
//		System.out.println("Blue score = " + blueTeam.score);
//		System.out.println("Red score = " + redTeam.score);
//		System.exit(0);
	}

	private void initialize()
	{

		blueTeam = new Team(BLUE);
		redTeam = new Team(RED);
		
		scale = new Scale();
		
		blueSwitch = new Switch(BLUE);
		redSwitch = new Switch(RED);

		System.out.println("Time\tBlue\tRed\tScale\tBlueSwitch\tRedSwitch\tBlueVault\tRedVault");

	}
	
	private void updateScores()
	{
		
		updateScore(scale);  
		updateScore(blueSwitch);
		updateScore(redSwitch);
			
	}
	private void updateScore(Balance balance)
	{
		// update a device
		
		int previousOwner = balance.getOwner();
		
		int switchColor = balance.color;
		
		balance.update(); // this may change the owner of the balance
		
		int scoreIncrement = 1;
		if ( time <= 15)
		{
			scoreIncrement = 2;
		}
		
		
		
		
		
		if ( balance.getOwner() == BLUE && switchColor != RED )
		{
			if ( previousOwner != BLUE)
			{
				blueTeam.incrementScore(scoreIncrement);				
			}
			blueTeam.incrementScore(scoreIncrement);
		}
		else if ( balance.owner == RED && switchColor != BLUE)
		{
			if ( previousOwner != RED)
			{
				redTeam.incrementScore(scoreIncrement);				
			}
			redTeam.incrementScore(scoreIncrement);
		}
	}
	
	public int [] getScores()
	{
		int [] scores = new int[2];
		scores[0] = blueTeam.score;
		scores[1] = redTeam.score;
		return scores;
	}
}
