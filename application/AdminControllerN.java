package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import java.sql.Blob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class AdminControllerN implements Initializable{

	
	
	@FXML
	private TableView<Accommodations> tableAcc;
	
	/*@FXML 
     private TableColumn<Accomodations, ImageView> IMAGEACC;*/
	
	 @FXML 
	 private TableColumn<Accommodations, String> LOCATIONACC;
	
	@FXML 
     private	TableColumn<Accommodations, Integer> PRICEACC;
	
	@FXML 
	private TableColumn<Accommodations, Integer> CAPACITYACC;

	@FXML 
	private TableColumn<Accommodations, Integer> ACTIVATEACC;
	
	

	@FXML
	private Button AcceptAllA;
	
	@FXML
	private Button DeclineAllA;
	@FXML 
	private Button PendingA;
	
	@FXML 
	private Button AcceptA;
	
	@FXML
	private Button DeclineA;
	
	@FXML
	private ImageView slikaovdje;
	
	@FXML
	private Button selectallU;
	
	@FXML
	private Label PickARowA;
	
	@FXML
	private Label emptyA;
	
	@FXML
	private Label idnekretnine;
	
	
	private ObservableList<Accommodations> listAcc=FXCollections.observableArrayList();
	
		
public void PendingClickA() {
		
	PendingA.setDisable(true);
	AcceptA.setVisible(true);
	DeclineA.setVisible(true);
	AcceptAllA.setVisible(true);
	DeclineAllA.setVisible(true);
	System.out.println("Evo me PENDING Nekretnine");

PreparedStatement ps=null;
ResultSet rs = null;
Connection con=null;
int i=0;
try {

	con=getConnection();
ps = con.prepareStatement("select Nekretnina.ID,Lokacija.Naziv, cijena, broj_kreveta, Active FROM Nekretnina\n"
			+ "INNER JOIN Lokacija\n"
			+ "ON Nekretnina.ID_L=Lokacija.ID\n"
			+ "WHERE Nekretnina.Active=0;");
//	ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
	rs = ps.executeQuery();
	
	while(rs.next())
	  {	
	 
		try {
			
			/*Blob blob=rs.getBlob(4);
			//String b2=blob.toString();
			InputStream inputStream=rs.getBinaryStream("slika");
			Image image=new Image(inputStream);*/
			// slikaovdje.setImage(image);
			
	
			
			
			Accommodations a= new Accommodations(rs.getInt("Nekretnina.ID"),rs.getString("Lokacija.Naziv"), rs.getInt("Nekretnina.cijena"), rs.getInt("Nekretnina.broj_kreveta"), rs.getInt("Nekretnina.Active"));
			 listAcc.add(a);		
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

public  void clicktableAcc() {
	

	
//idnekretnine.setText(tableAcc.getSelectionModel().getSelectedItem().getID());
	if(tableAcc.getSelectionModel().getSelectedItem()!=null) {
	int a=tableAcc.getSelectionModel().getSelectedItem().getID();
	
idnekretnine.setText("Accomodations");


PreparedStatement ps=null;
ResultSet rs = null;
Connection con=null;
int i=0;
try {

	con=getConnection();
ps = con.prepareStatement("select slika FROM projekat.Nekretnina WHERE ID=?");
ps.setInt(1, a);
//	ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
	rs = ps.executeQuery();
	
	while(rs.next())
	  {	
	 
		try {
			System.out.println("EVO ME U BLOBU");
			Blob blob=rs.getBlob(1);
			//String b2=blob.toString();
			InputStream inputStream=blob.getBinaryStream();
			Image image=new Image(inputStream);
		  slikaovdje.setImage(image);
			
	
			
			
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

	
	public void AcceptAllAcc() {
		
		if(listAcc.isEmpty()) {
			emptyA.setVisible(true);
		}else {
			
			for(Accommodations accomodation: listAcc) {
				SaveAndRemoveAcc(accomodation.getLocationacc(),accomodation.getPriceacc(),accomodation.getCapacityacc(),accomodation.getID());
				
			}
			
			tableAcc.getItems().clear();
			slikaovdje.setImage(null);
		}
		
	}
	
	public void DeclineAllAcc() {
		
		if(listAcc.isEmpty()) {
			emptyA.setVisible(true);
			}else {
				
	for(Accommodations accomodation: listAcc) {
		DeclineAndRemoveAcc(accomodation.getLocationacc(),accomodation.getPriceacc(),accomodation.getCapacityacc(),accomodation.getID());
		
	}
	tableAcc.getItems().clear();
	slikaovdje.setImage(null);
			
		}
		
		
	}
	
	public void DeclineClickA() {
		
		
		System.out.println("Radi55");
		if(!listAcc.isEmpty()) {
			System.out.println("Radi2");
			if(tableAcc.getSelectionModel().getSelectedItem()!=null && !(tableAcc.getSelectionModel().getSelectedItem().getLocationacc().isEmpty())){
			System.out.println("Radi");
		DeclineAndRemoveAcc(tableAcc.getSelectionModel().getSelectedItem().getLocationacc(),tableAcc.getSelectionModel().getSelectedItem().getPriceacc(),
				tableAcc.getSelectionModel().getSelectedItem().getCapacityacc(),tableAcc.getSelectionModel().getSelectedItem().getID());
		
		
	    tableAcc.getItems().remove(tableAcc.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARowA.setVisible(true);
			}
		}
		
		else {
			emptyA.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("DC one by one");
		
				
	}
	
	private void DeclineAndRemoveAcc(String name, int price, int capacity, int id) {

		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "delete from projekat.Nekretnina  where ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, id);
			 preparedStmt.executeUpdate();
	         System.out.println("Record is deleted successfully......");
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(null,preparedStmt, con);
		}	
		
	}
	
	public void AcceptClickA() {
		
		
		System.out.println("Accept accomodation");
		if(!listAcc.isEmpty()) {
			System.out.println("Radi2");
			if(tableAcc.getSelectionModel().getSelectedItem()!=null && !(tableAcc.getSelectionModel().getSelectedItem().getLocationacc().isEmpty())){
			System.out.println("Radi");
			
			SaveAndRemoveAcc(tableAcc.getSelectionModel().getSelectedItem().getLocationacc(),tableAcc.getSelectionModel().getSelectedItem().getPriceacc(),
					tableAcc.getSelectionModel().getSelectedItem().getCapacityacc(),tableAcc.getSelectionModel().getSelectedItem().getID());
		
	    tableAcc.getItems().remove(tableAcc.getSelectionModel().getSelectedItem());
	    slikaovdje.setImage(null);
		
		}else {
			System.out.println("Radi3");
				PickARowA.setVisible(true);
			}
		}
		
		else {
			emptyA.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
		
		
	System.out.println("AC one by one");
			
	}
	

	private void SaveAndRemoveAcc(String location, int price, int capacity, int ID) {
		
		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "update projekat.Nekretnina set Active=? where ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(2, ID);
			 preparedStmt.executeUpdate();
	         System.out.println("Record is updated successfully......");
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(null,preparedStmt, con);
		}	
		
		
	}
	
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		// IMAGEACC.setCellValueFactory(new PropertyValueFactory<Accomodations, ImageView>("imageacc"));
		LOCATIONACC.setCellValueFactory(new PropertyValueFactory<Accommodations, String>("locationacc"));
		PRICEACC.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("priceacc"));
		CAPACITYACC.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("capacityacc"));
		ACTIVATEACC.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("activateacc"));
		 tableAcc.setItems(listAcc);
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	
}