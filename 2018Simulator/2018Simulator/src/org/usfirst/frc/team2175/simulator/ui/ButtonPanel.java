package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{

	PowerUpSimulator simulator;
	
	public ButtonPanel(PowerUpSimulator powerUpSimulator)
	{
		setBackground(new Color(110, 210, 255));
		simulator = powerUpSimulator;
		
		JButton start = new JButton("Start");
		add(start);
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				simulator.start = true;
				
			}
		});
		
		
	}
}
