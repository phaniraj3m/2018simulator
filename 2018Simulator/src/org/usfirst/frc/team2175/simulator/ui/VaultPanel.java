package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.usfirst.frc.team2175.simulator.Game;
import org.usfirst.frc.team2175.simulator.Team;

public class VaultPanel extends JPanel
{
	
	Team team;
	private final Font scoreFont = new Font("Helvetica", Font.BOLD, 24);

	JLabel force = new JLabel();
	JLabel boost = new JLabel();
	JLabel levitate = new JLabel();
	
	JSlider speed1 = new JSlider(JSlider.HORIZONTAL,-10,10,0);
	JSlider speed2 = new JSlider(JSlider.HORIZONTAL,-10,10,0);
	JSlider speed3 = new JSlider(JSlider.HORIZONTAL,-10,10,0);

	public VaultPanel(int color, Team team)
	{
		this.team = team;
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

		add(Box.createVerticalStrut(100));
		add(speed1);
		add(speed2);
		add(speed3);
		updateScores(0, 0, 0);
		
		speed1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e)
			{
				double value = 1.0 + (1.0 * speed1.getValue())/10 ;
				team.getRobots()[0].setSpeed(value);
				
			}
		});
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
