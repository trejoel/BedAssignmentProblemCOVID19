package architecture;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import architecture.BedA;
import architecture.PatientA;
import architecture.Utilities;

/** 
 *                          2020-MAY-17 
 * Based on submitted paper "A multi-agent greedy heuristic algorithm for the bedassignment problem
 *  for COVID-19 patients" to IEEE Latin Am Trans may 2020
 * We assume there exists 1000 available beds (700 normal, 300 with ventilator). 
 * We will create the set of patients following a normal distribution during simulation. Eventually, 
 * there exists much more than 1000 requests 
 * 
 * We use threads to simulate the concurrent enter of patients
 * */


public class HospitalA {
	
	private ArrayList<PatientA> setPatients;
	private ArrayList<BedA> setBeds; //There exists 1000 available beds
	
	
	public HospitalA() {
		this.setPatients=new ArrayList<PatientA>();
		this.setBeds=new ArrayList<BedA>();
	}
	
	
	//30% of beds are with ventilator, the remaining are normal beds
    public void generateBeds(int xVentilatorBed, int xNormalBed) {
    	  //xVentilatorBed number of beds
    	  //xNormalBed number of bedss
    	  BedA auxBed;
    	  for (int iVentilator=0;iVentilator<300;iVentilator++) {
    		  auxBed=new BedA(iVentilator);
    		  auxBed.setType(true);
    		  setBeds.add(auxBed);
    	  }
    	  for (int iNormal=300;iNormal<1000;iNormal++) {
    		  auxBed=new BedA(iNormal);
    		  setBeds.add(auxBed);
    	  }
    }
    
    
    // Every day we generate a set of patients with random synthoms and features
    // We simulate 100 days according to mathematical models. Starting from 30 days before
    //peak of the curve to 30 days after the peak of the curve
    public void generatePatients() {
       	//number of days
    	   Utilities Ut=new Utilities();
    	   PatientA auxP;
    	   int xAge;
    	   boolean xType=false, xDiabetes=false, xHiper=false, xObesity=false, xEpoc=false, xInmune=false;
    	   int idPatient=1;
    	   for (int i=1;i<100;i++) {
    		   int x=Ut.computeNumberPatients(i);
    		   for (int j=1;j<x;j++) {
    			   xAge=Ut.generateRandomAge();
    			   auxP=new PatientA(idPatient,xAge,i);
    			   auxP.setDiabetes(xDiabetes);
    			   auxP.setEpoc(xEpoc);
    			   auxP.setObesity(xObesity);
    			   auxP.setHyper(xHiper);
    			   auxP.setInmune(xInmune);
    			   setPatients.add(auxP);
    		   }
    	   }
    }
   
	public String assignBed(PatientA xPatient, int policy){
		//String text="Received Job:"+job.getId()+" at time:"+timeArrival+" estimation time="+job.get_execution_time();
		String text="";
		
		boolean schedulable=true;
		if (job.get_execution_time()>2000){
			//schedulable=false;
			//System.out.println("The job "+job.getId()+" is not classified as ligh customer due to its ex time="+job.get_execution_time());		
		}
		
	    switch (policy){  //1 Round robin; 2 Best fit; 3 First come first serve; 4 Round Robin Priority
	       case 1:text=text+roundRobin(job, this.curSMA,timeArrival,  schedulable);
	       		  break;
	       case 2:text=text+bestFit(job, this.curSMA,timeArrival, schedulable);
	       		  break;
	       case 3: text=text+firstComefirstServe(job, this.curSMA,timeArrival, schedulable);
	       		  break;
           case 4: text=text+priority(job, this.curSMA,timeArrival, schedulable);
	       		  break;	       
                   
	    }		
	    curSMA++;
	    return text;
	}
    
    

}
