package architecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import architecture.HospitalA;

public class Testbed extends Thread {
	
	private int simulationRun;
	private ArrayList<PatientA> setPatients; 
	private ArrayList<PatientA> setSortedPatients;
	private ArrayList<PatientB> setInverseSortedPatients;
	private ArrayList<String> outputString; // This ArrayList of Strings can be changed in case a patient dies
	private Utilities Ut;
	private HospitalA myHospital;
	
	public Testbed(int simulationRun){
		setPatients=new ArrayList<PatientA>();
		setSortedPatients=new ArrayList<PatientA>();
		setInverseSortedPatients=new ArrayList<PatientB>();
		outputString=new ArrayList<String>();
        this.simulationRun=simulationRun;        
        Ut=new Utilities();
	}

	
	public void start(){   // Start the HA
		
        // First we create the Hospital
        myHospital=new HospitalA();
	
        this.run();
}
	
	/*Code added 2020 May 21th**/
	
    public void run(){    	
		/**
		 * During this execution, every time a new VMAs enter the simulation, the FA should designates a host SMA
		 * */	
    	    int delay;
		try{
		//100 days of simulation
			int idPatient=1;
		for (int i=1;i<101;i++){  
			//Each time a cycle, we traverse the set of available 
			//beds and decrease days2release
			idPatient=generatePatients(i,idPatient);
			updatePatientParameters(i);
			// Update the days2release of patients day 2 day. Also update the daysOfHospitalization
	  }	
	}catch (Exception ex){
        System.out.println(ex);
     }
		
  }
    
    /***
     * This function is called by Testbed and review for all the patients that
     * have assigned a bed. 
     * */   
 
 public void updatePatientParameters(int evaluationDay) {
	 for (int counter=0; counter<setPatients.size(); counter++) {
		 setPatients.get(counter).chance2Dead();
	 }	 
 }
 
    
    // Every day we generate a set of patients with random synthoms and features
    // We simulate 100 days according to mathematical models. Starting from 30 days before
    //peak of the curve to 30 days after the peak of the curve
    public int generatePatients(int numberDay, int startingID) {
       	//number of days
    	   PatientA auxP;
    	   PatientB auxBP;
    	   int xAge;
    	   boolean xType=false, xDiabetes=false, xHiper=false, xObesity=false, xEpoc=false, xInmune=false;
    	   int idPatient=startingID;
    	   int x=Ut.computeNumberPatients(numberDay);
    	   for (int j=1;j<x;j++) {
    		   xAge=Ut.generateRandomAge();
    		   auxP=new PatientA(idPatient,xAge,numberDay);
    		   auxP.setDiabetes(Ut.generateRandomDiabetes());
    		   auxP.setEpoc(Ut.generateRandomEpoc());
    		   auxP.setObesity(Ut.generateRandomObesity());
    		   auxP.setHyper(Ut.generateRandomHyper());
    		   auxP.setInmune(Ut.generateRandominmunesup());
    		   auxP.setLOS(Ut.estimateLenghtOfStay(xAge));
    		   auxP.setDay2Release(Ut.randomDay2Release());
    		   auxP.setType(Ut.generateRandomType());
    		   //Now for patientB 
    		   auxBP=new PatientB(idPatient,xAge,numberDay);
    		   auxBP.setDiabetes(auxP.getDiabetes());
    		   auxBP.setEpoc(auxP.getEpoc());
    		   auxBP.setObesity(auxP.getObesity());
    		   auxBP.setHyper(auxP.getHyper());
    		   auxBP.setInmune(auxP.getInmuneSup());
    		   auxBP.setLOS(auxP.getLOS());
    		   auxBP.setDay2Release(auxP.getDay2release());
    		   auxBP.setType(auxP.getType());
		   //System.out.println("number of patients:"+setPatients.size());
    		  // System.out.println(auxP.getDay2release());
    		   setPatients.add(auxP);
    		   setSortedPatients.add(auxP);
    		   setInverseSortedPatients.add(auxBP);
    		   idPatient++;
       }
    	   return idPatient;
    }
 
