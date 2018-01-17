package com.zetcode;

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
    private Craft craft1, craft2;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
    	bgImage = loadImage("f://workspace//2018Field.jpg");
    	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        craft1 = new Craft("f://workspace//2175Blue.jpg");
        craft2 = new Craft("f://workspace//2175Red.jpg");

        timer = new Timer(DELAY, this);
        timer.start();        
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
        g2d.drawImage(craft1.getImage(), craft1.getX(), craft1.getY(), this);        
        g2d.drawImage(craft2.getImage(), 600-craft1.getX(), 400-craft1.getY(), this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        craft1.move();
        craft2.move();
        repaint();  
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft1.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft1.keyPressed(e);
        }
    }
    
    
	private Image loadImage(String fileName) {
		return new ImageIcon(fileName).getImage();
	}
}