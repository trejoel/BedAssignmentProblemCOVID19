package architecture;

import java.util.ArrayList;
import architecture.BedA;
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


public class HospitalA {
	private ArrayList<PatientA> setPatients;
	
	
	public HospitalA() {
		this.setPatients=new ArrayList<PatientA>();
	}
	
    public void generateBeds(int xVentilatorBed, int xNormalBed) {
    	
    	
    }

}
