import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;




public class Dijkstra2 {

	/**
	 * @param args
	 */
	public void computePaths(Vertex source)
    {
        source.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
      	vertexQueue.add(source);

	while (!vertexQueue.isEmpty()) {
	    Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies)
            {
                Vertex v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.minDistance + weight;
		if (distanceThroughU < v.minDistance) {
		    vertexQueue.remove(v);
		    v.minDistance = distanceThroughU ;
		    v.previous = u;
		    vertexQueue.add(v);
		}
            }
        }
    }

    public List<Vertex> getShortestPathTo(Vertex target)
    {
        List<Vertex> path = new ArrayList<Vertex>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return path;
    }
    public double compute(int source,int destination,int numberOfLcation,int distanceMatrix[][])
    {
    	source=source-1;
    	destination= destination-1;
    int numberOfVer=numberOfLcation;
    Vertex[] v=new Vertex[numberOfVer];
    for(int i=0;i<numberOfVer;i++)
    {
    	v[i]=new Vertex(i+1);
    }
    
    for(int i=0;i<numberOfVer;i++)
    {
    	int count=0;
    	for(int j=0;j<numberOfVer;j++)
    	{
    		if(distanceMatrix[i][j]!=-1)
    			count++;
    	}
    	v[i].adjacencies = new Edge[count];
    	
    	for(int j=0,k=0;j<numberOfVer;j++)
    	{
    		if(distanceMatrix[i][j]!=-1)
    			v[i].adjacencies[k++]= new Edge(v[j], distanceMatrix[i][j]);
    	}
    	
    }
	
        computePaths(v[source]);
       
	    //System.out.println("Distance to " + v[destination] + ": " + v[destination].minDistance);
	    List<Vertex> path = getShortestPathTo(v[destination]);
	    //System.out.println("Path: " + path);
	    return v[destination].minDistance;
	}
    

}
