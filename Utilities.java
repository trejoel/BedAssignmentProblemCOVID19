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
  
    
    //Assuming the avarage age is 45
    
public int generateRandomAge() {
	int xAge=45;
	//Please complete to generate a consistent value for age
	//consider the following table to generate a random age:
	//https://www.cdc.gov/mmwr/volumes/69/wr/mm6915e3.htm 
	return xAge;

}

/*
 * This function we can determine the probabiliy a patient is obese, diabetes, hipertense,
 * etc. The current values are just random
 * */
    
    
    
    //We need to determine the diabetes indices
   public boolean generateRandomDiabetes() { //Assume 40% of hospitalization is diabetes
	   boolean doesDiabetes=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<5) {
		   doesDiabetes=true;
	   }
	   return doesDiabetes;
   }
   
   public boolean generateRandomObesity() {//Assume 70% of hospitalization is obese
	   boolean doesObesity=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<8) {
		   doesObesity=true;
	   }
	   return doesObesity;
   }
   
   public boolean generateRandomType() { //Assume 20% of hospitalization require ventilator
	   boolean doesRequireVentilator=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<3) {
		   doesRequireVentilator=true;
	   }
	   return doesRequireVentilator;
   }

   public boolean generateRandomHyper() {// Assume 50% of hospitalization have hypertension
	   boolean doesHyper=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<6) {
		   doesHyper=true;
	   }
	   return doesHyper;
   }
   
   public boolean generateRandomEpoc() {// Assume 10% of hospitalization have epoc
	   boolean doesEpoc=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<2) {
		   doesEpoc=true;
	   }
	   return doesEpoc;
   }
   
   public boolean generateRandominmunesup() {// Assume 40% of hospitalization have weak inmumesup system
	   boolean doesinmunesup=false;
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   if (randomInteger<5) {
		   doesinmunesup=true;
	   }
	   return doesinmunesup;
   }
   
   //Some volunteer to develop this function. Now is just a random value
   
   public int estimateLenghtOfStay(int age) {
	   int LOS=15; // The average time is 15 days
	   Random random = new Random();
	   int randomInteger = random.nextInt(10);
	   boolean randomBoolean = random.nextBoolean();
	   if (randomBoolean) {
		   LOS=LOS+randomInteger;
	   }
	   else {
		   LOS = LOS-randomInteger;
	   }
	   return LOS;
	    
   }
   
   
   /**
		  epoc = xEpoc;
		  inmunesup= xInmunesup;
    * **/
	
	
}