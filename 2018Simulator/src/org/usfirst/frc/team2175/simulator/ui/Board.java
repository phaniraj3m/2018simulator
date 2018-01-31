package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Color;
import java.awt.Dimension;
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

	private Image bgImage;
	
    private Timer timer;
    private Craft redCraft1, redCraft2, redCraft3;
    private Craft blueCraft1, blueCraft2, blueCraft3;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
    	Dimension d = new Dimension(900,440);
    	setSize(d);
    	setPreferredSize(d);
    	setMinimumSize(d);
    	setMaximumSize(d);
    	bgImage = loadImage(".//2018Field.jpg");
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        redCraft1 = new Craft(".//RedWithCube.jpg",185,90);
        
        redCraft2 = new Craft(".//RedWithCube.jpg",185,260);
        redCraft3 = new Craft(".//RedWithCube.jpg",185,370);
        
        blueCraft1 = new Craft(".//BlueWithCube.jpg",1040,95);
        blueCraft2 = new Craft(".//BlueWithCube.jpg",1040,190);
        blueCraft3 = new Craft(".//BlueWithCube.jpg",1040,380);

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

	public Craft getRedCraft1()
	{
		return redCraft1;
	}

	public Craft getRedCraft2()
	{
		return redCraft2;
	}

	public Craft getRedCraft3()
	{
		return redCraft3;
	}

	public Craft getBlueCraft1()
	{
		return blueCraft1;
	}

	public Craft getBlueCraft2()
	{
		return blueCraft2;
	}

	public Craft getBlueCraft3()
	{
		return blueCraft3;
	}
}