package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel
{

	private final int SCREEN_SIZE = 200;

	private final Font blueFont = new Font("Helvetica", Font.BOLD, 14);

	private final Font redFont = new Font("Helvetica", Font.BOLD, 14);

	JLabel redScore, blueScore, time;

	public ScorePanel()
	{
		setBackground(new Color(255, 0, 255));
		blueScore = new JLabel("Blue:" + 100);
		add(blueScore);
		time = new JLabel("Time:" + 0);
		add(time);
		redScore = new JLabel("Red:" + 200);
		add(redScore);

	}

	public void updateTime(double newTime)
	{
		time.setText("Time" + newTime);
		time.repaint();
		
	}

	public void updateScores(int[] scores)
	{
		blueScore.setText("Blue: " +  scores[0]);
		redScore.setText("Red: " +  scores[1]);
		
		blueScore.repaint();
		redScore.repaint();
		
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	//
	// drawScore((Graphics2D)g);
	// }
	//
	// private void drawScore(Graphics2D g) {
	//
	// int i;
	// String s;
	//
	// System.out.println(getX() + "," + getY());
	// g.setFont(blueFont);
	// g.setColor(new Color(96, 128, 255));
	// s = "Blue Score: ";
	// g.drawString(s, getX() + 26, getY());
	//
	// g.setFont(redFont);
	// g.setColor(new Color(196, 128, 55));
	// s = "Red Score: ";
	// g.drawString(s, getX() + 126, getY());
	//
	// }
}
