package architecture;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import architecture.BedA;
import architecture.PatientA;
import architecture.Utilities;

/** 
 *                          2020-MAY-24 
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
	private int dayOfSimulation;
	
	public HospitalA() {
		curBedN=0; //First assigned roundRound with ventilator
		curBedV=300; //First assigned roundRobin with no ventilator
		//this.setPatients=new ArrayList<PatientA>();
		this.setBedsV=new ArrayList<BedA>();
		this.setBedsN=new ArrayList<BedA>();
		this.dayOfSimulation=0;
	}
	
	public int getDayOfSimulation() {
	  return this.dayOfSimulation;
	}
	
	public void setDayOfSimulation(int xDayOfSimulation) {
		this.dayOfSimulation=xDayOfSimulation;
	}
	
	
	//30% of beds are with ventilator, the remaining are normal beds
    public void generateBeds(int xVentilatorBed, int xNormalBed) {
    	  //xVentilatorBed number of beds
    	  //xNormalBed number of bedss
      //ventilatorbeds from 0 to 299
    	 //Normal beds from 300 to 999
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
        public void makeAvailableBeds() {
	    	 for (int counter = 0; counter < setBedsV.size(); counter++) { 
	    		 if (!setBedsV.get(counter).isAvailable()) {
	    			 if (setBedsV.get(counter).getdays2Release()>0) {
		    			 System.out.println("Days2Release:"+setBedsV.get(counter).getdays2Release());
		    			 setBedsV.get(counter).decreaseDays2Release();
		    			 //Vamos a ver si lo decrementa
		    			 System.out.println("Days2Release:"+setBedsV.get(counter).getdays2Release());	    				 
	    			 }
	    			 else {  //This day the bed will be available
		    			 setBedsV.get(counter).setAvailable(true);
	    			 }

	    		 }
	    	 }
	    	 for (int counter = 0; counter < setBedsN.size(); counter++) { 
	    		 if (!setBedsN.get(counter).isAvailable()) {
	    			 if (setBedsN.get(counter).getdays2Release()>0) {
		    			 System.out.println("Days2Release:"+setBedsN.get(counter).getdays2Release());
		    			 setBedsN.get(counter).decreaseDays2Release();
		    			 //Vamos a ver si lo decrementa
		    			 System.out.println("Days2Release:"+setBedsN.get(counter).getdays2Release());	    				 
	    			 }
	    			 else {//This day the bed will be available
	    				 setBedsV.get(counter).setAvailable(true);
	    			 }

	    		 }
	    	 }
     }
     
    public int getNextAvailableBed(boolean isWithVentilator, int currentBed) {
      //ventilatorbeds from 0 to 299
  	  //Normal beds from 300 to 999
      int counter=0;
      int index=currentBed;
      if (isWithVentilator) {
    		 while (counter<300) {
    			 if (setBedsV.get(index).isAvailable()) {
    				 return index;
    			 }
    			 else {
    				 if (index<299) {
    					 index++;	 
    				 }
    				 else { //this is the last ventilator bed 299
    					 index=0;
    				 }
    				 counter++;	 
    			 }
    		 }
    		 if (counter==300) {
    			 return -1; //There is not available bed
    		 }
    	    }
    	  else {
     		 while (counter<700) {
    			 if (setBedsV.get(index).isAvailable()) {
    				 return index;
    			 }
    			 else {
    				 if (index<999) {
    					 index++;	 
    				 }
    				 else { //this is the last ventilator bed 299
    					 index=300;
    				 }
    				 counter++;	 
    			 }
    		    }
    		 if (counter==700) {
    			 return -1; //There is not available bed
    		 }
    	  }
    	  return index;
    }
   
    /**
     * Since patients contains its discharge day, when assigned, a bed also knows its availability
     * Program the availability
     * */
	public String assignBed(PatientA xPatient, int policy){
		//String text="Received Job:"+job.getId()+" at time:"+timeArrival+" estimation time="+job.get_execution_time();
		//IntegerString xAux=null;
		String text="";
		int x=0;
		//Update the day of arrival, and update availability of beds
		if (xPatient.getDayOfArrival()>this.dayOfSimulation) {
			this.makeAvailableBeds();
		}
		//xAux=new IntegerString(x,text);
		
		
	    switch (policy){  //1 Round robin; 2 Best fit; 3 First come first serve; 4 Round Robin Priority
	       case 1: text=roundRobin(xPatient);
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
	
	//initial curBed 0 for ventilator bed, 300 for normal bed
	//[patientID, type, estimatedLenghOfStay, arrivalDay, dischargeDay, Accepted, BedID, Death]
	public String roundRobin(PatientA xPatient){
        String text=xPatient.getId()+",";
        text=text+xPatient.getType()+",";
        text=text+xPatient.getLOS()+",";
        text=text+xPatient.getDayOfArrival()+",";
        text=text+xPatient  +",";
        int index=0;      //The problem is now how to compute the last assigned index
        if (xPatient.getType()) {//Require ventilator
        	   index=getNextAvailableBed(true, curBedV);
        	   if (index==-1) {
        		   System.out.println("Patient:"+xPatient.getId()+" Not assigned");
        	   }
        	   else {
        		   System.out.println("Bed "+setBedsV.get(index).getId()+" Occupancy is:"+setBedsV.get(index).isAvailable());
        		   setBedsV.get(index).receivePatient(xPatient);
        		   System.out.println("Bed "+setBedsV.get(index).getId()+" Occupancy is:"+setBedsV.get(index).isAvailable());
        	       this.curBedV=index;
        	   }
        }
        else {
        	   index=getNextAvailableBed(true,curBedN);
        	   if (index==-1) {
        		   System.out.println("Patient:"+xPatient.getId()+" Not assigned");
        	   }
        	   else {
        		   System.out.println("Bed "+setBedsN.get(index).getId()+" Occupancy is:"+setBedsN.get(index).isAvailable());
        		   setBedsN.get(index).receivePatient(xPatient);
        		   System.out.println("Bed "+setBedsN.get(index).getId()+" Occupancy is:"+setBedsN.get(index).isAvailable());
        		   this.curBedN=index;
        	   }
        }
      /*  BedA myBed=;
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
        }	*/                    
        return text;
}
    
    

}