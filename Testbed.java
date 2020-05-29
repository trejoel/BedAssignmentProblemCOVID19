package architecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import architecture.HospitalA;

public class Testbed extends Thread {
	
	private int simulationRun;
	private ArrayList<PatientA> setPatients; 
	private ArrayList<String> outputString; // This ArrayList of Strings can be changed in case a patient dies
	private Utilities Ut;
	private HospitalA myHospital;
	
	public Testbed(int simulationRun){
		setPatients=new ArrayList<PatientA>();
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
		 //System.out.println("chance2dead:"+setPatients.get(counter).isDead()+" days2release="+setPatients.get(counter).getDay2release());
	
		 /*	 if (setPatients.get(counter).isDead()) {
			 setPatients.get(counter).setDead(true);
		 }
		 else {
			 setPatients.get(counter).increaseDaysHospitalization();
			 setPatients.get(counter).decreaseDay2Release();
		 }*/
	 }	 
 }
 
    
    // Every day we generate a set of patients with random synthoms and features
    // We simulate 100 days according to mathematical models. Starting from 30 days before
    //peak of the curve to 30 days after the peak of the curve
    public int generatePatients(int numberDay, int startingID) {
       	//number of days
    	   PatientA auxP;
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
		   //System.out.println("number of patients:"+setPatients.size());
    		  // System.out.println(auxP.getDay2release());
    		   setPatients.add(auxP);
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
		         //out.println(myHospital.assignBed(xPatient, 1));
		         arrayOfStrings.add(myHospital.assignBed(xPatient, 1));
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
		         //out.println(myHospital.assignBed(xPatient, 1));
		         arrayOfStrings.add(myHospital.assignBed(xPatient, 2));
		      }  
			   System.out.println("Cierra el archivo");
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		return arrayOfStrings;

    }
    
	
	public static void main(String args[]){						
		Testbed Simulation=new Testbed(1);
		Simulation.start();		
		Simulation.roundRobin();
		Simulation.ImprovedroundRobin();
		System.out.println("Se fini!");				
	}

	
	
}