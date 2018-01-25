package org.usfirst.frc.team2175.simulator.distance;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team2168.robot.FalconLinePlot;
import org.usfirst.frc.team2168.robot.FalconPathPlanner;
import org.usfirst.frc.team2175.simulator.Game;

import com.csvreader.CsvReader;

public class Movements
{

	private List<Vertex> redNodes = new ArrayList<Vertex>();
	private List<Edge> redEdges = new ArrayList<Edge>();
	private HashMap<String, String> redEdgeMap = new HashMap<String, String>();
	private HashMap<String, String> blueEdgeMap = new HashMap<String, String>();

	private List<Vertex> blueNodes = new ArrayList<Vertex>();
	private List<Edge> blueEdges = new ArrayList<Edge>();

	private HashMap<String, Vertex> redMap = new HashMap<String, Vertex>(), blueMap = new HashMap<String, Vertex>();

	DijkstraAlgorithm redDijkstra, blueDijkstra;
	
	private static Movements m_instance;
	
	public static void main(String[] args)
	{
		Movements m = new Movements();
		
		double  [][] redpath = null;
		double  [][] bluepath = null;
		
		double [] start = new double [] {267,28};
		double [] end = new double [] {363,28};

		
		bluepath = m.getPath(start[0],start[1],end[0],end[1], Game.BLUE);
		m.display(bluepath, false, start,end);
		
		start = new double [] {265,300};
		end = new double [] {361,300};
		
		redpath = m.getPath(start[0],start[1],end[0],end[1], Game.RED);
		
		m.display(redpath, true, start,end);
		
		
		start = new double [] {28,28};
		end = new double [] {561,300};
		
		redpath = m.getPath(start[0],start[1],end[0],end[1], Game.RED);
		m.display(redpath, true, start,end);
		bluepath = m.getPath(start[0],start[1],end[0],end[1], Game.BLUE);
		
		m.display(bluepath, false, start,end);
		
		
		
		
	

	}
	
	public static Movements getInstance()
	{
		if (m_instance == null)
		{
			m_instance = new Movements();
		}
		return m_instance;		
		
	}
	
	// this is the main public interface
	
	public synchronized double [][] getPath(double x_start, double y_start, double x_target, double y_target, int teamColor)
	{
		
		// find zone in which the robot is
		
		List<Vertex> nodeList = redNodes;
		DijkstraAlgorithm dijsktra = redDijkstra;
		if ( teamColor == Game.BLUE)
		{
			nodeList = blueNodes;
			dijsktra = blueDijkstra;
		}
		
		Vertex [] v = findZone(nodeList,x_start, y_start, x_target, y_target);
		
		if (v[0] == null || v[1] == null)
			return null; // no path
		
		if ( v[0].equals(v[1]))
		{
			return new double[][] {new double[] {x_start,y_start},new double[] {x_target,y_target}};
		}
		
		dijsktra.execute(v[0]);		
		LinkedList<Vertex> path = dijsktra.getPath(v[1]);
		
		int size = path.size();
		
		double[][] waypoints = new double[size][2];
		// waypoints[0] = new double[] { start.getX(), start.getY() };
		int j = 0;
		for (Vertex vertex : path)
		{
			if (vertex != null)
			{
				waypoints[j] = new double[] { vertex.getX(), vertex.getY() };
				j++;
			}
		}
		
		double totalTime = 15; // seconds
		double timeStep = 0.1; // period of control loop on Rio, seconds
		double robotTrackWidth = 2; // distance between left and right
									// wheels, feet

		FalconPathPlanner planner = new FalconPathPlanner(waypoints);
		planner.calculate(totalTime, timeStep, robotTrackWidth);

		
		return planner.smoothPath;
	}

