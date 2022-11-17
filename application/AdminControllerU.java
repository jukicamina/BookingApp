package application;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminControllerU implements Initializable{

	
	@FXML
	private TableView<UsersAllU> tableU;
	
	@FXML 
     private TableColumn<UsersAllU, String> KOR_IMEU;
	
	 @FXML 
	 private TableColumn<UsersAllU, String> PREZIMEU;
	
	@FXML 
     private	TableColumn<UsersAllU, String> PASSWORDU;
	
	@FXML 
	private TableColumn<UsersAllU, String> IMEU;

	@FXML 
	private TableColumn<UsersAllU, String> ACTIVATEU;
	
	
	@FXML
	private AnchorPane containerMainItemsHOME;
	
	@FXML
	private AnchorPane containerMainItems;
	
	@FXML
	private Button AcceptAllU;
	
	@FXML
	private Button DeclineAllU;
	@FXML 
	private Button PendingU;
	
	@FXML 
	private Button AcceptU;
	
	@FXML
	private Button DeclineU;
	
	@FXML
	private Button selectallU;
	
	@FXML
	private Label PickARowU;
	
	@FXML
	private Label emptyU;
	

	

	
	ObservableList<UsersAllU> listU=FXCollections.observableArrayList();
int done=0;
	





private void DeclineAndRemove(String name, String lname, String uname, String p) {

	PreparedStatement preparedStmt = null;
	Connection con=null;
	System.out.println("Sara");
	try {
		con=getConnection();
		/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
		preparedStmt.executeUpdate();*/
		 String query = "delete from projekat.Korisnik where Kor_ime=? ";
		 preparedStmt = con.prepareStatement(query);
		 // preparedStmt.setInt(1, 1);
		 preparedStmt.setString(1, uname);
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

	private void SaveAndRemove(String name, String lname, String uname, String p) {
		
		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.Korisnik SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "update projekat.Korisnik set Activate=? where Kor_ime=? ";
			 preparedStmt = con.prepareStatement(query);
			 preparedStmt.setInt(1, 1);
			 preparedStmt.setString(2, uname);
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
	
	
	
	
	
	
	public void AcceptClickU() {
	/*	System.out.println("Ime je: "+ table.getSelectionModel().getSelectedItem().getIme());
		System.out.println("Prezime je: "+ table.getSelectionModel().getSelectedItem().getPrezime());
		System.out.println("Kor_ime je: "+ table.getSelectionModel().getSelectedItem().getKor_ime());
		System.out.println("Password je: "+ table.getSelectionModel().getSelectedItem().getPassword());*/
		
		
		System.out.println("Radi55");
		if(!listU.isEmpty()) {
			System.out.println("Radi2");
			if(tableU.getSelectionModel().getSelectedItem()!=null && !(tableU.getSelectionModel().getSelectedItem().getIme().isEmpty())){
			System.out.println("Radi");
			
			SaveAndRemove(tableU.getSelectionModel().getSelectedItem().getIme(),tableU.getSelectionModel().getSelectedItem().getPrezime(),
					tableU.getSelectionModel().getSelectedItem().getKor_ime(),tableU.getSelectionModel().getSelectedItem().getPassword());
		
	    tableU.getItems().remove(tableU.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARowU.setVisible(true);
			}
		}
		
		else {
			emptyU.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	
		

		
		
	System.out.println("AC one by one");
	}
	
	
	public void DeclineClickU() {
		System.out.println("Radi55");
		if(!listU.isEmpty()) {
			System.out.println("Radi2");
			if(tableU.getSelectionModel().getSelectedItem()!=null && !(tableU.getSelectionModel().getSelectedItem().getIme().isEmpty())){
			System.out.println("Radi");
		DeclineAndRemove(tableU.getSelectionModel().getSelectedItem().getIme(),tableU.getSelectionModel().getSelectedItem().getPrezime(),
				tableU.getSelectionModel().getSelectedItem().getKor_ime(),tableU.getSelectionModel().getSelectedItem().getPassword());
		
		
	    tableU.getItems().remove(tableU.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARowU.setVisible(true);
			}
		}
		
		else {
			emptyU.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("DC one by one");
		
		
	}
	
	
	public void AcceptAllU() {
		
		
	System.out.println("Evo me Accept All");

	if(listU.isEmpty()) {
		emptyU.setVisible(true);
		System.out.println("prazno");
		
			
		}
			else {
				for(UsersAllU user: listU) {
					  System.out.println("Evo me 5");
						SaveAndRemove(user.getIme(), user.getPrezime(), user.getKor_ime(), user.getPassword());}
			
				tableU.getItems().clear();
			}
	}
	
	
	public void DeclineAllU() {
		
		System.out.println("Evo me Decline All");

		if(listU.isEmpty()) {
			emptyU.setVisible(true);
			System.out.println("prazno");
			
				
			}
				else {
					for(UsersAllU user: listU) {
						  System.out.println("Evo me 7");
						  DeclineAndRemove(user.getIme(), user.getPrezime(), user.getKor_ime(), user.getPassword());
							
					}
					tableU.getItems().clear();
				}

	}
	
	
	
	
	public void Bezze() {
		/*System.out.println("Ime je: "+ table.getSelectionModel().getSelectedItem().getIme());
		System.out.println("Prezime je: "+ table.getSelectionModel().getSelectedItem().getPrezime());
		System.out.println("Kor_ime je: "+ table.getSelectionModel().getSelectedItem().getKor_ime());
		System.out.println("Password je: "+ table.getSelectionModel().getSelectedItem().getPassword());
		System.out.println("Bezze");*/
	}
	
	public void PendingClickU() {
		PendingU.setDisable(true);
		AcceptU.setVisible(true);
		DeclineU.setVisible(true);
		AcceptAllU.setVisible(true);
		DeclineAllU.setVisible(true);
		System.out.println("Evo me PENDINGvSARA");
	
	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
int i=0;
	try {
	
		con=getConnection();
		ps = con.prepareStatement("SELECT Kor_ime,Password,Ime, Prezime, Activate FROM projekat.Korisnik WHERE Activate=0");
		rs = ps.executeQuery();
		
		while(rs.next())
		  {	// System.out.println("Evo me"); 
		
		/*  System.out.println("Password je: "+rs.getString("Password"));
		  System.out.println("user name je: "+rs.getString("Kor_ime"));
		  System.out.println("Pass2 je: "+uPasswordLIN.getText());
		  System.out.println("user name2 je: "+uNameLIN.getText());
		  System.out.println("Evo me6"); */
		  
			System.out.println("EVO ME: "+rs.getString("Kor_ime"));
		UsersAllU a= new UsersAllU(rs.getString("Kor_ime"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Password"), 0);
		 listU.add(a);
			System.out.println("EVOOOO MEE!!!: "+(listU.get(i++)).getIme());
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
		System.out.println("Evo me tu sara");
		KOR_IMEU.setCellValueFactory(new PropertyValueFactory<UsersAllU, String>("kor_ime"));
		IMEU.setCellValueFactory(new PropertyValueFactory<UsersAllU, String>("ime"));
		PREZIMEU.setCellValueFactory(new PropertyValueFactory<UsersAllU, String>("prezime"));
		PASSWORDU.setCellValueFactory(new PropertyValueFactory<UsersAllU, String>("password"));
		ACTIVATEU.setCellValueFactory(new PropertyValueFactory<UsersAllU, String>("activate"));
		 tableU.setItems(listU);
		
	
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	
	
}