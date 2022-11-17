package application;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class UserReservationController implements Initializable{
	
	@FXML
    private AnchorPane containerMainItemsHOMEUSERRES;
	
	
	
	@FXML
	private AnchorPane containerMainItemsUser;
	
	@FXML
	private TableView<ReservationsUser> tableURes;
	
	/*@FXML 
     private TableColumn<Accomodations, ImageView> IMAGEACC;*/
	
	 @FXML 
	 private TableColumn<ReservationsUser, String> ACCUSER;
	
	@FXML 
     private	TableColumn<ReservationsUser, String> LOCATIONUS;
	
	@FXML 
	private TableColumn<ReservationsUser, Integer> PRICEUS;

	@FXML 
	private TableColumn<ReservationsUser, String> DOFRES;
	@FXML 
	private TableColumn<ReservationsUser, Integer> DONEUS;
	
	
	@FXML
	private Button checkFunds;
	
	
	
	@FXML
	private Button resDone;
	@FXML 
	private Button cancelResUS;
	
	
	
	@FXML
	private Button PendingUS;
	
	
	@FXML
	private Button selectallU;
	
	@FXML
	private Label PickARowUS;
	
	@FXML
	private Label emptyUS;
	
	@FXML
	private Label MyFunds;
	
	@FXML
	private Label noMoney;
	
	@FXML
	private ImageView slikaovdje2;
	

	private ObservableList<ReservationsUser> listUR=FXCollections.observableArrayList();
	private int balansKorisnik; 
	
	

	
	
	
	public void tableUResClicked() {
		
		
		System.out.println("Table URES Clicked");
		
		if(tableURes.getSelectionModel().getSelectedItem()!=null) {
			int a=tableURes.getSelectionModel().getSelectedItem().getIDres();
			System.out.println("Table URES Clicked2:"+tableURes.getSelectionModel().getSelectedItem().getIDres());
		//idnekretnine.setText("Accomodations");


		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection con=null;
		int i=0;
		try {

			con=getConnection();
		ps = con.prepareStatement("select Nekretnina.slika from Nekretnina\n"
				+ "INNER JOIN Rezervacija \n"
				+ "ON Nekretnina.ID=Rezervacija.ID_N\n"
				+ "WHERE Rezervacija.ID=?;");
		ps.setInt(1, a);
//			ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
			rs = ps.executeQuery();
			
			while(rs.next())
			  {	
			 
				try {
					System.out.println("EVO ME U BLOBU URES");
					Blob blob=rs.getBlob(1);
					//String b2=blob.toString();
					InputStream inputStream=blob.getBinaryStream();
					Image image=new Image(inputStream);
				  slikaovdje2.setImage(image);
					
			
					
					
				//	Accomodations a= new Accomodations(rs.getInt("Nekretnina.ID"),rs.getString("Lokacija.Naziv"), rs.getInt("Nekretnina.cijena"), rs.getInt("Nekretnina.broj_kreveta"), rs.getInt("Nekretnina.Active"));
					// list.add(a);		
						  }	catch(SQLException e) {
							  System.out.println(e.getMessage());
						  }
			
			

			  }
			
			rs.close();	
			ps.close();
			con.close();
		 }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(rs,ps, con);
		}
			}
			else {
				
			}
		
		
		
		
		
	}
	
	
	public void checkFundsClick() {
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection con=null;
		
		try {
			
		
			con=getConnection();
			ps = con.prepareStatement("SELECT Korisnik.Balance FROM Korisnik where Korisnik.ID=?");
			ps.setInt(1,(int) CurrentUser.cuser.values().toArray()[0] );
			rs = ps.executeQuery();
			
			while(rs.next())
			  {	
			balansKorisnik=rs.getInt("Korisnik.Balance");
				MyFunds.setText("My funds: "+balansKorisnik+"KM");
				MyFunds.setVisible(true);
			
			  }
	
			rs.close();	
			ps.close();
			con.close();
		 }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(rs,ps, con);
		}
				
		}
		
	private void Done() {
		
		
		
		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "UPDATE projekat.Rezervacija SET Reg=1 WHERE ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, tableURes.getSelectionModel().getSelectedItem().getIDres());
			 preparedStmt.executeUpdate();
	         System.out.println("Reservation is done successfully......");
	      
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(null,preparedStmt, con);
		   
		}	
		
	}
	
	public void resDoneClick() {
		
		if(!listUR.isEmpty()) {
			System.out.println("Radi2");
			if(tableURes.getSelectionModel().getSelectedItem()!=null && !(tableURes.getSelectionModel().getSelectedItem().getDofres().isEmpty())){
				
				System.out.println("Radi");
		
				
				Done();
		
	
			}
		
	    
		else {
			System.out.println("Radi3");
				PickARowUS.setVisible(true);
			}
			}
		
		else {
			emptyUS.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("Done one by one");

		
	}
	

	private int DeclineAndRemove() {
		int ab;
		checkFundsClick();
		MyFunds.setVisible(false);
		if(balansKorisnik-tableURes.getSelectionModel().getSelectedItem().getCancFee()>=0) {
		PreparedStatement preparedStmt = null;
		Connection con=null;
		ab=1;
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "UPDATE projekat.Rezervacija SET Active=0 WHERE ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, tableURes.getSelectionModel().getSelectedItem().getIDres());
			 preparedStmt.executeUpdate();
	         System.out.println("Reservation is cancelled successfully......");
			preparedStmt.close();
			
			query = "update projekat.Korisnik \n"
					+ "inner join Rezervacija\n"
					+ "ON Rezervacija.ID_K=Korisnik.ID\n"
					+ "inner join Nekretnina \n"
					+ "On Rezervacija.ID_N=Nekretnina.ID\n"
					+ "\n"
					+ "set Balance=? WHERE Korisnik.ID=?; ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, balansKorisnik-tableURes.getSelectionModel().getSelectedItem().getCancFee());
			 preparedStmt.setInt(2, (int) CurrentUser.cuser.values().toArray()[0]);
			 preparedStmt.executeUpdate();
	         System.out.println("Reservation is cancelled successfully......");
			preparedStmt.close();
			

			query = "update projekat.iznajmljivac \n"
					+ "set Balance=? WHERE iznajmljivac.ID=?; ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, tableURes.getSelectionModel().getSelectedItem().getIznajmljivac_balans()+tableURes.getSelectionModel().getSelectedItem().getCancFee());
			 preparedStmt.setInt(2, tableURes.getSelectionModel().getSelectedItem().getIDI());
			 preparedStmt.executeUpdate();
	         System.out.println("Reservation is cancelled successfully......");
			preparedStmt.close();
			
			
			
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(null,preparedStmt, con);
		}
		}
		else {
			ab=0;
			noMoney.setVisible(true);
			
		}
		return ab;
		
	}
	
	public void cancelResUSClick() {
		

		if(!listUR.isEmpty()) {
			System.out.println("Radi2");
			if(tableURes.getSelectionModel().getSelectedItem()!=null && !(tableURes.getSelectionModel().getSelectedItem().getDofres().isEmpty())){
				if(tableURes.getSelectionModel().getSelectedItem().getDoneus()==0) {
				System.out.println("Radi");
		int d=DeclineAndRemove();
		
		if(d==1) {
	    tableURes.getItems().remove(tableURes.getSelectionModel().getSelectedItem());
		}
	    
		else {
			System.out.println("Radi3");
				PickARowUS.setVisible(true);
			}
			}
				else {
					
					System.out.println("Cant cancel reservation that is done");
				}	
			
			
			}}
		
		else {
			emptyUS.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("Cancel one by one");


		
		
		
	}

	
	
	
	public void PendingUSClick() {
		
		
		PendingUS.setDisable(true);
		resDone.setVisible(true);
		cancelResUS.setVisible(true);
		PendingUS.setVisible(true);
	//	DeclineAllR.setVisible(true);
		System.out.println("Evo me PENDING User Rezervacije");

	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
	int i=0;
	int x=(int) CurrentUser.cuser.values().toArray()[0];
	try {

		con=getConnection();
	ps = con.prepareStatement("select iznajmljivac.ID, iznajmljivac.Balance,Rezervacija.ID, Nekretnina.Accomodation_name, Lokacija.Naziv, Nekretnina.cijena, Rezervacija.pocetak,Nekretnina.CF,Rezervacija.Reg, Rezervacija.Active FROM Rezervacija\n"
			+ "			Inner join Nekretnina \n"
			+ "			on Nekretnina.ID=Rezervacija.ID_N\n"
			+ "			INNER JOIN Lokacija\n"
			+ "			ON Lokacija.ID=Nekretnina.ID_L\n"
			+ "            INNER JOIN iznajmljivac\n"
			+ "            ON iznajmljivac.ID=Nekretnina.ID_I"
			+ "			WHERE Rezervacija.ID_K=? AND Rezervacija.Active=1;");

	ps.setInt(1, x);	
	rs = ps.executeQuery();
		
		while(rs.next())
		  {	
		 
			try {
				
				
				ReservationsUser a= new ReservationsUser(rs.getInt("iznajmljivac.ID"),rs.getInt("iznajmljivac.Balance"),rs.getInt("Nekretnina.CF"),rs.getInt("Rezervacija.ID"),rs.getString("Accomodation_name"),rs.getString("Lokacija.Naziv"),rs.getInt("Nekretnina.cijena"), rs.getString("Rezervacija.pocetak"),rs.getInt("Rezervacija.Reg"));
				 listUR.add(a);		
					  }	catch(SQLException e) {
						  System.out.println(e.getMessage());
					  }
		
		

		  }
		
		rs.close();	
		ps.close();
		con.close();
	 }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
	    DataBaseConnectivity.closeAll(rs,ps, con);
	}}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		ACCUSER.setCellValueFactory(new PropertyValueFactory<ReservationsUser, String>("accuser"));
		LOCATIONUS.setCellValueFactory(new PropertyValueFactory<ReservationsUser, String>("locationus"));
		PRICEUS.setCellValueFactory(new PropertyValueFactory<ReservationsUser, Integer>("priceus"));
		DOFRES.setCellValueFactory(new PropertyValueFactory<ReservationsUser, String>("dofres"));
		DONEUS.setCellValueFactory(new PropertyValueFactory<ReservationsUser, Integer>("doneus"));
		 tableURes.setItems(listUR);
		
		
		
	}
	
	

}
