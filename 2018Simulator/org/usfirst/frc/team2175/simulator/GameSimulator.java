package org.usfirst.frc.team2175.simulator;

public class GameSimulator
{
	
	
	int time = 0 ;
	
	public static final int BLUE = 1, RED  = 2;

	Team blueTeam, redTeam;
	Robot[] robotsBlue, robotsRed;

	Scale scale;

	Switch blueSwitch, redSwitch;

	public static void main(String[] args)
	{
		new GameSimulator();
	}

	public GameSimulator()
	{
		// lets simulate some strategy

		initialize();
		
		Game game = new Game(blueTeam,redTeam); 
		game.start();
		
		
	}

	private void initialize()
	{

		blueTeam = new Team(BLUE);
		redTeam = new Team(RED);
		
		scale = new Scale();
		
		blueSwitch = new Switch(BLUE);
		redSwitch = new Switch(RED);
	}
}
