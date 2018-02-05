package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
{

	PowerUpSimulator simulator;
	
	public ButtonPanel(PowerUpSimulator powerUpSimulator)
	{
		setBackground(new Color(110, 210, 255));
		simulator = powerUpSimulator;
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		add(Box.createHorizontalStrut(130));
		JButton configureRed = new JButton("Configure");
		add(configureRed);
		add(Box.createHorizontalStrut(350));
		
		JButton start = new JButton("Start");
		add(start);
		add(Box.createHorizontalStrut(350));
		
		JButton configureBlue = new JButton("Configure");
		add(configureBlue);
		add(Box.createHorizontalStrut(130));
		
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				simulator.start = true;
				
			}
		});
		
		
	}
}
