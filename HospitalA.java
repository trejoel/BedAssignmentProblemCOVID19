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
	
	//private ArrayList<PatientA> setPatients;
	private ArrayList<BedA> setBedsV; //There exists 300 available beds with ventilator
	private ArrayList<BedA> setBedsN; //There exists 700 available normal beds
	private int curBedN;
	private int curBedV;
	
	public HospitalA() {
		curBedN=1;
		curBedV=1;
		//this.setPatients=new ArrayList<PatientA>();
		this.setBedsV=new ArrayList<BedA>();
		this.setBedsN=new ArrayList<BedA>();
	}
	
	
	//30% of beds are with ventilator, the remaining are normal beds
    public void generateBeds(int xVentilatorBed, int xNormalBed) {
    	  //xVentilatorBed number of beds
    	  //xNormalBed number of bedss
    	  BedA auxBed;
    	  for (int iVentilator=0;iVentilator<300;iVentilator++) {
    		  auxBed=new BedA(iVentilator);
    		  auxBed.setType(true);
    		  setBedsV.add(auxBed);
    	  }
    	  for (int iNormal=300;iNormal<1000;iNormal++) {
    		  auxBed=new BedA(iNormal);
    		  setBedsN.add(auxBed);
    	  }
    }
    
    
    /***
     * This function is called by Testbed and review for all the occupied beds
     * that discharge its patient. In such a case the bed sets avaible = true
     * */
     public void renewBeds() {
    	 
     }
   
	public String assignBed(PatientA xPatient, int policy){
		//String text="Received Job:"+job.getId()+" at time:"+timeArrival+" estimation time="+job.get_execution_time();
		String text="";
		
		
	    switch (policy){  //1 Round robin; 2 Best fit; 3 First come first serve; 4 Round Robin Priority
	       case 1:text=text+roundRobin(xPatient);//stands for normal bed or ventilator bed
	       		  if (xPatient.getType()) { //Ventilator
	       			  curBedV++;
	       		  }
	       		  else {
	       			  curBedN++;
	       		  }
	       		  break;
	    /*   case 2:text=text+bestFit(job, this.curSMA,timeArrival, schedulable);
	       		  break;
	       case 3: text=text+firstComefirstServe(job, this.curSMA,timeArrival, schedulable);
	       		  break;
           case 4: text=text+priority(job, this.curSMA,timeArrival, schedulable);
	       		  break;	*/       
                   
	    }		
	    return text;
	}
	
	//curBedN current normal bed, curBedV current ventilator bed
	
	/**
	 * We assign a patient to the first available bed, as soon as patient request for 
	 * a bed. Without loss of generality a patient i migrating from a normal (ventilator) bed 
	 * to a ventilator (normal) bed is a new patient j.  
	 * 
	 * 
	 * **/
	
	public String roundRobin(PatientA xPatient){
        String text="";
        BedA xNode;
        float xAvailable=0;
        int index=i%20;
        xNode=listSMA.get(index);
        xAvailable=xNode.isAvailable(xTimeArrival);	    
        text=text+job.getId()+","+job.get_demand(0)+","+job.get_starting_time()+","+xAvailable;
        xAvailable=xAvailable+xNode.computeExecutionTime(job); //This is the estimated time to be return
        //System.out.println("Waiting Time:"+xAvailable);
        text=text+","+xAvailable;
        if (xAvailable<job.getDeadline() && schedulable)
        {
        	xNode.receiveVMA(job);
            text=text+",1,"+xNode.getID();
            //text="GREAT Waiting time:"+xAvailable;
            //System.out.println("Se asigna a la SMA:"+xNode.getID()+" hold time: "+xAvailable);
        }
        else
        {                                		
            //text="SORRY Waiting time:"+xAvailable;
            text=text+",0,"+xNode.getID();
            //System.out.println("No se pudo asignar el Job:"+job.getId()+" hold time:"+xAvailable);
        }	                    
        return text;
}
    
    

}