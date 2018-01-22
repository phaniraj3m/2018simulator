package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{

	public ButtonPanel()
	{
		setBackground(new Color(110, 210, 255));
		
		JButton start = new JButton("Start");
		add(start);
		
		
	}
}
