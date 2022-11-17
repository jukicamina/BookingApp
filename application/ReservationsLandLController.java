package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class ReservationsLandLController implements Initializable{
	
	
	@FXML
	private TableView<Reservations> tableR;
	
	/*@FXML 
     private TableColumn<Accomodations, ImageView> IMAGEACC;*/
	
	 @FXML 
	 private TableColumn<Reservations, String> ACCOMODATION;
	
	@FXML 
     private	TableColumn<Reservations, String> USERR;
	
	@FXML 
	private TableColumn<Reservations, String> BEGIN;

	@FXML 
	private TableColumn<Reservations, String> END;
	@FXML 
	private TableColumn<Reservations, Integer> DONE;
	
	
	@FXML
	private Button AcceptAllR;
	
	
	
	@FXML
	private Button DeclineAllR;
	@FXML 
	private Button PendingR;
	
	@FXML 
	private Button AcceptR;
	
	@FXML
	private Button DeclineR;
	
	
	@FXML
	private Button selectallU;
	
	@FXML
	private Label PickARowR;
	
	@FXML
	private Label emptyR;
	
	@FXML
	private Label idnekretnineR;
	
	
	private ObservableList<Reservations> listR=FXCollections.observableArrayList();

	
	
	private void DeclineAndRemove() {

		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "UPDATE projekat.Rezervacija SET Active=0 WHERE ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, tableR.getSelectionModel().getSelectedItem().getIDR());
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
	
	
	
	public void DeclineClickR() {
		
		
		
		if(!listR.isEmpty()) {
			System.out.println("Radi2");
			if(tableR.getSelectionModel().getSelectedItem()!=null && !(tableR.getSelectionModel().getSelectedItem().getUserr().isEmpty())){
				if(tableR.getSelectionModel().getSelectedItem().getDone()==0) {
				System.out.println("Radi");
		DeclineAndRemove();
		
	    tableR.getItems().remove(tableR.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Cant cancel reservation is done");
		}}
				else {
			System.out.println("Radi3");
				PickARowR.setVisible(true);
			}
		}
		
		else {
			emptyR.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("Cancel one by one");
		}
	
		
	
	
	

	
	
	
	
	
	public void tableRClick() {}
	
	private int a;
	
	
	
	public void PendingClickR() {
		
		PendingR.setDisable(true);
		DeclineR.setVisible(true);
	//	DeclineAllR.setVisible(true);
		System.out.println("Evo me PENDING Rezervacije");

	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
	int i=0;
	int x=(int) CurrentUser.cuser.values().toArray()[0];
	try {

		con=getConnection();
	ps = con.prepareStatement("select Rezervacija.ID,Rezervacija.ID_N,Nekretnina.Accomodation_name, Korisnik.Kor_ime, Rezervacija.pocetak, Rezervacija.kraj, Rezervacija.Reg, Rezervacija.Active\n"
			+ "from Rezervacija\n"
			+ "inner join Korisnik\n"
			+ "ON Rezervacija.ID_K=Korisnik.ID\n"
			+ "INNER JOIN Nekretnina\n"
			+ "ON Rezervacija.ID_N=Nekretnina.ID\n"
			+ "INNER JOIN iznajmljivac\n"
			+ "ON Nekretnina.ID_I=iznajmljivac.ID\n"
			+ "where iznajmljivac.ID=? AND Rezervacija.Active=1;");
//		ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
	ps.setInt(1, x);	
	rs = ps.executeQuery();
		
		while(rs.next())
		  {	
		 
			try {
				
				/*Blob blob=rs.getBlob(4);
				//String b2=blob.toString();
				InputStream inputStream=rs.getBinaryStream("slika");
				Image image=new Image(inputStream);*/
				// slikaovdje.setImage(image);
				
		
				
				
				Reservations a= new Reservations(rs.getInt("ID"),rs.getInt("ID_N"),rs.getString("Nekretnina.Accomodation_name"),rs.getString("Korisnik.Kor_ime"), rs.getString("Rezervacija.pocetak"), rs.getString("Rezervacija.kraj"),rs.getInt("Rezervacija.Reg"));
				 listR.add(a);		
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
	
	
	
	
	
	
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		ACCOMODATION.setCellValueFactory(new PropertyValueFactory<Reservations, String>("accomodation"));
		USERR.setCellValueFactory(new PropertyValueFactory<Reservations, String>("userr"));
		BEGIN.setCellValueFactory(new PropertyValueFactory<Reservations, String>("begin"));
		END.setCellValueFactory(new PropertyValueFactory<Reservations, String>("end"));
		DONE.setCellValueFactory(new PropertyValueFactory<Reservations, Integer>("done"));
		 tableR.setItems(listR);
		
		
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	

}