package architecture;

import java.util.ArrayList;
import java.util.Collections; 

public class sortedPatienBList {
	private ArrayList<PatientB> setPatients; 
	
	public sortedPatienBList(ArrayList<PatientB> xPatients) {
		this.setPatients=xPatients;
	}
	
	
	public ArrayList<PatientB> setPatients(){
		return this.setPatients;
	}

	
	public ArrayList<PatientB> getSortedSetPatients(){
	    Collections.sort(setPatients);         

	    return setPatients;     
	}
}


