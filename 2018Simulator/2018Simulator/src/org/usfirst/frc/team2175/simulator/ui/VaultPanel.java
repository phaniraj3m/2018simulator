package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.usfirst.frc.team2175.simulator.Game;

public class VaultPanel extends JPanel
{
	private final Font scoreFont = new Font("Helvetica", Font.BOLD, 24);

	JLabel force = new JLabel();
	JLabel boost = new JLabel();
	JLabel levitate = new JLabel();

	public VaultPanel(int color)
	{

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		force.setFont(scoreFont);
		boost.setFont(scoreFont);
		levitate.setFont(scoreFont);

		Color textColor = null;

		if (color == Game.BLUE)
		{
			textColor = Color.BLUE;
		}
		else
		{
			textColor = Color.RED;
		}
		force.setForeground(textColor);
		boost.setForeground(textColor);
		levitate.setForeground(textColor);

		add(Box.createVerticalStrut(100));
		add(force);
		add(boost);
		add(levitate);

		updateScores(0, 0, 0);
	}

	public void updateScores(int forceCount, int boostCount, int levitateCount)
	{
		force.setText("Force: " + forceCount);
		boost.setText("Boost: " + boostCount);
		levitate.setText("Levitate: " + levitateCount);

		force.repaint();
		boost.repaint();
		levitate.repaint();
	}

}
