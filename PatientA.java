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
	private int LOS;
	private boolean type; // 0 non-ventilator, 1 ventilator (non-critical)
	private boolean diabetes;  // 1 for positive
	private boolean hyper; // 1 for positive
	private boolean epoc; // 1 for positive
	private boolean obesity; // 1 for positive
	private boolean inmunesup; // 1 for positive
	private boolean assigned;
	private int days2release; // random value close to the LOS
	private boolean dead; // by default false
	private int daysHospitalization;
	private Utilities Ut;
	
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
	  this.dayOfArrival=xdayOfArrival;
	  this.LOS=15;
	  this.days2release=15;
	  daysHospitalization=0;
	  Ut=new Utilities();
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
		  this.dayOfArrival=xdayOfArrival;
		  this.LOS=15;
		  this.days2release=15;
		  Ut=new Utilities();
		  daysHospitalization=0;
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
	
	public int getLOS() {
		return this.LOS;
	}
	
	public int getDay2release() {
		return this.days2release;
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	
	//Setters
	
	public void setType(boolean xValue) {
		this.type=xValue;
	}
	
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
	
	public void setLOS(int xLOS) {
		this.LOS=xLOS;
	}
	
	public void assigned(boolean isAssigned) {
		this.assigned=isAssigned;
	}
	
	public void setDay2Release(int xDay2Release) {
		this.days2release=xDay2Release;
	}
	
	public void setDead(boolean xValue) {
		this.dead=xValue;
	}
	
	//Auxiliar functions
	
	public void increaseDaysHospitalization() {
		this.daysHospitalization++;
	}
	
	public void decreaseDay2Release() {
		if (this.dead) {
			days2release=0;
		}
		if (days2release>0)
		   days2release--;
	}
	
	public void chance2Dead() {
		//   public boolean isDead(int age, boolean isDiabetes, boolean isHyper, boolean isObese, boolean isEpoc, int daysHospitalization) {
		this.dead=Ut.isDead(age,diabetes,hyper, obesity, epoc, this.daysHospitalization);
	/*    if (this.isDead()) {
	    	  this.setDay2Release(0);
	    }
	    else {
	    	  if (this.getDay2release()>0);
	    	     {
	    	    	   this.setDay2Release(this.getDay2release()-1);
	    	     }
	    }*/
	}


}