import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;




  public class AlgorithmProject{
	
	  private int nearestTaxi(Request r) 
	  {
		  double disFromTaxi=0,mindis;
		  
		  int position=0,i;
		  double[] d = new double[Input.numberOfVehicles];
	  		for(i=0;i<Input.numberOfVehicles;i++)
	    		{
	  		    
	  			disFromTaxi=Input.costMatrix[r.source][Input.tarr[i].currentLocation];
	  			
	  			d[i]=disFromTaxi;
	    		}
	  
	  		mindis=d[0];
	  		for(i=1;i<Input.numberOfVehicles;i++)
	  			{
	  		
	  		
	  			if(d[i]<mindis)
	  					{
	  					mindis=d[i];
	  					position=i;
	  					}
	  		
	  			}
		return position;
		  
	  }
	  private int secondTaxi(Request r,int previousPosition)
	  {
		  double disFromTaxi=0,mindis;
		  
		  int position=0,i;
		  double[] d = new double[Input.numberOfVehicles];
	  		for(i=0;i<Input.numberOfVehicles;i++)
	    		{
	  		    
	  			disFromTaxi=Input.costMatrix[r.source][Input.tarr[i].currentLocation];
	  			
	  			d[i]=disFromTaxi;
	    		}
	  
	  		mindis=d[0];
	  		for(i=1;i<Input.numberOfVehicles;i++)
	  			{
	  		
	  		
	  			if((d[i]<mindis)&&(i!=previousPosition))
	  					{
	  					mindis=d[i];
	  					position=i;
	  					}
	  		
	  			}
		  return position;
	  }
	 
	  public  void start(String input,String Output) throws IOException{
		  int te = 0;
		  int count=0,revenue=0;
		  double min=999;
		  Input i1=new Input();
		  i1.takingInput(input);
		  int position=0;
		  double disf=0,dist=0;
		  BufferedWriter bw=new BufferedWriter(new FileWriter(new File(Output)));
		  for(Request r : i1.baseTime)
		  		{
			    	position=nearestTaxi(r);
			  		System.out.println(position);
			  		te=(int) (i1.tarr[position].time+2*i1.costMatrix[i1.tarr[position].currentLocation][r.source]);
			  		disf=i1.costMatrix[i1.tarr[position].currentLocation][r.source];
			  		dist=i1.costMatrix[r.source][r.destination];
			  		if(disf<dist)
			  		{
			  			if((r.tend>te)&&(i1.tarr[position].time==0)&&(r.tend<1440))
			  		
			  				{
			  				if(te<r.tstart)
			  					i1.tarr[position].time=r.tstart;
			  				else
			  					i1.tarr[position].time=te;
			  				i1.tarr[position].addingPass(r);
			  				count++;
			  				i1.tarr[position].flag=true;
			  				r.satisfied=true;
			  				i1.tarr[position].revenue=i1.tarr[position].revenue+i1.costMatrix[r.source][r.destination];
			  				bw.write("Request "+r.no+"satisfied by taxi "+position+" at time "+i1.tarr[position].time+"\n");	
			  				}
			  			else
			  			{ 
			  			te=(int) (i1.tarr[position].time+2*i1.costMatrix[i1.tarr[position].currentLocation][r.source]);
			  			if((r.tend>te)&&(r.tend<1440))
			  				{	
			  				if(te<r.tstart)
			  					i1.tarr[position].time=r.tstart;
			  				else
			  					i1.tarr[position].time=te;
			  				i1.tarr[position].addingPass(r);
			  				count++;
			  				i1.tarr[position].revenue=i1.tarr[position].revenue+i1.costMatrix[r.source][r.destination];
			  				bw.write("Request "+r.no+"satisfied by taxi "+position+" at time "+i1.tarr[position].time+"\n");
		  			}
			  	else
			  		{
			  			
			  			position=secondTaxi(r, position);
			  			te=(int) (i1.tarr[position].time+2*i1.costMatrix[i1.tarr[position].currentLocation][r.source-1]);
			  			if((r.tend>te)&&(r.tend<1440))
			  				{
			  				if(te<r.tstart)
			  					i1.tarr[position].time=r.tstart;
			  				else
			  					i1.tarr[position].time=te;
			  				i1.tarr[position].addingPass(r);
			  				count++;
			  				i1.tarr[position].revenue=i1.tarr[position].revenue+i1.costMatrix[r.source-1][r.destination-1];
			  				bw.write("Request "+r.no+"satisfied by taxi "+position+" at time "+i1.tarr[position].time+"\n");
			  				}
			  
			  			
			  		
		  			}
			  	}
			  			}
		  	}
		  
		  bw.write("The total number of request satisfied = "+count+"\n");
		  for(int i=0;i<Input.numberOfVehicles;i++)
		  {
			 bw.write("Revenue of Cab"+i+":"+Integer.toString((int) Input.tarr[i].revenue)+"\n");
			 revenue = (int) (revenue+Input.tarr[i].revenue);
		  }
		  
		  bw.write("total revenue"+revenue);
		  bw.close();
		 
	  }
  }
  