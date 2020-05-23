package architecture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import architecture.HospitalA;

public class Testbed extends Thread {
	
	private int startTime;
	private int simulationRun;
	private ArrayList<PatientA> setPatients;
	private Utilities Ut;
	private HospitalA myHospital;
	
	public Testbed(int xstartTime, int simulationRun){
        this.startTime=xstartTime;
        this.simulationRun=simulationRun;        
        Ut=new Utilities();
	}
	
	public int getStartingTime(){
        return this.startTime;
    }
	
	public void start(){   // Start the FA and the SMAs
		
        // First we create the Front Agent
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
	  }	
	}catch (Exception ex){
        System.out.println(ex);
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
    		   setPatients.add(auxP);
    		   idPatient++;
       }
    	   return idPatient;
    }
    
    public void roundRobin(){
    	String text="";
		File file=new File("roundRobin.txt");
		PatientA xPatient;
		try(  PrintWriter out = new PrintWriter( file)  ){
			//out.println("[patientID, taskDemand, Start Time, Waiting_Time, Execution_Time, Accepted, WorkStationID]");
			out.println("patientID, Ventilator, estimatedLenghOfStay, arrivalDay, dischargeDay, Accepted, BedID, Dead");
		      for (int counter = 0; counter < setPatients.size(); counter++) { 		      
		        xPatient=(PatientA)setPatients.get(counter); 
		    	    System.out.println(xPatient.getId()); 	
		    		text=myHospital.assignBed(xPatient,1);//1 is for roundrobin
		    		out.println(text); 
		      }  
		for (int i=0;i<245;i++){
	    		  //virtual_machine[i]=new JobA(i, xCPU_Avaible, xMEM_Avaible, (float)xstart_time, (float)xexecution_time,(float)xdeadline);
	    		  //Here we need to subscribe the VMA to the FA. Review if the listVMA is better to be a VMA object		      		      	
	    		text=front_agent.receiveJob(virtual_machine[i], (long)virtual_machine[i].get_starting_time(),1);
	    		out.println(text);  
	    		//virtual_machine[i].printVMA();		  
	    	   }			
		    out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}    	    	    	

    }

	
	
}