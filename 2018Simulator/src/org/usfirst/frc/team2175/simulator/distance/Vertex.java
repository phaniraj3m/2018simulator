package org.usfirst.frc.team2175.simulator.distance;

public class Vertex
{
	final private String id;
	final private String name, description;
	double x, y; // this is the bottom left corner
	double x_width, y_height;

	public Vertex(String id, String name, String description, double x, double y,double x_width, double y_height)
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.x = x;
		this.y = y;
		this.x_width = x_width;
		this.y_height = y_height;
		
	}
	public Vertex(String id, String name)
	{
		this(id, name, name, 0, 0,0,0);
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id == null)
		{
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return id + "," +   name+ ","  +  x + "," +  y + "," +  description;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getX_width()
	{
		return x_width;
	}
	public double getY_height()
	{
		return y_height;
	}

}
