package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;

import javax.swing.JPanel;

import org.usfirst.frc.team2175.simulator.Game;

public class VaultPanel extends JPanel
{

	public VaultPanel(int color)
	{
		if (color == Game.BLUE)
		{
			setBackground(new Color(0, 0, 255));
		}
		else
		{
			setBackground(new Color(255, 0, 0));
		}
	}
}
