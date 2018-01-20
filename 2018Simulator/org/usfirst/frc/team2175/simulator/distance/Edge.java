package org.usfirst.frc.team2175.simulator.distance;

public class Edge  {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private double weight;

    public Edge(String id, Vertex source, Vertex destination, double weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    
    public Edge(String id, Vertex source, Vertex destination) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        
        weight = Math.pow(source.x - destination.x,2) + 
        		Math.pow(source.y - destination.y,2);
        weight = Math.sqrt(weight);
    
    }
    public String getId() {
        return id;
    }
    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}
