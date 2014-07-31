import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.StringTokenizer;

//import junit.framework.Test;



/**
 *
 * @author Abhijit
 */


class Input {

    /**
     * @param args the command line arguments
     */
	 
     static int numberOfLocations;
     static int numberOfVehicles;
     static int capacity;
     static int numberOfPassengers;
     static int[][] distanceMatrix;
     static double[][] costMatrix;
     static Taxi[] tarr; 
     static int[] locationAtMidnight;
     Request request;
     static ArrayList< Request > baseCost = new ArrayList<Request>();
     static ArrayList< Request > baseTime = new ArrayList<Request>();
	public  void takingInput(String input) {
        File file;
        String line;
        int i,j;
        file = new File(input);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            line=br.readLine();
            StringTokenizer str = new StringTokenizer(line);
            numberOfLocations = Integer.parseInt((String)str.nextElement());           
            numberOfVehicles = Integer.parseInt((String)str.nextElement());           
            capacity = Integer.parseInt((String)str.nextElement());            
            numberOfPassengers = Integer.parseInt((String)str.nextElement());            
            distanceMatrix = new int[numberOfLocations+1][numberOfLocations+1];
            costMatrix = new double[numberOfLocations+1][numberOfLocations+1];
            for(i=0;i<numberOfLocations;i++)
            {
                line = br.readLine();
                str = new StringTokenizer(line);
                j=0;
                while(str.hasMoreElements())
                {
                    distanceMatrix[i][j]=Integer.parseInt((String)str.nextElement());
                    j++;
                }
            }
            for(int k=0;k<numberOfLocations;k++)
            {
         	   Dijkstra2 s1 = new Dijkstra2();
         	   for(int m=0;m<numberOfLocations;m++)
         	   costMatrix[k+1][m+1]=s1.compute(k+1, m+1, numberOfLocations, distanceMatrix);
         	   
            }
           
           locationAtMidnight = new int[numberOfVehicles];
           tarr = new Taxi[numberOfVehicles];
           line= br.readLine();
           str = new StringTokenizer(line);
           j=0;
                while(str.hasMoreElements())
                {
                    locationAtMidnight[j]=Integer.parseInt((String)str.nextElement());
                    System.out.print(locationAtMidnight[j]+" ");
                    tarr[j]= new Taxi();
                    tarr[j].source = locationAtMidnight[j];
                    tarr[j].destination=locationAtMidnight[j];
                    tarr[j].currentLocation = locationAtMidnight[j];
                    j++;
                }
           
           System.out.println(" ");
           i=0;
           for(int ii=0;ii<numberOfPassengers;ii++){
        	   line=br.readLine();
        	   line=line.trim();
        	  
        	   request = new Request();
               str = new StringTokenizer(line);
               
               request.no=ii+1;
               request.source = Integer.parseInt(str.nextToken());
               request.destination  = Integer.parseInt(str.nextToken());
               request.tstart =  Integer.parseInt(str.nextToken());
               request.tend = Integer.parseInt(str.nextToken());
               request.distance=costMatrix[request.source][request.destination];
               request.satisfied=false;
               baseCost.add(request);
               baseTime.add(request);
               
               
           }
           Collections.sort(baseCost, new RequestComparator2());
           Collections.sort(baseTime,new RequestComparator());
           
          
        } catch (FileNotFoundException ex) {
           System.out.println("error");
        } catch (IOException ex) {
            System.out.println("error");
            }
    }
       
}
