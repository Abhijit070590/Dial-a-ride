
class Vertex implements Comparable<Vertex>
{
    public final int name;
    public Edge[] adjacencies;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    public Vertex(int argName) { name = argName; }
    //public String toString() { return name; }
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
	@Override
	public String toString() {
		return "Vertex [name=" + name + "]";
	}
	
    
    
    
}