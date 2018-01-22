package org.usfirst.frc.team2175.simulator.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.usfirst.frc.team2175.simulator.Game;

public class PowerUpSimulator extends JFrame {

	public PowerUpSimulator() {

		initUI();
	}

	private void initUI() {

		
		getContentPane().setLayout(new BorderLayout());
		
		add(new Board());
		
		VaultPanel redVault = new VaultPanel(Game.RED);
		VaultPanel blueVault = new VaultPanel(Game.BLUE);
		
		ScorePanel scorePanel = new ScorePanel();
		ButtonPanel buttonPanel = new ButtonPanel();
		
		add(redVault,BorderLayout.WEST);
		add(blueVault,BorderLayout.EAST);
		
		add(buttonPanel,BorderLayout.SOUTH);
		add(scorePanel,BorderLayout.NORTH);
		

		setSize(1400, 800);
		setResizable(false);

		setTitle("PowerUP");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				PowerUpSimulator ex = new PowerUpSimulator();
				ex.setVisible(true);
			}
		});
	}
}
