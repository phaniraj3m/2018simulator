package org.usfirst.frc.team2175.simulator.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import org.usfirst.frc.team2175.simulator.Game;
import org.usfirst.frc.team2175.simulator.Robot;

public class PowerUpSimulator extends JFrame implements ActionListener
{
	boolean start = false;

	Board board = new Board();
	Game game= new Game();
	ScorePanel scorePanel;
	VaultPanel redVault, blueVault;

	double time = 0.0;

	int increment = 10; // 10 milliseconds = tick between clock updates of the screen

	double multiplier = 10; // so the game time increments in steps of  multiplier * increment

	public PowerUpSimulator()
	{

		initUI();

		// game.start();

	}

	private void initUI()
	{

		getContentPane().setLayout(new BorderLayout());

		add(board);

		redVault = new VaultPanel(Game.RED,game.getRedTeam() );
		blueVault = new VaultPanel(Game.BLUE, game.getBlueTeam());

		scorePanel = new ScorePanel();
		ButtonPanel buttonPanel = new ButtonPanel(this);

		add(redVault, BorderLayout.WEST);
		add(blueVault, BorderLayout.EAST);

		add(buttonPanel, BorderLayout.SOUTH);
		add(scorePanel, BorderLayout.NORTH);

		setSize(1400, 628);
		setResizable(false);

		setTitle("PowerUP");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Timer timer = new Timer(increment, this);
		timer.setInitialDelay(5000);
		timer.start();
	}

	public static void main(String[] args)
	{

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run()
			{

				PowerUpSimulator ex = new PowerUpSimulator();
				ex.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// System.out.println(time);
		if (game == null)
		{
			game = new Game();
		}
		board.actionPerformed(arg0);

		if (!start)
			return;

		if (time < 150)
		{
			time += (multiplier * 1.0 * increment) / 1000.0;
			game.onTick(time);

			update();
			

			board.repaint();
		}
		else
		{
			String result = game.completeGame();
			start = false;
			JOptionPane.showMessageDialog(this,result,"Result",JOptionPane.PLAIN_MESSAGE);
			reset();
		}
	}

	public void update()
	{
		scorePanel.updateTime(time);

		scorePanel.updateScores(game.getScores());
		redVault.updateScores(game.getRedTeam().getForceCount(), game.getRedTeam().getBoostCount(),
				game.getRedTeam().getLevitateCount());
		blueVault.updateScores(game.getBlueTeam().getForceCount(), game.getBlueTeam().getBoostCount(),
				game.getBlueTeam().getLevitateCount());

		Robot[] blueRobots = game.getBlueTeam().getRobots();
		Robot[] redRobots = game.getRedTeam().getRobots();

		board.getRedCraft1().setX((int) redRobots[0].getX_position());
		board.getRedCraft1().setY((int) redRobots[0].getY_position());
		board.getRedCraft2().setX((int) redRobots[1].getX_position());
		board.getRedCraft2().setY((int) redRobots[1].getY_position());
		board.getRedCraft3().setX((int) redRobots[2].getX_position());
		board.getRedCraft3().setY((int) redRobots[2].getY_position());

		board.getBlueCraft1().setX((int) blueRobots[0].getX_position());
		board.getBlueCraft1().setY((int) blueRobots[0].getY_position());
		board.getBlueCraft2().setX((int) blueRobots[1].getX_position());
		board.getBlueCraft2().setY((int) blueRobots[1].getY_position());
		board.getBlueCraft3().setX((int) blueRobots[2].getX_position());
		board.getBlueCraft3().setY((int) blueRobots[2].getY_position());
	}
	
	private void reset()
	{
		time = 0;
		
		game.getRedTeam().initialize();
		game.getBlueTeam().initialize();
		update();
		board.repaint();
	}
}
