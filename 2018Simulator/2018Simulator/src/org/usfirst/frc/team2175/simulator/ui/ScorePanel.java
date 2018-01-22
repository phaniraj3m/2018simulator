package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel
{

	private final int SCREEN_SIZE = 200;

	private final Font scoreFont = new Font("Helvetica", Font.BOLD, 24);



	JLabel redScore, blueScore, time;

	public ScorePanel()
	{
		setBackground(new Color(0,200,0));
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		
		blueScore = new JLabel("Blue:" + 0);
		blueScore.setForeground(Color.BLUE);
		blueScore.setFont(scoreFont);
		add(blueScore);
		
		add(Box.createHorizontalStrut(600));
		
		time = new JLabel("Time:" + 0);
		time.setFont(scoreFont);
		add(time);
		
		add(Box.createHorizontalStrut(480));
		redScore = new JLabel("Red:" + 0);
		redScore.setFont(scoreFont);
		redScore.setForeground(Color.RED);
		add(redScore);
		

	}

	public void updateTime(double newTime)
	{
		newTime = Math.round(100*newTime)/100;
		
		time.setText("Time " + newTime);
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
