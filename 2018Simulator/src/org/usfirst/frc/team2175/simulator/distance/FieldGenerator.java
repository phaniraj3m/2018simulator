package org.usfirst.frc.team2175.simulator.distance;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.usfirst.frc.team2175.simulator.Game;

import com.csvreader.CsvWriter;

public class FieldGenerator
{
	// assume top null belongs to blue, so red should avoid that
	// if BLUE_MODE is true, generate paths through top null, else generate
	// paths through bottom null

	private ArrayList<Vertex> redVertices = new ArrayList<Vertex>();
	private ArrayList<Vertex> blueVertices = new ArrayList<Vertex>();
	
	int incr = 18;
	


	int FIELD_WIDTH = 288 + 72 + 288;
	int FIELD_HEIGHT = 30 + 264 + 30;

	public static void main(String[] args)
	{
		new FieldGenerator();

	}

	private FieldGenerator()
	{
		try
		{
			generateVertices();
			saveVertices();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveVertices() throws Exception
	{
		
		BufferedWriter redWriter = new BufferedWriter(new FileWriter("C:\\frc2175\\2018simulator\\redVertices.csv"));
		BufferedWriter blueWriter = new BufferedWriter(new FileWriter("C:\\frc2175\\2018simulator\\blueVertices.csv"));
		
		String line = "﻿Vertex Number,Vertex Name,Vertex X,Vertex Y,Description";
		redWriter.write(line);redWriter.newLine();
		blueWriter.write(line);blueWriter.newLine();
		
		for (Iterator iterator = redVertices.iterator(); iterator.hasNext();)
		{
			Vertex vertex = (Vertex) iterator.next();
			redWriter.write(vertex.toString());
			redWriter.newLine();
		}
		for (Iterator iterator = blueVertices.iterator(); iterator.hasNext();)
		{
			Vertex vertex = (Vertex) iterator.next();
			blueWriter.write(vertex.toString());
			blueWriter.newLine();
		}
		
		redWriter.close();
		blueWriter.close();
		
		
		
	}

	private void generateVertices()
	{
		for (int x = 0; x <= FIELD_WIDTH; x += incr)
		{
			for (int y = 0; y <= FIELD_HEIGHT; y += incr)
			{
				Vertex v = processSquare(x, y);
			}
		}
		
		

	}

	private Vertex processSquare(int x, int y)
	{

		boolean isSwitch = isSwitch(x, y);
		boolean isScale = isScale(x, y);
		boolean isCorner = isCorner(x, y);

		int isNull = isNull(x, y); // 0 = not a null, otherwise returns RED or BLUE depending on which one its in 
		int isPowerCubeZone = isPowerZone(x, y);// 0 = not in power zone, otherwise returns RED or BLUE depending on which one its in 
		int isPlatformZone = isPlatformZone(x, y);// 0 = not in platform zone, otherwise returns RED or BLUE depending on which one its in 

		
		if ( isNull == Game.RED)
		{
			//System.out.println("Red Null " + x  + "," + y);
		}
		else if ( isNull == Game.BLUE)
		{
			//System.out.println("Blue Null " + x  + "," + y);
		}
		
		if (isSwitch || isScale || isCorner)
		{
			// this is not a vertex, the square is occupied by an object
			return null;
		}
		
		// depending on the values of the 3 integers, this is a possible vertex for 1 or both robots
		// so it will be added to the appropriate vertex list(s), and to appropriate edge list(s) 
		
		String label = "V_" + x + "_" + y;
		
		Vertex v = new Vertex(label, label, label, x, y,0,0); // width and height are fake, since this code isnt used
		
		// now decide which list to add to
		
		if (isNull == Game.RED || isPowerCubeZone == Game.RED ||isPlatformZone == Game.RED )
		{
			redVertices.add(v);
		}
		else if (isNull == Game.BLUE || isPowerCubeZone == Game.BLUE ||isPlatformZone == Game.BLUE )
		{
			blueVertices.add(v);
		}
		else
		{
			// neither a red or blue restricted one
			blueVertices.add(v);
			redVertices.add(v);
		}
		
		return v;
	}

	private int isPlatformZone(int x, int y)
	{
		// red platform (left)
		// bottom left starts at x = 261.47, y = 95.25
		// height = 133.5, width =  119.75
		
		if ( x >= 261.47 && x <= 261.47 + 119.75 )
		{
			if ( y >= 95.25 && y <= 95.25 + 133.5)
			{
				return Game.RED;
			}
		}
		else if ( x >= FIELD_WIDTH - 261.47 - 119.75 && x <= FIELD_WIDTH -261.47 )
		{
			if ( y >= 95.25 && y <= 95.25 + 133.5)
			{
				return Game.BLUE;
			}
		}		
		
		return 0;
	}

	private int isPowerZone(int x, int y)
	{
		// zone is 45 inches high, 42 inches wide
		// left corner is at x  = 98 inches, y = 139.5 inches  
		
		if ( x >= 98 && x <= 98 + 42 )
		{
			if ( y >= 139.5 && y <= 139.5 + 45)
			{
				return Game.RED;
			}
		}
		else if ( x >= FIELD_WIDTH - 98 - 42 && x <= FIELD_WIDTH - 98 )
		{
			if ( y >= 139.5 && y <= 139.5 + 45)
			{
				return Game.BLUE;
			}
		}	
		
		return 0;
	}

	private int isNull(int x, int y)
	{
		// we have defined blue on top, red on bottom
		// zone is 95.25 inches high, 72 inches wide
		// left corner is at x  = 288 inches, y = 0 inches  
		
		if ( x >= 288 && x <= 288 + 72 )
		{
			if ( y >= 0 && y <= 95.25)
			{
				return Game.RED;
			}
		}
		else if ( x >= 288 && x <= 288 + 72 )
		{
			if ( y >= FIELD_HEIGHT - 95.25 && y <= FIELD_HEIGHT)
			{
				return Game.BLUE;
			}
		}	
		return 0;
	}

	private boolean isCorner(int x, int y)
	{
		// the corners are defined by these edges
		if ( (x + y) <= 30 )
			 return true ; // bottom left corner
		
		if ( y >= 294 && x <= 30 && y >= 294  + x )
			return true ; // top left

		if (x >= (FIELD_WIDTH - 30) && y >= (324 -(x - FIELD_WIDTH+30)))
			return true ; // top right

		if (x >= (FIELD_WIDTH - 30) && y <= (x - FIELD_WIDTH+30))
			return true ; // bottom right

		
		return false;
	}

	private boolean isScale(int x, int y)
	{
		// height of the scale is 108 inches
		// bottom left corner of scale is x = FIELD_WIDTH/2 - 12, width = 24
		// y of bottom edge is 30 + 132 - 54 = 108 inches
		
		if ( x >= (FIELD_WIDTH/2 -12) && x <= (FIELD_WIDTH/2 + 12))
		{
			if ( y >= 108 && y <= 216)
			{
				return true ; // scale
			}
		}
		return false;
	}

	private boolean isSwitch(int x, int y)
	{
		// height of each switch is 153.5 inches
		// bottom left corner of switch 1 is x = 140, width = 56
		// bottom left corner of switch 2 is x = FIELD_WIDTH - 140 - 56, width = 56
		// y of bottom edge is 30 + 132 - 76.75 = 85.25 inches
		
		if ( x >= 140 && x <= 196)
		{
			if ( y >= 85.25 && y < (85.25 + 153.5))
			{
				return true ; // switch 1
			}
		}
		
		if ( x >= (FIELD_WIDTH - 140 -56)  && x <= (FIELD_WIDTH - 140))
		{
			if ( y >= 85.25 && y < (85.25 + 153.5))
			{
				return true ; // switch 2
			}
		}
		return false;
	}
}
