package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ArcadePanel extends JPanel {

	private Image bgImage, redRobot;

	public ArcadePanel() {
		bgImage = loadImage("f://workspace//2018Field.jpg");
		ScreenManager screen = new ScreenManager();
		
	

		Graphics2D g = screen.getGraphics();
		draw(g);

	}

	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}

	public void draw(Graphics g) {
		g.drawImage(bgImage, 0, 0, null);
	}

	private void doDrawing(Graphics g) {

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
		//
		// ArrayList ms = craft.getMissiles();
		//
		// for (Object m1 : ms) {
		// Missile m = (Missile) m1;
		// g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		// }
	}

}
