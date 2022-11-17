package application;

public class ReservationsUser {

	private int IDI;
	private int iznajmljivac_balans;
	private int CancFee;
	private int IDres;
	private String accuser;
	private String locationus;
	private int priceus;
	private String dofres;
	private int doneus;
	
	public ReservationsUser(int idi,int iznbal,int cf,int id,String aname, String locationres, int pricee, String dater, int d) {
		iznajmljivac_balans=iznbal;
		IDI=idi;
		CancFee=cf;
		IDres=id;
		accuser=aname;
		locationus=locationres;
		priceus=pricee;
		dofres=dater;
		doneus=d;
	}
	
	
	

	public int getIDI() {
		return IDI;
	}




	public void setIDI(int iDI) {
		IDI = iDI;
	}




	public int getIznajmljivac_balans() {
		return iznajmljivac_balans;
	}




	public void setIznajmljivac_balans(int iznajmljivac_balans) {
		this.iznajmljivac_balans = iznajmljivac_balans;
	}




	public int getDoneus() {
		return doneus;
	}



	public void setDoneus(int done) {
		this.doneus = done;
	}






	public int getCancFee() {
		return CancFee;
	}






	public void setCancFee(int cancFee) {
		CancFee = cancFee;
	}






	public int getIDres() {
		return IDres;
	}



	public void setIDres(int iDres) {
		IDres = iDres;
	}



	public String getAccuser() {
		return accuser;
	}



	public void setAccuser(String accuser) {
		this.accuser = accuser;
	}



	public String getLocationus() {
		return locationus;
	}



	public void setLocationus(String locationus) {
		this.locationus = locationus;
	}



	public int getPriceus() {
		return priceus;
	}



	public void setPriceus(int priceus) {
		this.priceus = priceus;
	}



	public String getDofres() {
		return dofres;
	}



	public void setDofres(String dofres) {
		this.dofres = dofres;
	}



	
	
	
	
	
	
}
