import java.util.ArrayList;
import java.util.PriorityQueue;


public class Taxi {
	public static boolean flag;
	int source;
	int destination;
	ArrayList<Request> Passengers= new ArrayList<Request>();
	int nump=0;
	double revenue=0;
	int time=0;
	int currentLocation;
	int nextstop;

	
	void addingPass(Request r){
		r.satisfied=true;
		Passengers.add(r);
		nump++;
		flag=false;
		int mindis,i;
		int position=0;
		source=r.source;
		destination=r.destination;
		currentLocation=r.source;
		int des=0;
		Request aa=null;
		if(nump==Input.capacity){
		while(!Passengers.isEmpty())
		{
			i=0;
			mindis=(int) Input.costMatrix[currentLocation][Passengers.get(0).destination];
			aa=Passengers.get(0);
			for(Request re: Passengers)
			{
				if(mindis>(int) Input.costMatrix[currentLocation][re.destination])
				{
					mindis=(int) Input.costMatrix[currentLocation][re.destination];
					position=i;
					des=re.destination;
					aa=re;
				}
				i++;
			}
			System.out.println("Request"+aa.no+"is dropped at"+time);
			Passengers.remove(aa);
			nump--;
			time=(int) (time+2*Input.costMatrix[currentLocation][des]);
//			System.out.println(position+" "+a.destination);
			currentLocation=des;
		}
		}
		
		int ii=0,position2=-1;
		Request a1=null;
		for(Request ps:Passengers)
		{
			if(ps.destination==currentLocation)
			{
				//Passengers.remove(ii);
				position2=ii;
				a1=ps;
				
				
			}
			ii++;
		}
		
		if(position2!=-1)
		{
			System.out.println("Request"+a1.no+"is dropped at"+time);
			Passengers.remove(position2);
			nump--;
			
		}
		
	}


	
		
	
}

