import java.util.Comparator;

class RequestComparator implements Comparator<Request>{

	@Override
	public int compare(Request o1, Request o2) {
		// TODO Auto-generated method stub
		if(o1.tstart<o2.tstart)
			return -1;
		if(o1.tstart==o2.tstart)
		{
			if(o1.tend<o2.tend)
				return -1;
			if(o1.tend>o2.tend)
				return 1;
			else 
				return 0;
		
		}
		else
			return 1;
	}
	
}
