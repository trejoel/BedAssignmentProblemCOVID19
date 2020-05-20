package architecture;

import java.io.Serializable;

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

public class PatientA implements Serializable {
	
	private int id;
	private int age;
	private int dayOfArrival;
	private boolean type; // 0 critical, 1 urgent (non-critical)
	private boolean diabetes;  // 1 for positive
	private boolean hyper; // 1 for positive
	private boolean epoc; // 1 for positive
	private boolean obesity; // 1 for positive
	private boolean inmunesup; // 1 for positive
	private boolean assigned;
	private boolean dead; // by default false
	private boolean hospital_discharge; //by default false
	
	public PatientA(int pId, int xAge, int xdayOfArrival) {
	  id=pId;
	  age=xAge;
	  type=false; //default
	  diabetes=false; //default
	  hyper = false; 
	  epoc = false;
	  obesity = false;
	  inmunesup= false;
	  assigned=false;
	  dead=false;
	  hospital_discharge=false;
	  this.dayOfArrival=xdayOfArrival;
	}
	
	public PatientA(int pId, int xAge, boolean xType, boolean xDiabetes, boolean xHyper, boolean xEpoc, boolean xObesity, boolean xInmunesup,int xdayOfArrival) {
		  id=pId;
		  age=xAge;
		  type=xType;
		  diabetes=xDiabetes;
		  hyper = xHyper;
		  epoc = xEpoc;
		  obesity = xObesity;
		  inmunesup= xInmunesup;
		  assigned=false;
		  dead=false;
		  hospital_discharge=false;
		  this.dayOfArrival=xdayOfArrival;
		}
	
	//Getters
	
	public int getDayOfArrival() {
		return this.dayOfArrival;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public boolean getType() {
		return this.type;
	}
	
	public boolean getDiabetes() {
		return this.diabetes;
	}
	
	public boolean getHyper() {
		return this.hyper;
	}
	
	public boolean getEpoc() {
		return this.epoc;
	}
	
	public boolean getObesity() {
		return this.obesity;
	}
	
	public boolean getInmuneSup() {
		return this.inmunesup;
	}
	
	public void getInmuneSup(boolean xValue) {
		this.inmunesup=xValue;
	}	
	
	
	
	//Setters
	
	public void setDiabetes(boolean xValue) {
		this.diabetes=xValue;
	}
	
	public void setHyper(boolean xValue) {
		this.hyper=xValue;
	}
	
	public void setEpoc(boolean xValue) {
		this.epoc=xValue;
	}
	
	
	public void setObesity(boolean xValue) {
		this.obesity=xValue;
	}
	
	public void setInmune(boolean xValue) {
		this.inmunesup=xValue;
	}
	

	
	//We need to compute the lenght of stay considering all the parameters of patient
	public int getLenghtOfStay() {
		int x=0;
		return x;
	}
	
	public void assigned(boolean isAssigned) {
		this.assigned=isAssigned;
	}


}
