package architecture;

import java.util.ArrayList;
import java.util.Collections; 

public class sortedPatientList {
	private ArrayList<PatientA> setPatients; 
	
	public sortedPatientList(ArrayList<PatientA> xPatients) {
		this.setPatients=xPatients;
	}

	
	public ArrayList<PatientA> getSortedSetPatients(){
	    Collections.sort(setPatients);         

	    return setPatients;     
	}
}


