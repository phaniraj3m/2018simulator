package org.usfirst.frc.team2175.simulator.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class MovingSpriteEx extends JFrame {

	public MovingSpriteEx() {

		initUI();
	}

	private void initUI() {

		add(new Board(null));

		setSize(1200, 700);
		setResizable(false);

		setTitle("Moving sprite");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				MovingSpriteEx ex = new MovingSpriteEx();
				ex.setVisible(true);
			}
		});
	}
}
