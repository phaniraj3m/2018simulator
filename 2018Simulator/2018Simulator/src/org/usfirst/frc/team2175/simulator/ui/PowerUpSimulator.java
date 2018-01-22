package org.usfirst.frc.team2175.simulator.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import org.usfirst.frc.team2175.simulator.Game;

public class PowerUpSimulator extends JFrame implements ActionListener
{

	Game game;
	ScorePanel scorePanel;

	double time = 0.0;
	
	int increment = 100; // milliseconds

	public PowerUpSimulator()
	{

		initUI();

		// game.start();

	}

	private void initUI()
	{

		getContentPane().setLayout(new BorderLayout());

		add(new Board());

		VaultPanel redVault = new VaultPanel(Game.RED);
		VaultPanel blueVault = new VaultPanel(Game.BLUE);

		scorePanel = new ScorePanel();
		ButtonPanel buttonPanel = new ButtonPanel();

		add(redVault, BorderLayout.WEST);
		add(blueVault, BorderLayout.EAST);

		add(buttonPanel, BorderLayout.SOUTH);
		add(scorePanel, BorderLayout.NORTH);

		setSize(1400, 800);
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
		//System.out.println(time);

		if (game == null)
		{
			game = new Game();
		}
		
		if ( time < 150)
		{
			time+= (1.0*increment)/1000.0;
			game.onTick(time);

			scorePanel.updateTime(time);

			scorePanel.updateScores(game.getScores());			
		}
		
	}
}
