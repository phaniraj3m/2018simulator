package org.usfirst.frc.team2175.simulator;

public class Game extends Thread
{
	
	
	public static final int BLUE = 1, RED  = 2;
	private String[] COLORS = new String [] {"None","Blue","Red"};
	
	Team blueTeam, redTeam;
	Robot[] robotsBlue, robotsRed;

	Scale scale;

	Switch blueSwitch, redSwitch;

	int time = 0;
	
	
	public Game()
	{
		initialize();
		
	}
	public Game(Team blue, Team red)
	{

		this.blueTeam = blue;
		this.redTeam = red;

	}

	public void run()
	{

		System.out.println("Time\tBlue\tRed\tScale\tBlueSwitch\tRedSwitch");

		
		// this is the tick every second
		do
		{
			time++;
			redTeam.move();
			blueTeam.move();
			updateScores();
			System.out.print(time+ "\t" + blueTeam.score + "\t" + redTeam.score + "\t");
			System.out.println(COLORS[scale.owner] + "\t" + COLORS[blueSwitch.owner] + "\t" + COLORS[redSwitch.owner]);
			
		}
		while (time <150);
		completeGame();

	}
	
	private void completeGame()
	{
		System.out.print(time+ "\t" + blueTeam.score + "\t" + redTeam.score + "\t");
		System.out.print(COLORS[scale.owner] + "\t" + COLORS[blueSwitch.owner] + "\t" + COLORS[redSwitch.owner]);
	
//		System.out.println("Blue score = " + blueTeam.score);
//		System.out.println("Red score = " + redTeam.score);
		System.exit(0);
	}

	private void initialize()
	{

		blueTeam = new Team(BLUE);
		redTeam = new Team(RED);
		
		scale = new Scale();
		
		blueSwitch = new Switch(BLUE);
		redSwitch = new Switch(RED);
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
}