    public ArrayList<String> roundRobin(){
  	  String text="";
  	  File file=new File("RoundRobin.txt");
		PatientA xPatient;
		myHospital.resetValues();
		ArrayList<String> arrayOfStrings=new ArrayList<String>();
		int startingBed=0;
		try(  PrintWriter out = new PrintWriter( file)  ){
			//out.println("[patientID, taskDemand, Start Time, Waiting_Time, Execution_Time, Accepted, WorkStationID]");
			out.println("patientID, Ventilator, estimatedLenghOfStay, arrivalDay, days2Discharge, Accepted, BedID, Dead");
		       System.out.println("number of patients:"+setPatients.size());
			   for (int counter = 0; counter < setPatients.size(); counter++) {
		       //for (int counter = 0; counter < 45; counter++) {
		        xPatient=(PatientA)setPatients.get(counter);
		    		text=myHospital.assignBed(xPatient, 1);
		    		out.println(text);  
		         arrayOfStrings.add(text);
		      }  
			   System.out.println("Cierra el archivo");
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return arrayOfStrings;

  }
 
    
    public ArrayList<String> ImprovedroundRobin(){
    	  String text="";
    	  File file=new File("ImprovedRoundRobin.txt");
    	  myHospital.resetValues();
		PatientA xPatient;
		ArrayList<String> arrayOfStrings=new ArrayList<String>();
		int startingBed=0;
		try(  PrintWriter out = new PrintWriter( file)  ){
			//out.println("[patientID, taskDemand, Start Time, Waiting_Time, Execution_Time, Accepted, WorkStationID]");
			out.println("patientID, Ventilator, estimatedLenghOfStay, arrivalDay, days2Discharge, Accepted, BedID, Dead");
		       System.out.println("number of patients:"+setPatients.size());
			   for (int counter = 0; counter < setPatients.size(); counter++) {
		       //for (int counter = 0; counter < 45; counter++) {
		         xPatient=(PatientA)setPatients.get(counter);
		    		text=myHospital.assignBed(xPatient, 2);
		    		out.println(text);  
		        arrayOfStrings.add(text);
		      }  
			   System.out.println("Cierra el archivo");
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return arrayOfStrings;

    }
    
   	/**  	
   	 * 
   	 * public ArrayList<PatientA> sortSetPatients(ArrayList<PatientA> xPatients){
    		ArrayList<PatientA> setSortedPatients=new ArrayList<PatientA>();
    		int n=xPatients.size();
    		PatientA keyPatient;
    		for (int counter = 1; counter < n; counter++) {
    			keyPatient=xPatients.get(counter);
    			int i=counter-1;
    			while (i>-1 && xPatient)
    		}
    		return setSortedPatients;
    	}
    	
 
        int n = array.length;  
        for (int j = 1; j < n; j++) {  
            int key = array[j];  
            int i = j-1;  
            while ( (i > -1) && ( array [i] > key ) ) {  
                array [i+1] = array [i];  
                i--;  
            }  
            array[i+1] = key;  
        }  
    	 * */
    

    public ArrayList<String> ShorthestLOS(){
  	  String text="";
  	  File file=new File("ShorthestLOS.txt");
  	  myHospital.resetValues();
		PatientA xPatient;
		ArrayList<String> arrayOfStrings=new ArrayList<String>();
		ArrayList<PatientA> newSetPatients=new sortedPatientList(setSortedPatients).getSortedSetPatients();
		int startingBed=0;
		try(  PrintWriter out = new PrintWriter( file)  ){
			//out.println("[patientID, taskDemand, Start Time, Waiting_Time, Execution_Time, Accepted, WorkStationID]");
			out.println("patientID, Ventilator, estimatedLenghOfStay, arrivalDay, days2Discharge, Accepted, BedID, Dead");
		       System.out.println("number of patients:"+newSetPatients.size());
			    for (int counter = 0; counter < newSetPatients.size(); counter++) {
		         xPatient=(PatientA)newSetPatients.get(counter);
		    		 text=myHospital.assignBed(xPatient, 1);
		    		 out.println(text);  
		         arrayOfStrings.add(text);
		      }  
			   System.out.println("Cierra el archivo");
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return arrayOfStrings;

  }
    
 
    public ArrayList<String> LargestLOS(){
    	  String text="";
    	  File file=new File("LargestLOS.txt");
    	  myHospital.resetValues();
  		PatientB xPatient;
  		PatientA xPatientA;
  		ArrayList<String> arrayOfStrings=new ArrayList<String>();
  		//ArrayList<PatientB> newSetPatients=new sortedPatienBList(setInverseSortedPatients).getSortedSetPatients();
  		ArrayList<PatientB> newSetPatients=new sortedPatienBList(setInverseSortedPatients).getSortedSetPatients();
  		int startingBed=0;
  		try(  PrintWriter out = new PrintWriter( file)  ){
  			//out.println("[patientID, taskDemand, Start Time, Waiting_Time, Execution_Time, Accepted, WorkStationID]");
  			out.println("patientID, Ventilator, estimatedLenghOfStay, arrivalDay, days2Discharge, Accepted, BedID, Dead");
  		       System.out.println("number of patients:"+newSetPatients.size());
  			    for (int counter = 0; counter < newSetPatients.size(); counter++) {
  		         xPatient=(PatientB)newSetPatients.get(counter);
  		         xPatientA=convertB2A(xPatient);
  		    		 text=myHospital.assignBed(xPatientA, 1);
  		    		 out.println(text);  
  		         arrayOfStrings.add(text);
  		      }  
  			   System.out.println("Cierra el archivo");
  		    out.close();
  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} 
  		return arrayOfStrings;

    }
    
    public PatientA convertB2A(PatientB xPatient) {
    	  PatientA auxP;
    	  auxP=new PatientA(xPatient.getId(),xPatient.getAge(),xPatient.getDayOfArrival());
      auxP.setDiabetes(xPatient.getDiabetes());
	  auxP.setEpoc(xPatient.getEpoc());
	  auxP.setObesity(xPatient.getObesity());
	  auxP.setHyper(xPatient.getHyper());
	  auxP.setInmune(xPatient.getInmuneSup());
	  auxP.setLOS(xPatient.getLOS());
	  auxP.setDay2Release(xPatient.getDay2release());
	  auxP.setType(xPatient.getType());
    	  return auxP;
    }
    
	
	public static void main(String args[]){						
		Testbed Simulation=new Testbed(1);
		Simulation.start();		
		Simulation.roundRobin();
		Simulation.ImprovedroundRobin();
		Simulation.ShorthestLOS();
		Simulation.LargestLOS();
		System.out.println("Se fini!");				
	}

	
	
}