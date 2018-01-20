package org.usfirst.frc.team2175.simulator.distance.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.usfirst.frc.team2175.simulator.distance.DijkstraAlgorithm;
import org.usfirst.frc.team2175.simulator.distance.Edge;
import org.usfirst.frc.team2175.simulator.distance.Graph;
import org.usfirst.frc.team2175.simulator.distance.Vertex;

import com.csvreader.CsvReader;

public class Movements
{

	private List<Vertex> nodes;
	private List<Edge> edges;

	public static void main(String[] args)
	{
		new Movements();

	}

	private Movements()
	{
		// load vertices

		nodes = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();

		try
		{
			loadVertices();

			loadEdges();
			
	        Graph graph = new Graph(nodes, edges);
	        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
	        
	        dijkstra.execute(nodes.get(0));
	        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(64));
	        
	        Vertex oldV, newV = null;
	        double totalD = 0.0;
	        for (Vertex vertex : path) {
	        	if ( newV != null)
	        	{
	        		oldV = newV;
	        		newV = vertex;
	        		double distance = dijkstra.getDistance(oldV, newV);
	        		totalD += distance;
	        		System.out.println(oldV + " " + newV + " " + distance);
	        	}
	        	else
	        	{
	        		newV = vertex;
	        	}
	        }
	        System.out.println("Total distance = " + totalD);
	        
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadVertices() throws FileNotFoundException, IOException
	{
		CsvReader reader = new CsvReader("C:\\frc2175\\2018simulator\\Vertices.csv");

		reader.readHeaders();

		while (reader.readRecord())
		{
			String[] values = reader.getValues();
			String number = values[0];
			String name = values[1];
			String x = values[2];
			String y = values[3];
			String description = values[4];

			Vertex v = new Vertex(number, name, description, Double.parseDouble(x), Double.parseDouble(y));
			nodes.add(v);
		}
	}

	private void loadEdges()
	{
		// lets create edges from every vertex to every other vertex
		
		Random r = new Random();

		for (int i = 0; i < nodes.size(); i++)
		{
			Vertex v1 = nodes.get(i);
			for (int j = 1; j < nodes.size(); j++)
			{
				Vertex v2 = nodes.get(j);

				// create an edge from V1 to V2
				
				if ( r.nextInt(10) % 2 == 0) continue;

				Edge lane = new Edge(i + " to " + j , v1,v2);
				edges.add(lane);

			}
		}
	}
}
