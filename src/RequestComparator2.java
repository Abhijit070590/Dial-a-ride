import java.util.Comparator;


public class RequestComparator2 implements Comparator<Request>{

	@Override
	public int compare(Request o1, Request o2) {
		// TODO Auto-generated method stub
		if(o1.distance>o2.distance)
		{
		return -1;
		
		}
	else
		{
		if(o1.distance<o2.distance)
			{
			return 1;
			}
		else
			return 0;
		}
	}

}