	private Vertex [] findZone(List<Vertex> nodeList, double x_start, double y_start, double x_target, double y_target)
	{
		// find the vertex in which the co-ordinates lie
		
		Vertex [] v = new Vertex[2]; 
		int found = 0 ;
		int i = 0 ;
		
		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();)
		{
			Vertex vertex = (Vertex) iterator.next();
			
			double x1 = vertex.getX();
			double x2 = vertex.getX() + vertex.getX_width();
			double y1 = vertex.getY();
			double y2 = vertex.getY() + vertex.getY_height();
			
			if ( x_start > x1 && x_start <= x2 && y_start > y1 && y_start <= y2)
			{
				v[0] = vertex;
				found++;
			}
			
			if ( x_target > x1 && x_target <= x2 && y_target > y1 && y_target <= y2)
			{
				v[1] = vertex;
				found++;
			}
			i++;
			if (found == 2) break;
			
		}
		
		return v;
	}
	private Movements()
	{
		// load vertices

		try
		{

			loadVertices("..//Vertices.csv", redMap, redNodes, redEdgeMap, true);
			// loadVertices("C:\\frc2175\\2018simulator\\blueVertices.csv",
			// blueMap, blueNodes, blueEdgeMap, false);

			loadEdges(redEdges, redMap, redEdgeMap);
			loadEdges(blueEdges, blueMap, blueEdgeMap);

			Graph redGraph = new Graph(redNodes, redEdges);
			redDijkstra = new DijkstraAlgorithm(redGraph);

			Graph blueGraph = new Graph(blueNodes, blueEdges);
			blueDijkstra = new DijkstraAlgorithm(blueGraph);

			// getPaths(redNodes.get(0), redNodes, redDijkstra,true);
			// getPaths(blueNodes.get(0), blueNodes, blueDijkstra,false);

			/*
			 * dijkstra.execute(redNodes.get(0)); LinkedList<Vertex> path =
			 * dijkstra.getPath(redNodes.get(6));
			 * 
			 * 
			 * Vertex oldV, newV = null; double totalD = 0.0; for (Vertex vertex
			 * : path) { if (newV != null) { oldV = newV; newV = vertex; double
			 * distance = dijkstra.getDistance(oldV, newV); totalD += distance;
			 * System.out.println(oldV + " " + newV + " " + distance); } else {
			 * newV = vertex; } } System.out.println("Total distance = " +
			 * totalD);
			 */

		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getPaths(Vertex start, List<Vertex> nodes, DijkstraAlgorithm dijkstra, boolean redMode)
	{
		dijkstra.execute(start);
		for (int i = nodes.size() - 4; i < nodes.size(); i++)
		{
			Vertex end = nodes.get(i);
			if (!start.equals(end))
			{
				System.out.print("Path from " + start.getId() + " to " + end.getId() + " is ");

				LinkedList<Vertex> path = dijkstra.getPath(end);
				Vertex oldV, newV = null;
				double totalD = 0.0;
				int countNodes = 0;
				for (Vertex vertex : path)
				{
					if (newV != null)
					{
						oldV = newV;
						newV = vertex;
						double distance = dijkstra.getDistance(oldV, newV);
						totalD += distance;
						countNodes++;
						System.out.print(newV.getId() + " ,");
					}
					else
					{
						countNodes++;
						newV = vertex;
					}

				}
				System.out.println(" Total distance = " + totalD);

				double[][] waypoints = new double[countNodes][2];
				// waypoints[0] = new double[] { start.getX(), start.getY() };
				int j = 0;
				for (Vertex vertex : path)
				{
					if (vertex != null)
					{
						waypoints[j] = new double[] { vertex.getX(), vertex.getY() };
						j++;
					}
				}

				double totalTime = 8; // seconds
				double timeStep = 0.1; // period of control loop on Rio, seconds
				double robotTrackWidth = 2; // distance between left and right
											// wheels, feet

				FalconPathPlanner planner = new FalconPathPlanner(waypoints);
				planner.calculate(totalTime, timeStep, robotTrackWidth);
				
				display(planner.smoothPath, redMode, new double [] {start.getX(),start.getY()}, new double [] {end.getX(),end.getY()});


			}

		}

	}
	
	private void display(double [][] smoothPath, boolean redMode, double[] start, double[] end)
	{
		Color c = Color.red;
		if (!redMode)
			c = Color.blue;
		
		FalconLinePlot fig1 = new FalconLinePlot(smoothPath, c, c);
		fig1.yGridOn();
		fig1.xGridOn();
		fig1.setYLabel("Y (inches)");
		fig1.setXLabel("X (inches)");
		fig1.setXTic(0, 640, 40);
		fig1.setYTic(0, 340, 30);
		if (redMode)
		{
			fig1.setTitle("Red Trajectory from (" + start[0] + "," + start[1] + ") to (" + end[0] + "," + end[1] + ")");
		}
		else
		{
			fig1.setTitle("Blue Trajectory from (" + start[0] + "," + start[1] + ") to (" + end[0] + "," + end[1] + ")");

		}
	}

	private void loadVertices(String s, HashMap<String, Vertex> vertices, List<Vertex> nodes,
			HashMap<String, String> edgeMap, boolean redMode) throws FileNotFoundException, IOException
	{
		CsvReader reader = new CsvReader(s);

		reader.readHeaders();

		while (reader.readRecord())
		{
			String[] values = reader.getValues();
			String number = values[0];
			String name = values[1];
			String x = values[2];
			String y = values[3];
			String x_w = values[4];
			String y_h = values[5];
			String available = values[6];
			String connected = values[7];

			if (number == null || number.isEmpty())
				continue;

			Vertex v = new Vertex(number, name, name, Double.parseDouble(x), Double.parseDouble(y),
					Double.parseDouble(x_w), Double.parseDouble(y_h));

			if (available.equals("B"))
			{
				blueNodes.add(v);
				blueMap.put(v.getId(), v);
				blueEdgeMap.put(number, connected);
			}
			else if (available.equals("R"))
			{
				redNodes.add(v);
				redMap.put(v.getId(), v);
				redEdgeMap.put(number, connected);
			}
			else if (available.equals("A"))
			{
				blueNodes.add(v);
				redNodes.add(v);

				blueMap.put(v.getId(), v);
				redMap.put(v.getId(), v);

				blueEdgeMap.put(number, connected);
				redEdgeMap.put(number, connected);
			}
		}

	}

	private void loadEdges(List<Edge> edges, HashMap<String, Vertex> vertices, HashMap<String, String> edgeMap)
	{
		// lets create edges from every vertex to every other vertex. based on
		// the nodes on the edgeMap
		Iterator<String> keys = edgeMap.keySet().iterator();
		while (keys.hasNext())
		{
			String key = (String) keys.next();
			String values = edgeMap.get(key);
			String[] vertexList = values.split(",");
			for (int i = 0; i < vertexList.length; i++)
			{
				String key2 = vertexList[i];
				Vertex v2 = vertices.get(key2);
				Vertex v1 = vertices.get(key);

				boolean onPlatform = false;
				if (key.equals("V28") || key.equals("V29") || key.equals("V30") || key.equals("V39")
						|| key.equals("V40") || key.equals("V41"))
					onPlatform = true;

				if (key2.equals("V28") || key2.equals("V29") || key2.equals("V30") || key2.equals("V39")
						|| key2.equals("V40") || key2.equals("V41"))
					onPlatform = true;

				if (v1 != null && v2 != null)
				{
					Edge lane = new Edge(key + ":" + key2, v1, v2, onPlatform);
					edges.add(lane);

					Edge lane2 = new Edge(key2 + ":" + key, v2, v1, onPlatform);
					edges.add(lane2);
				}
			}

		}

	}

	private void createEdges(Vertex v, List<Edge> edges, HashMap<String, Vertex> vertices,
			HashMap<String, String> edgeMap)
	{
		String s = "V_";
		double x = v.getX();
		double y = v.getY();

		int incr = 18; // size of each square

		// System.out.println(v);
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				if (j == 0 && i == 0)
					continue;
				String label = s + ((int) x + incr * i) + "_" + ((int) y + incr * j);
				Vertex v2 = vertices.get(label);
				if (v2 != null)
				{
					// System.out.println(label);
					String key1 = v.getId();
					String key2 = v2.getId();
					if (edgeMap.get(key1 + ":" + key2) == null && edgeMap.get(key2 + ":" + key1) == null)
					{
						Edge lane = new Edge(key1 + ":" + key2, v, v2, false);
						edges.add(lane);
						edgeMap.put(key1 + ":" + key2, "A");
					}
				}
			}
		}

	}
}
