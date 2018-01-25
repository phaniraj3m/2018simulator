package org.usfirst.frc.team2175.simulator.distance.test;

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
import org.usfirst.frc.team2175.simulator.distance.DijkstraAlgorithm;
import org.usfirst.frc.team2175.simulator.distance.Edge;
import org.usfirst.frc.team2175.simulator.distance.Graph;
import org.usfirst.frc.team2175.simulator.distance.Vertex;

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

	public static void main(String[] args)
	{
		new Movements();

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
			DijkstraAlgorithm redDijkstra = new DijkstraAlgorithm(redGraph);

			Graph blueGraph = new Graph(blueNodes, blueEdges);
			DijkstraAlgorithm blueDijkstra = new DijkstraAlgorithm(blueGraph);

			getPaths(redNodes.get(0), redNodes, redDijkstra,true);
			getPaths(blueNodes.get(0), blueNodes, blueDijkstra,false);

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
		for (int i = nodes.size()-4; i < nodes.size(); i++)
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

				Color c = Color.red;
				if ( !redMode)
					c = Color.blue;
				
				FalconLinePlot fig1 = new FalconLinePlot(planner.smoothPath, c, Color.green);
				fig1.yGridOn();
				fig1.xGridOn();
				fig1.setYLabel("Y (inches)");
				fig1.setXLabel("X (inches)");
				if (redMode)
				{
					fig1.setTitle("Red Trajectory from " + start.getId() + " to " + end.getId());
				}
				else
				{
					fig1.setTitle("Blue Trajectory from " + start.getId() + " to " + end.getId());
					
				}

				// force graph to show 1/2 field dimensions of 24ft x 27 feet
				//fig1.setXTic(0, 684, 1);
				//fig1.setYTic(0, 324, 1);
				//fig1.addData(planner.smoothPath, Color.red, Color.blue);

			}

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
