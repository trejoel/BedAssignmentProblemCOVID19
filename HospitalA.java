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
		curBedV=0; //First assigned roundRobin with no ventilator
		//this.setPatients=new ArrayList<PatientA>();
		this.setBedsV=new ArrayList<BedA>();
		this.setBedsN=new ArrayList<BedA>();
		this.generateBeds();
		//this.traverseBeds();
		this.dayOfSimulation=0;
	}
	
	public int getDayOfSimulation() {
	  return this.dayOfSimulation;
	}
	
	public void setDayOfSimulation(int xDayOfSimulation) {
		this.dayOfSimulation=xDayOfSimulation;
	}
	
	public void traverseBeds() {
		for (int counter = 0; counter < setBedsV.size(); counter++) {
			System.out.println("Ventilator bed with ID:"+setBedsV.get(counter).getId());
		}
		for (int counter = 0; counter < setBedsN.size(); counter++) {
			System.out.println("Ventilator bed with ID:"+setBedsN.get(counter).getId());
		}
	}
	
	
	//30% of beds are with ventilator, the remaining are normal beds
    public void generateBeds() {
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
    	  //Is just the identifier
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
       // 	System.out.println("Avaible beds");
	    	 for (int counter = 0; counter < setBedsV.size(); counter++) { 
	    		 if (!setBedsV.get(counter).isAvailable()) {
	    			 //System.out.println("Bed"+setBedsV.get(counter).getId()+" remaining days "+setBedsV.get(counter).getdays2Release());
	    			 if (setBedsV.get(counter).getdays2Release()>0) {
		    			 setBedsV.get(counter).decreaseDays2Release();
		    			 if (setBedsV.get(counter).getdays2Release()==0) {
		    				 System.out.println("Se libero la cama "+setBedsV.get(counter).getId());
			    			 setBedsV.get(counter).relaseBed();
		    			 }
		    			 //System.out.println("Bed"+setBedsV.get(counter).getId()+" remaining days "+setBedsV.get(counter).getdays2Release());
		    			 //Vamos a ver si lo decrementa
	    			 }
	    			/* else {  //This day the bed will be available
	    				 System.out.println("Se libero la cama "+setBedsV.get(counter).getId());
		    			 setBedsV.get(counter).relaseBed();
	    			 }*/

	    		 }
	    	 }
	    	 for (int counter = 0; counter < setBedsN.size(); counter++) { 
	    		 if (!setBedsN.get(counter).isAvailable()) {
	    			 if (setBedsN.get(counter).getdays2Release()>0) {
		    			 setBedsN.get(counter).decreaseDays2Release();
		    			 if (setBedsN.get(counter).getdays2Release()==0) {
		    				 System.out.println("Se libero la cama "+setBedsN.get(counter).getId());
			    			 setBedsN.get(counter).relaseBed();
		    			 }
		    			 //Vamos a ver si lo decrementa
	    			 }
	    		/*	 else {//This day the bed will be available
	    				 System.out.println("Se libero la cama "+setBedsN.get(counter).getId());
	    				 setBedsN.get(counter).relaseBed();
	    			 }*/

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
    			 //Just 300 ventilators, not 1000. I will Fix this bug; then erase
    			 counter++;	
    			// System.out.println("Index ventilator:"+index+setBedsV.get(index).isAvailable());
    			// System.out.println(index);
    			 if (setBedsV.get(index).isAvailable()) {
    				// System.out.println("Return "+index);
    				 return index;
    			 }
    			 else {
    				 if (index<298) {
    					 index++;	 
    				 }
    				 else { //this is the last ventilator bed 299
    					 index=0;
    				 } 
    			 }
    		 }
    		 if (counter==300) {
    			 return -1; //There is not available bed
    		 }
    	    }
    	  else {
     		 while (counter<700) {
     		counter++;	
			// System.out.println("Index with no ventilator:"+index+setBedsV.get(index).isAvailable());
    			//System.out.println(index); 
     		if (setBedsN.get(index).isAvailable()) {
    				// System.out.println("Return "+index);
    				 return index;
    			 }
    			 else {
    				 if (index<698) {
    					 index++;	 
    				 }
    				 else { //this is the last ventilator bed 299
    					 index=0;
    				 } 
    			 }
    		    }
    		 if (counter==700) {
    			 return -1; //There is not available bed
    		 }
    	  }
      //System.out.println("Counter "+counter);
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
			//System.out.println("Index:"+this.curBedV+" kkkkk: "+setBedsV.get(curBedV).isAvailable());
			this.dayOfSimulation=xPatient.getDayOfArrival();
			this.makeAvailableBeds();
		}
		//xAux=new IntegerString(x,text);
		
		
	    switch (policy){  //1 Round robin; 2 Best fit; 3 First come first serve; 4 Round Robin Priority
	       case 1: //text=roundRobin(xPatient);
	       		   text=this.roundRobin(xPatient);
	       		  break;
	    /*   case 2:text=text+bestFit(job, this.curSMA,timeArrival, schedulable);
	       		  break;
	       case 3: text=text+firstComefirstServe(job, this.curSMA,timeArrival, schedulable);
	       		  break;
           case 4: text=text+priority(job, this.curSMA,timeArrival, schedulable);
	       		  break;	*/       
                   
	    }	
	    //System.out.println(text);
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
        text=text+xPatient.getDay2release()  +",";
        int index=0;//0 is a temporary value. We updated with the curBedV and curBedN     
        //System.out.println("Cama ventilador:"+curBedV+setBedsN.get(curBedV).isAvailable());
       // System.out.println("Cama normal:"+curBedN+setBedsN.get(curBedN).isAvailable());
        if (xPatient.getType()) {//Require ventilator
        	   //System.out.println("Bed:"+curBedV+setBedsV.get(curBedV).isAvailable());
        	   index=getNextAvailableBed(true, curBedV);
        	  // System.out.println("el indice:"+index);
        	   if (index==-1) {
        		   curBedV=0;
        		   text=text+"FALSE,";
        		   System.out.println("Patient:"+xPatient.getId()+" Not assigned");
        	   }
        	   else {
        		   text=text+"TRUE,";
        		   text=text+setBedsV.get(index).getId()+",";
        		  // System.out.println(xPatient.getId()+" "+xPatient.getType()+" Bed "+setBedsV.get(index).getId()+" Occupancy is:"+setBedsV.get(index).isAvailable());
        		   setBedsV.get(index).receivePatient(xPatient);
        		 //  System.out.println(xPatient.getId()+" "+xPatient.getType()+" Bed "+setBedsV.get(index).getId()+" Occupancy is:"+setBedsV.get(index).isAvailable());
        	       this.curBedV=index;
        	   }
        }
        else {
        	   index=getNextAvailableBed(false,curBedN);
    		   this.curBedN=index;
        	   //System.out.println("el indice:"+index);
        	   if (index==-1) {
        		   this.curBedN=0;
        		   text=text+"FALSE,";
        		  // System.out.println("Patient:"+xPatient.getId()+" Not assigned");
        	   }
        	   else {
        		   text=text+"TRUE,";
        		   text=text+setBedsN.get(index).getId()+",";
        		  // System.out.println(xPatient.getId()+" "+xPatient.getType()+"Bed "+setBedsN.get(index).getId()+" Occupancy is:"+setBedsN.get(index).isAvailable());
        		   setBedsN.get(index).receivePatient(xPatient);
        		 // System.out.println(xPatient.getId()+" "+xPatient.getType()+" Bed "+setBedsN.get(index).getId()+" Occupancy is:"+setBedsN.get(index).isAvailable());
        		   this.curBedN=index;  
        	   }
        } 
        if (index==-1) {
        	   xPatient.setDead(true);
        }
        text=text+xPatient.isDead()+"";
        return text;
}
	
	
	/*public String fakeRoundRobin(PatientA xPatient) {
		String text=xPatient.getId()+",";
		int index=0;
		//int cont=0;
		//while (cont<10) {
			index=this.curBedN;
			index=getNextAvailableBed(true, curBedV);
			this.curBedV=index;
			setBedsV.get(index).receivePatient(xPatient);
			//int age=setBedsV.get(index).getPatient().getId();
			int age=0;			
			System.out.println("Index:"+index+" available: "+setBedsV.get(index).isAvailable()+" days2ReleaseBed: "+setBedsV.get(index).getdays2Release()+" patient release: "+xPatient.getDay2release());
		//	cont++;
	//	}
		return text;
	}*/
    
    

}