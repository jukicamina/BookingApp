package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController implements Initializable{

	
	@FXML
	private TableView<UsersAll> table;
	
	@FXML 
     private TableColumn<UsersAll, String> KOR_IME;
	
	 @FXML 
	 private TableColumn<UsersAll, String> PREZIME;
	
	@FXML 
     private	TableColumn<UsersAll, String> PASSWORD;
	
	@FXML 
	private TableColumn<UsersAll, String> IME;

	@FXML 
	private TableColumn<UsersAll, String> ACTIVATE;
	
	
	@FXML
	private AnchorPane containerMainItemsADMINHOME;
	
	@FXML
	private AnchorPane containerMainItemsadmin;
	
	@FXML
	private Button AcceptAll;
	
	@FXML
	private Button DeclineAll;
	@FXML 
	private Button Pending;
	
	@FXML 
	private Button Accept;
	
	@FXML
	private Button Decline;
	
	@FXML
	private Button bLOadmin;
	
	@FXML
	private Button bUadmin;
	
	@FXML 
	private Button bHome;
	@FXML
	private Pane Label;
	@FXML 
	private Label letterB;
	@FXML
	private Button bLLadmin;
	
	@FXML
	private Button bACadmin;
	
	
	@FXML
	private Button selectall;
	
	@FXML
	private Label PickARow;
	
	@FXML
	private Label empty;
	
	@FXML
	private Button U;
	
	ObservableList<UsersAll> list=FXCollections.observableArrayList();
int done=0;
	





public void toUser() {
	
	
	try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("adminU.fxml"));
		containerMainItemsadmin.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}

public void toLandLord() {
	
try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("okviradmin.fxml"));
		containerMainItemsADMINHOME.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	
}

public void toAccomodations() {
	
try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("adminN.fxml"));
		containerMainItemsadmin.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}

public void adminLogOutClick() {
	
	
	CurrentUser.cuser.clear();
	System.out.println("Size of the map is: "+CurrentUser.cuser.size());
try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("sample2.fxml"));
		containerMainItemsADMINHOME.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


