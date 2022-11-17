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

public class UADController implements Initializable{

	@FXML
	private TableView<Accommodations> tableUAD;
	
	/*@FXML 
     private TableColumn<Accomodations, ImageView> IMAGEACC;*/
	
	 @FXML 
	 private TableColumn<Accommodations, String> LOCATIONUAD;
	
	@FXML 
     private	TableColumn<Accommodations, Integer> PRICEUAD;
	
	@FXML 
	private TableColumn<Accommodations, Integer> CAPACITYUAD;

	@FXML 
	private TableColumn<Accommodations, Integer> ACTIVATEUAD;
	
	

	@FXML 
	private Button PendingUAD;
	

	@FXML
	private Button DeleteUAD;


	
	@FXML
	private Label PickARowUAD;
	
	@FXML
	private Label emptyUAD;
	

	private ObservableList<Accommodations> listUAD=FXCollections.observableArrayList();

	
	public void clicktableUAD() {}
	
	
	private void DeleteAndRemove() {
		
		
		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "delete from projekat.Nekretnina  where ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, tableUAD.getSelectionModel().getSelectedItem().getID());
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
	
	
	public void DeleteClickUAD() {
		
		
		System.out.println("Radi55");
		if(!listUAD.isEmpty()) {
			System.out.println("Radi2");
			if(tableUAD.getSelectionModel().getSelectedItem()!=null && !(tableUAD.getSelectionModel().getSelectedItem().getLocationacc().isEmpty())){
			System.out.println("Radi");
		DeleteAndRemove();
		
		
	    tableUAD.getItems().remove(tableUAD.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARowUAD.setVisible(true);
			}
		}
		
		else {
			emptyUAD.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("Delete one by one");
		
		
	}
	
	

public void PendingClickUAD(){
	
	
	
	PendingUAD.setDisable(true);
	
	DeleteUAD.setVisible(true);
	System.out.println("Evo me PENDING UAD");

PreparedStatement ps=null;
ResultSet rs = null;
Connection con=null;
int i=0;
try {

	con=getConnection();
ps = con.prepareStatement("select Nekretnina.ID,Lokacija.Naziv, cijena, broj_kreveta,iznajmljivac.ID, Active FROM Nekretnina\n"
		+ "INNER JOIN Lokacija\n"
		+ "ON Nekretnina.ID_L=Lokacija.ID\n"
		+ "inner join iznajmljivac\n"
		+ "ON iznajmljivac.ID=Nekretnina.ID_I\n"
		+ "WHERE iznajmljivac.ID=? AND Active=1");
//	ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
ps.setInt(1, (int) CurrentUser.cuser.values().toArray()[0]);
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
			 listUAD.add(a);		
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
	
	
	LOCATIONUAD.setCellValueFactory(new PropertyValueFactory<Accommodations, String>("locationacc"));
	PRICEUAD.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("priceacc"));
	CAPACITYUAD.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("capacityacc"));
	ACTIVATEUAD.setCellValueFactory(new PropertyValueFactory<Accommodations, Integer>("activateacc"));
	 tableUAD.setItems(listUAD);
	
	
}



private Connection getConnection() throws SQLException {
	Connection conn;
	conn = DataBaseConnectivity.getInstance().getConnection();
	return conn;
}

}
