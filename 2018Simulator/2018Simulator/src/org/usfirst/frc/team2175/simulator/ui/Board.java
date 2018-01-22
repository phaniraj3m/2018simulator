package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

	private Image bgImage, redRobot;
	
    private Timer timer;
    private Craft redCraft1, redCraft2, redCraft3;
    private Craft blueCraft1, blueCraft2, blueCraft3;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
    	bgImage = loadImage(".//2018Field.jpg");
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        redCraft1 = new Craft(".//2175Red.jpg",180,80);
        
        redCraft2 = new Craft(".//2175Red.jpg",180,250);
        redCraft3 = new Craft(".//2175Red.jpg",180,360);
        
        blueCraft1 = new Craft(".//2175Blue.jpg",980,75);
        blueCraft2 = new Craft(".//2175Blue.jpg",980,160);
        blueCraft3 = new Craft(".//2175Blue.jpg",980,380);

        // timer = new Timer(DELAY, this);
        //timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(bgImage, 0,0, this);        
        g2d.drawImage(blueCraft1.getImage(), blueCraft1.getX(), blueCraft1.getY(), this);        
        g2d.drawImage(blueCraft2.getImage(), blueCraft2.getX(), blueCraft2.getY(), this);        
        g2d.drawImage(blueCraft3.getImage(), blueCraft3.getX(), blueCraft3.getY(), this);        

        g2d.drawImage(redCraft1.getImage(), redCraft1.getX(), redCraft1.getY(), this);        
        g2d.drawImage(redCraft2.getImage(), redCraft2.getX(), redCraft2.getY(), this);        
        g2d.drawImage(redCraft3.getImage(), redCraft3.getX(), redCraft3.getY(), this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    	blueCraft1.move();
    	blueCraft2.move();
    	blueCraft3.move();
        
    	redCraft1.move();
    	redCraft2.move();
    	redCraft3.move();
        repaint();

    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	blueCraft1.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	blueCraft1.keyPressed(e);
        }
    }
    
    
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
}