package application;

public class Reservations {
 
private int IDN;	
private int IDR;
	private String accomodation;
	private String userr;
	private String begin;
	private String end;
	private int done;
	
	public Reservations(int idr,int id,String Acc, String user, String b, String e,int d) {
		IDR=idr;
		IDN=id;
		accomodation=Acc;
		userr=user;
		begin=b;
	end=e;
	done=d;
				
	}
	
	



	public int getDone() {
		return done;
	}





	public void setDone(int done) {
		this.done = done;
	}





	public int getIDR() {
		return IDR;
	}





	public void setIDR(int iDR) {
		IDR = iDR;
	}





	public int getIDN() {
		return IDN;
	}





	public void setIDN(int iDN) {
		IDN = iDN;
	}





	public String getAccomodation() {
		return accomodation;
	}

	public void setAccomodation(String accomodation) {
		this.accomodation = accomodation;
	}

	public String getUserr() {
		return userr;
	}

	public void setUserr(String userr) {
		this.userr = userr;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	
	
	
	
	
}