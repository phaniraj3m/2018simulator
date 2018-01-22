package org.usfirst.frc.team2175.simulator.ui;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Craft
{

	private int dx;
	private int dy;
	private int x;
	private int y;
	private Image image;

	private String fileName;

	public Craft(String f, int x_init, int y_init)
	{

		initCraft(f, x_init, y_init);
	}

	private void initCraft(String f, int x_init, int y_init)
	{

		ImageIcon ii = new ImageIcon(f);
		image = ii.getImage();
		x = x_init;
		y = y_init;
	}

	public void move()
	{
		x += dx;
		y += dy;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Image getImage()
	{
		return image;
	}

	public void keyPressed(KeyEvent e)
	{

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
		{
			dx = -1;
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			dx = 1;
		}

		if (key == KeyEvent.VK_UP)
		{
			dy = -1;
		}

		if (key == KeyEvent.VK_DOWN)
		{
			dy = 1;
		}
		if (key == KeyEvent.VK_PAGE_DOWN)
		{
			dy = 5;
		}
		if (key == KeyEvent.VK_PAGE_UP)
		{
			dy = -5;
		}
		if (key == KeyEvent.VK_HOME)
		{
			dx = 5;
		}
		if (key == KeyEvent.VK_END)
		{
			dx = -5;
		}

	}

	public void keyReleased(KeyEvent e)
	{

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
		{
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			dx = 0;
		}

		if (key == KeyEvent.VK_UP)
		{
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN)
		{
			dy = 0;
		}
		if (key == KeyEvent.VK_PAGE_DOWN)
		{
			dy = 0;
		}
		if (key == KeyEvent.VK_PAGE_UP)
		{
			dy = 0;
		}
		if (key == KeyEvent.VK_HOME)
		{
			dx = 0;
		}
		if (key == KeyEvent.VK_END)
		{
			dx = 0;
		}
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}