package architecture;

import java.util.ArrayList;
import java.util.Vector;

import architecture.PatientA;

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

public class BedA {
	
	private int id;
	private boolean type;//true is with ventilator
	private boolean available;
	private int occupiedDays;
	private int days2Release;
	private PatientA xPatient;
 
    public BedA(int xid) {
    	this.id=id;
    	this.type=false;
    	this.available=false;
    	this.occupiedDays=0;
    	this.days2Release=0;
    	xPatient=null;
    }
    
    
   //getters
    
    public int getId() {
    	return this.id;
    }
    
    public boolean getType() {
    	return this.type;
    }
    
    public boolean isAvailable() {
    	return this.available;
    }
    
    public int getOccupiedDays() {
    	return this.occupiedDays;
    }
    
    public int getdays2Release() {
    	return this.days2Release;
    }
    
    public PatientA getPatient() {
    	return this.xPatient;
    }
    
    //setters
    
    public void setType(boolean xType) {
    	this.type=xType;
    }
    
    public void setAvailable(boolean xAvailable) {
    	this.available=xAvailable;
    }
    
    public void setOccupiedDays(int xOccupiedDays) {
    	this.occupiedDays=xOccupiedDays;
    }
    
    public void setDays2Realease(int xDays2Release) {
    	this.days2Release=xDays2Release;
    }
    
    public void increaseOccupiedDays() {
    	this.occupiedDays=this.occupiedDays+1;
    }
    
    public void relaseBed() {
    	this.days2Release=0;
    	this.available=true;
    }
    
    public void receivePatient(PatientA xPatient) {
    	int xOccupiedDays=xPatient.getLOS();
    	if (xOccupiedDays>0) {
    		setAvailable(false);
    		this.xPatient=xPatient;
    	}
    	this.setDays2Realease(xOccupiedDays);
    	xPatient.assigned(true);
    }
    
    public void decreaseDays2Release() {
    	   days2Release--;
    	   if (days2Release==0) {
    		   this.setAvailable(true);
    	   }
    }
    
    public void removePatient(PatientA xPatient) {
    	xPatient.assigned(false);
    }

}