package org.usfirst.frc.team2175.simulator.ui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Arcade extends JFrame{

	public static void main(String[] args) {
		new Arcade();

	}
	
	public Arcade() {

		setSize(1600, 700);
		setTitle("FRC PowerUp");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ArcadePanel panel = new ArcadePanel();
	
		add(panel);
		setVisible(true);
	}

}
