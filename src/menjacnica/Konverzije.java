package menjacnica;

import java.util.Date;

public class Konverzije {

	private Date datum;
	private String izValuta;
	private String uValuta;
	private double kurs;

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date date) {
		this.datum = date;
	}

	public String getIzValuta() {
		return izValuta;
	}

	public void setIzValuta(String izValuta) {
		this.izValuta = izValuta;
	}

	public String getuValuta() {
		return uValuta;
	}

	public void setuValuta(String uValuta) {
		this.uValuta = uValuta;
	}

	public double getKurs() {
		return kurs;
	}

	public void setKurs(double odnos) {
		this.kurs = odnos;
	}
}
