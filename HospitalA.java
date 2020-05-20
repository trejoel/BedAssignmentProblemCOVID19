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
	
    public void generateBeds(int xVentilatorBed, int xNormalBed) {
    	  //xVentilatorBed number of beds
    	  //xNormalBed number of bedss
    }
    
    
    // Every day we generate a set of patients with random synthoms and features
    // We simulate 100 days according to mathematical models. Starting from 30 days before
    //peak of the curve to 30 days after the peak of the curve
    public void generatePatients() {
       	//number of days
    	   Utilities Ut=new Utilities();
    	   for (int i=1;i<100;i++) {
    		   int x=Ut.computeNumberPatients(i);
    		   for (int j=1;j<x;j++) {
    			   
    		   }
    	   }
    }
   
    public void assignPatient(PatientA xPatient) {
    	   
    }
    
    

}
