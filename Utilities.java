package architecture;

import java.util.Random;

public class Utilities {

    
    //This function returns the estimation of number of patients per the
    //day according to predicted function. The peak is estimated at day 30 to day 40
    // Some volunteer to implement this function? 
    //Now I just write a constant value, but it can be defined as a function returning
    //The number of bed requests
    public int computeNumberPatients(int dayOfSimulation) {
    	 Random random = new Random();  
    	int retVal=0;
    	  int numbOfPatient;
    	  int randomInteger = random.nextInt(10);
    	  boolean randomBoolean=random.nextBoolean();
    	  if (dayOfSimulation>=47 && dayOfSimulation<=53) {//Assuming day 50 is the peak of pandemia
    		  numbOfPatient=100;
    	  }
    	  else {
    		  numbOfPatient=100-2*Math.abs(30-dayOfSimulation);
    		  if (numbOfPatient<0) {
    			  numbOfPatient=1;
    		  }
    	  }
	//Adjusting value
		if (randomBoolean) {
				numbOfPatient=numbOfPatient+randomInteger;
	     }
		else {
			numbOfPatient=numbOfPatient-randomInteger;  
			}    	  
	
	    	  return retVal;
	}
    //This is just a temporary function to determine the number of patients
    
    
    //We need to determine the diabetes indices
   public boolean generateRandomDiabetes() {
	   boolean doesDiabetes=false;
	   return doesDiabetes;
   }
   
   public boolean generateRandomObesity() {
	   boolean doesObesity=false;
	   return doesObesity;
   }
   
   public boolean generateRandomType() {
	   boolean doesRequireVentilator=false;
	   return doesRequireVentilator;
   }

   public boolean generateRandomHyper() {
	   boolean doesHyper=false;
	   return doesHyper;
   }
   
   public boolean generateRandomEpoc() {
	   boolean doesEpoc=false;
	   return doesEpoc;
   }
   
   public boolean generateRandominmunesup() {
	   boolean doesinmunesup=false;
	   return doesinmunesup;
   }
   
   
   /**
		  epoc = xEpoc;
		  inmunesup= xInmunesup;
    * **/
	
	
}
