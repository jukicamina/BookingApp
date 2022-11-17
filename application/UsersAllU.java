package application;

public class UsersAllU {
	
	

		private String ime;
		private String prezime;
		private String password;
		private String kor_ime;
		 int activate;
		
		
		public UsersAllU(String k, String i, String p, String pp, int a){
			
			this.kor_ime=k;
			this.ime=i;
			this.prezime=p;
			this.password=pp;
			this.activate=a;
		}


		public String getIme() {
			return ime;
		}


		public void setIme(String ime) {
			this.ime = ime;
		}


		public String getPrezime() {
			return prezime;
		}


		public void setPrezime(String prezime) {
			this.prezime = prezime;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getKor_ime() {
			return kor_ime;
		}


		public void setKor_ime(String kor_ime) {
			this.kor_ime = kor_ime;
		}


		public int getActivate() {
			return activate;
		}


		public void setActivate(int activate) {
			this.activate = activate;
		}
		
	}
	