private void DeclineAndRemove(String name, String lname, String uname, String p) {

	PreparedStatement preparedStmt = null;
	Connection con=null;
	
	try {
		con=getConnection();
		/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
		preparedStmt.executeUpdate();*/
		 String query = "delete from projekat.iznajmljivac  where Kor_ime=? ";
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
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "update projekat.iznajmljivac set Activate=? where Kor_ime=? ";
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
	
	
	
	
	
	
	public void AcceptClick() {
	/*	System.out.println("Ime je: "+ table.getSelectionModel().getSelectedItem().getIme());
		System.out.println("Prezime je: "+ table.getSelectionModel().getSelectedItem().getPrezime());
		System.out.println("Kor_ime je: "+ table.getSelectionModel().getSelectedItem().getKor_ime());
		System.out.println("Password je: "+ table.getSelectionModel().getSelectedItem().getPassword());*/
		
		
		System.out.println("Radi55");
		if(!list.isEmpty()) {
			System.out.println("Radi2");
			if(table.getSelectionModel().getSelectedItem()!=null && !(table.getSelectionModel().getSelectedItem().getIme().isEmpty())){
			System.out.println("Radi");
			
			SaveAndRemove(table.getSelectionModel().getSelectedItem().getIme(),table.getSelectionModel().getSelectedItem().getPrezime(),
					table.getSelectionModel().getSelectedItem().getKor_ime(),table.getSelectionModel().getSelectedItem().getPassword());
		
	    table.getItems().remove(table.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARow.setVisible(true);
			}
		}
		
		else {
			empty.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	
		

		
		
	System.out.println("AC one by one");
	}
	
	
	public void DeclineClick() {
		System.out.println("Radi55");
		if(!list.isEmpty()) {
			System.out.println("Radi2");
			if(table.getSelectionModel().getSelectedItem()!=null && !(table.getSelectionModel().getSelectedItem().getIme().isEmpty())){
			System.out.println("Radi");
		DeclineAndRemove(table.getSelectionModel().getSelectedItem().getIme(),table.getSelectionModel().getSelectedItem().getPrezime(),
				table.getSelectionModel().getSelectedItem().getKor_ime(),table.getSelectionModel().getSelectedItem().getPassword());
		
		
	    table.getItems().remove(table.getSelectionModel().getSelectedItem());
		
		}else {
			System.out.println("Radi3");
				PickARow.setVisible(true);
			}
		}
		
		else {
			empty.setVisible(true);
			System.out.println("prazno");
			// PickARow.setVisible(true);
		}
	System.out.println("DC one by one");
		
		
	}
	
	
	public void AcceptAll() {
		
		
	System.out.println("Evo me Accept All");

	if(list.isEmpty()) {
		empty.setVisible(true);
		System.out.println("prazno");
		
			
		}
			else {
				for(UsersAll user: list) {
					  System.out.println("Evo me 5");
						SaveAndRemove(user.getIme(), user.getPrezime(), user.getKor_ime(), user.getPassword());}
			
				table.getItems().clear();
			}
	}
	
	
	public void DeclineAll() {
		
		System.out.println("Evo me Decline All");

		if(list.isEmpty()) {
			empty.setVisible(true);
			System.out.println("prazno");
			
				
			}
				else {
					for(UsersAll user: list) {
						  System.out.println("Evo me 7");
						  DeclineAndRemove(user.getIme(), user.getPrezime(), user.getKor_ime(), user.getPassword());
							
					}
					table.getItems().clear();
				}

	}
	
	
	
	
	public void Bezze() {
		/*System.out.println("Ime je: "+ table.getSelectionModel().getSelectedItem().getIme());
		System.out.println("Prezime je: "+ table.getSelectionModel().getSelectedItem().getPrezime());
		System.out.println("Kor_ime je: "+ table.getSelectionModel().getSelectedItem().getKor_ime());
		System.out.println("Password je: "+ table.getSelectionModel().getSelectedItem().getPassword());
		System.out.println("Bezze");*/
	}
	
	public void PendingClick() {

		/* FXMLLoader loader=new FXMLLoader();
	        loader.setLocation(getClass().getResource("sample2.fxml"));
	        try {
				loader.load();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        Controller sn=loader.getController();
	        sn.setBlin1();
	        Parent p=loader.getRoot();  
	        Stage window=new Stage();
	        window.setScene(new Scene(p));
	        window.setTitle("dfd");
	        window.show();*/
	/*	FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
		try {
			Parent root = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Controller dac = (Controller) loader.getController();
		logoutadmin=dac.setBlin1();
		logoutadmin.setText("logout");
		Parent p=this.containerMainItemsHOME;
		try {
			p = loader.load();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} */
		
		
	        
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("sample2.fxml"));
		try {
			System.out.println("Button controller");
			AnchorPane b2 = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Controller controller = (Controller)loader.getController();
		controller.setBlin1();*/
		
		
		
		
		Pending.setDisable(true);
		Accept.setVisible(true);
		Decline.setVisible(true);
		AcceptAll.setVisible(true);
		DeclineAll.setVisible(true);
		System.out.println("Evo me PENDING");
	
	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
int i=0;
	try {
	
		con=getConnection();
		ps = con.prepareStatement("SELECT Kor_ime,Password,Ime, Prezime, Activate FROM projekat.iznajmljivac WHERE Activate=0");
		rs = ps.executeQuery();
		
		while(rs.next())
		  {	// System.out.println("Evo me"); 
		
		/*  System.out.println("Password je: "+rs.getString("Password"));
		  System.out.println("user name je: "+rs.getString("Kor_ime"));
		  System.out.println("Pass2 je: "+uPasswordLIN.getText());
		  System.out.println("user name2 je: "+uNameLIN.getText());
		  System.out.println("Evo me6"); */
		  
			System.out.println("EVO ME: "+rs.getString("Kor_ime"));
		UsersAll a= new UsersAll(rs.getString("Kor_ime"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("Password"), 0);
		 list.add(a);
			System.out.println("EVOOOO MEE!!!: "+(list.get(i++)).getIme());
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
		
		
		
		
		
		KOR_IME.setCellValueFactory(new PropertyValueFactory<UsersAll, String>("kor_ime"));
		IME.setCellValueFactory(new PropertyValueFactory<UsersAll, String>("ime"));
		PREZIME.setCellValueFactory(new PropertyValueFactory<UsersAll, String>("prezime"));
		PASSWORD.setCellValueFactory(new PropertyValueFactory<UsersAll, String>("password"));
		ACTIVATE.setCellValueFactory(new PropertyValueFactory<UsersAll, String>("activate"));
		 table.setItems(list);
		
	
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	
	
}