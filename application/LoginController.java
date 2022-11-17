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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class LoginController {

	@FXML 
	private TextField uNameLIN;

	@FXML
	private   Button bLIN1;
	
	@FXML
	private TextField uPasswordLIN;
	
	@FXML
	private Button bLIN2;
	 @FXML
	 private AnchorPane containerMainItemsLOGINHOME;
	 @FXML
	 private AnchorPane containerMainItemsLogin;
	 
	 @FXML 
	 private Pane Label;
	 @FXML 
	 private Pane pane5;
	 @FXML 
	 private Label letterB;
	 
	 @FXML
	 private Button bHOME;
	 
	 @FXML
	 private Button bSU1;
	 @FXML
	 private Button bHOME1;
	
	
	
	
	@FXML
	private AnchorPane containerMainItemsLOGIN;
	
	private Parent root;
	private Scene scene;
	private Stage stage;

	
	
public void SU1ButtonClick() {
		
		try {
			
		AnchorPane ap=FXMLLoader.load(getClass().getResource("signup.fxml"));
		containerMainItemsLOGINHOME.getChildren().setAll(ap);
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

public void LN1ButtonClick() {
	try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("login.fxml"));
		containerMainItemsLOGINHOME.getChildren().setAll(ap);
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	public void HOME1ButtonClick() {
		
		try {
			
			AnchorPane ap=FXMLLoader.load(getClass().getResource("sample2.fxml"));
			containerMainItemsLOGINHOME.getChildren().setAll(ap);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void UNAMELIN() {
		// System.out.println("evo");
	}
	
		
		
		public void bLIN2Clicked() {
			
			PreparedStatement ps=null;
			ResultSet rs = null;
			Connection con=null;
			int iznajmljivac_true=0;
			try {
				
			
				con=getConnection();
				ps = con.prepareStatement("SELECT ID,Kor_ime,Password, Activate FROM projekat.iznajmljivac");
				rs = ps.executeQuery();
				int a=0;
				while(rs.next())
				  {	// System.out.println("Evo me"); 
				
				/*  System.out.println("Password je: "+rs.getString("Password"));
				  System.out.println("user name je: "+rs.getString("Kor_ime"));
				  System.out.println("Pass2 je: "+uPasswordLIN.getText());
				  System.out.println("user name2 je: "+uNameLIN.getText());
				  System.out.println("Evo me6"); */
				  
				  
					if(rs.getString("Kor_ime").equals(uNameLIN.getText()) && rs.getString("Password").equals(uPasswordLIN.getText()) && rs.getInt("Activate")==1)
						{
						// System.out.println("Evo me7");
						a=1;
						
						try {
							
							
							
							// System.out.println("Evo me5");
							CurrentUser.cuser.put(rs.getString("Kor_ime"),rs.getInt("ID"));
							AnchorPane  ap= FXMLLoader.load(getClass().getResource("Welcome.fxml"));
							containerMainItemsLOGINHOME.getChildren().setAll(ap);
							iznajmljivac_true=1;
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				  
						// System.out.println("Yes");	
						}
					
					
				
				  }
				if(a==0) {
					
					
					try {
						
						
						
						// System.out.println("Evo me5");
						AnchorPane  ap= FXMLLoader.load(getClass().getResource("notloggedin.fxml"));
						containerMainItemsLogin.getChildren().setAll(ap);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
			
			if(iznajmljivac_true==0) {
			
			PreparedStatement ps2=null;
			ResultSet rs2 = null;
			Connection con2=null;
			try {
			
				con2=getConnection();
				ps2 = con2.prepareStatement("SELECT ID,Kor_ime,Password, Activate FROM projekat.Korisnik");
				rs2 = ps2.executeQuery();
				int b=0;
				while(rs2.next())
				  {	// System.out.println("Evo me"); 
				
				/*  System.out.println("Password je: "+rs.getString("Password"));
				  System.out.println("user name je: "+rs.getString("Kor_ime"));
				  System.out.println("Pass2 je: "+uPasswordLIN.getText());
				  System.out.println("user name2 je: "+uNameLIN.getText());
				  System.out.println("Evo me6"); */
				  
				  
					if(rs2.getString("Kor_ime").equals(uNameLIN.getText()) && rs2.getString("Password").equals(uPasswordLIN.getText()) && rs2.getInt("Activate")==1)
						{
						// System.out.println("Evo me7");
						b=1;
						
						try {
						
							if(rs2.getString("Kor_ime").equals("admin") && rs2.getString("Password").equals("admin")) {
					
								CurrentUser.cuser.put(rs2.getString("Kor_ime"),rs2.getInt("ID"));
								AnchorPane  ap= FXMLLoader.load(getClass().getResource("okviradmin.fxml"));
								containerMainItemsLOGINHOME.getChildren().setAll(ap);
								
							  
							}
							else {
							
							// System.out.println("Evo me5");
								CurrentUser.cuser.put(rs2.getString("Kor_ime"),rs2.getInt("ID"));
							AnchorPane  ap= FXMLLoader.load(getClass().getResource("userokvir.fxml"));
							containerMainItemsLOGINHOME.getChildren().setAll(ap);
							
							 
							
							
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				  
						// System.out.println("Yes");	
						}
					
					
				
				  }
				if(b==0) {
					
					
					try {
						
						
						
						// System.out.println("Evo me5");
						AnchorPane  ap= FXMLLoader.load(getClass().getResource("notloggedin.fxml"));
						containerMainItemsLogin.getChildren().setAll(ap);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
			
					
		//	System.out.println("Ime je "+uNameLIN.getText()+" "+"Password je "+uPasswordLIN.getText());
		}
					

private Connection getConnection() throws SQLException {
	Connection conn;
	conn = DataBaseConnectivity.getInstance().getConnection();
	return conn;
}
	

}
		
		
	
	
	

