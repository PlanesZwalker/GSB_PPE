package Modeles;

import java.util.*;

public class RapportdeVisite {
	
	private int NumRapport;
	private String MotifVisite;
	private String Bilan;
	private Date DateRapport;
	
	public int getNumRapport() {
		return NumRapport;
	}
	public void setNumRapport(int numRapport) {
		NumRapport = numRapport;
	}
	public String getMotifVisite() {
		return MotifVisite;
	}
	public void setMotifVisite(String motifVisite) {
		MotifVisite = motifVisite;
	}
	public String getBilan() {
		return Bilan;
	}
	public void setBilan(String bilan) {
		Bilan = bilan;
	}
	public Date getDateRapport() {
		return DateRapport;
	}
	public void setDateRapport(Date dateRapport) {
		DateRapport = dateRapport;
	}

	
}
