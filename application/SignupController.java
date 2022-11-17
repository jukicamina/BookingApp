package application;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SignupController {
	
	
	@FXML
	private TextField NameSU;
	
	@FXML
    private AnchorPane containerMainItems;
	
	
	 @FXML 
	 private Pane Label;
	 @FXML 
	 private Pane pane5;
	 @FXML 
	 private Label letterB;
	 
	 @FXML
	 private Button bHOME;
	 
	 @FXML
	 private Button bLIN1;
	 @FXML
	 private Button bHOME1;
	
	
	@FXML
	private AnchorPane containerMainItemsSIGNUPHOME;
	
	@FXML
	private TextField LNameSU;
	
	@FXML  
	private Button bSU1;
	
	@FXML
	private TextField PNumberSU;
	@FXML
	private TextField AOLSU;
	
	@FXML
	private TextField DOBSU;
	
	@FXML
	private TextField EmailSU;
	
	@FXML
	private TextField UNameSU;
	
	@FXML
	private TextField PasswordSU;
	
	@FXML
	private TextField BalanceSU;
	
	@FXML
	private MenuButton TOUSU;
	
	@FXML
	private CheckMenuItem user;
	
	@FXML
	private CheckMenuItem landlord;
	
	@FXML
	private Button bSU2;
	
	@FXML
	private MenuButton MB;
	
	@FXML
	private CheckMenuItem action1;
	
	@FXML
	private CheckMenuItem action2;
	
	@FXML 
	private Label taken;
	
	HashMap<String,Integer> cities = new HashMap<String, Integer>();
	ObservableList<String> locations = FXCollections.observableArrayList();
	
	final static String DATE_FORMAT = "yyyy-MM-dd";

	private static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	              return matchesDatePattern(date);
	        } catch (ParseException e) {
	            return false;
	        }
	}
	private static boolean matchesDatePattern(String dateString) {
	    return dateString.matches("^\\d+\\-\\d+\\-\\d+");
	}
	
public void SU1ButtonClick() {
		
		try {
			
		AnchorPane ap=FXMLLoader.load(getClass().getResource("signup.fxml"));
		containerMainItemsSIGNUPHOME.getChildren().setAll(ap);
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public void LN1ButtonClick() {
		try {
			
			System.out.println("EVO ME LOGIN CONTROLLER");
			AnchorPane ap= FXMLLoader.load(getClass().getResource("login.fxml"));
			containerMainItemsSIGNUPHOME.getChildren().setAll(ap);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public void HOME1ButtonClick() {
		
		try {
			
			AnchorPane ap=FXMLLoader.load(getClass().getResource("sample2.fxml"));
			containerMainItemsSIGNUPHOME.getChildren().setAll(ap);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	 private Connection getConnection() throws SQLException {
			Connection conn;
			conn = DataBaseConnectivity.getInstance().getConnection();
			return conn;
		}
	 
	 
	 private int ima2=0;
	 public void userClick() {
			if(user.isSelected()) {
				landlord.setDisable(true);
				ima2=0;
			}
			if(landlord.isSelected()) {
				user.setDisable(true);
ima2=0;
			}
			if(landlord.isSelected() || user.isSelected()) {
				
				ima2=0;
			}
			if(!user.isSelected() && !landlord.isSelected()) {
				taken.setText("Type of user is missing");
				taken.setVisible(true);
				user.setDisable(false);
				landlord.setDisable(false);
				ima2=1;
			}
			 
		 }

	 
	 
	 ArrayList<String> existing=new ArrayList<String>();
	 private void ifExists() {
		 
		 PreparedStatement ps=null;
			ResultSet rs = null;
			Connection con=null;
			
			try {
			
				con=getConnection();
				ps = con.prepareStatement("SELECT ID,Kor_ime,Password, Activate FROM projekat.iznajmljivac");
				rs = ps.executeQuery();
				int a=0;
				while(rs.next())
				  {			
						existing.add(rs.getString("Kor_ime"));
				
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
			
			PreparedStatement ps2=null;
			ResultSet rs2 = null;
			Connection con2=null;
			try {
			
				con2=getConnection();
				ps2 = con2.prepareStatement("SELECT ID,Kor_ime,Password, Activate FROM projekat.Korisnik");
				rs2 = ps2.executeQuery();
				int b=0;
				while(rs2.next())
				  {	 

					existing.add(rs2.getString("Kor_ime"));		
				
				  }
			
				
				
				rs2.close();	
				ps2.close();
				con2.close();
			 }catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
			    DataBaseConnectivity.closeAll(rs2,ps2, con2);
			} 
		 
		 
		 
	 }
	
	 public void bSU2Click() {
		 
		 

			PreparedStatement ps1=null;
			ResultSet rs1 = null;
			Connection con1=null;
			int ima5=0;
			int generatedKey = 0;
			int generatedKey2=0;
			try {
			
				con1=getConnection();
				ps1 = con1.prepareStatement("SELECT Naziv,ID FROM projekat.Lokacija");
				rs1 = ps1.executeQuery();
				
				while(rs1.next())
				  {	 
					cities.put(rs1.getString("Naziv"),rs1.getInt("ID"));
				locations.add(rs1.getString("Naziv"));

				  }
				
				rs1.close();	
				ps1.close();
				
				for(int i=0;i<locations.size();++i) {
					System.out.println("Postojeći gradovi: "+locations.get(i));
					if(AOLSU.getText().equals(locations.get(i))) {
						
						ima5=1;
					}}
				if(ima5==0) {
				
				
				ps1 = con1.prepareStatement("INSERT INTO projekat.Lokacija (Naziv) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
				System.out.println("NOVA LOKACIJA DODANA U BAZU. ");
				 ps1.setString(1,AOLSU.getText());
					
				ps1.execute();
				rs1 = ps1.getGeneratedKeys();
				if(rs1.next()) {
					
					generatedKey2=rs1.getInt(1);	
					
				}
				cities.put(AOLSU.getText(), generatedKey2);
				locations.add(AOLSU.getText());
				ps1.close();
				
				}else {
					System.out.println("Zapis već postoji u bazi!");
				}
				rs1.close();	
				ps1.close();

				con1.close();
		 }catch ( java.lang.NullPointerException | SQLException e) {
			// TODO Auto-generated catch block
			 
			
		
			 if(e instanceof NullPointerException) {
				 System.out.println("DEsio se nullpointer");
			 }
			 
		}finally {
			 
		    DataBaseConnectivity.closeAll(rs1,ps1, con1);
		}
				
		  
		
	ifExists();	
	int ima=0;
	for(String exist: existing) {
		if(exist.equals(UNameSU.getText())) {
			System.out.println("Postoji3337!!!");
			ima=1;
			taken.setText("\tOops, username already exists");
			taken.setVisible(true);
			
		}
		
		else if(NameSU.getText().isEmpty()) {
			taken.setText("\t\tName is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(LNameSU.getText().isEmpty()) {
			taken.setText("\tLast name is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(PNumberSU.getText().isEmpty()) {
			taken.setText("\tPhone number is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(AOLSU.getText().isEmpty()) {
			taken.setText("\t\tAdress is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(DOBSU.getText().isEmpty()) {
			taken.setText("\t\tDate of birth is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(isDateValid(DOBSU.getText())==false) {
			taken.setText("\t\tEnter a valid date!");
			taken.setVisible(true);
			ima=1;
			
		}
		else if(EmailSU.getText().isEmpty()) {
			taken.setText("\t\tEmail is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(UNameSU.getText().isEmpty()) {
			taken.setText("\t\tUsername is missing!");
			taken.setVisible(true);
			ima=1;			
}
		else if(PasswordSU.getText().isEmpty()) {
			taken.setText("\t\tPassword is missing!");
			taken.setVisible(true);
			ima=1;			
}
		
		else if(BalanceSU.getText().isEmpty()) {
			taken.setText("\t\tBalance is missing!");
			taken.setVisible(true);
			ima=1;			
}
		
		
else if((!isInteger(BalanceSU.getText())) || (Integer.parseInt(BalanceSU.getText())<0) ) {
	taken.setText("\tPlease enter a positive number");
			taken.setVisible(true);
			ima=1;
		}
	
	}
	userClick();
	
		 if(ima==0 && ima2==0) {
			 System.out.println("Number is: "+Integer.parseInt(BalanceSU.getText()));
				taken.setVisible(false);
		 if(user.isSelected()) {
			PreparedStatement preparedStmt = null;
			  Connection con=null;
			  try {
				//  System.out.println("EVO ME: ");
				//  System.out.println("EVO ME: "+NameSU.getText());
					con=getConnection();
					
					
						
					preparedStmt = con.prepareStatement("INSERT INTO projekat.Korisnik (Ime,Prezime,Password,Kor_ime,Datum_rod,Balance,Email,Adresa_stanovanja,BrojTelefona) VALUES (?,?,?,?,?,?,?,?,?)");
					//System.out.println("EVO ME: "+NameSU.getText());
					 preparedStmt.setString(1, NameSU.getText() );
					preparedStmt.setString(2, LNameSU.getText() );
					preparedStmt.setString(3, PasswordSU.getText() );
					
				preparedStmt.setString(4, UNameSU.getText() );
						
					
					preparedStmt.setString(5, DOBSU.getText() );
					preparedStmt.setInt(6,Integer.parseInt( BalanceSU.getText()));
					preparedStmt.setString(7, EmailSU.getText() );
					preparedStmt.setInt(8, cities.get(AOLSU.getText()) );
					preparedStmt.setString(9, PNumberSU.getText() );
					
					
				/*	 preparedStmt.setString(1,"Sara" );
						preparedStmt.setString(2, "Tokic" );
						preparedStmt.setString(3, "1234" );
						preparedStmt.setString(4, "sara" );
						preparedStmt.setString(5, "1999-07-31" );
						preparedStmt.setInt(6, 300 );
						preparedStmt.setString(7, "sara@gmail.com" );
						preparedStmt.setString(8, "gradacac" );
						preparedStmt.setString(9, "25698753" );*/
					preparedStmt.execute();
					preparedStmt.close();
					con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				    DataBaseConnectivity.closeAll(null,preparedStmt, con);
				}
			  }
		 else if (landlord.isSelected()) {
			 
		 
			 PreparedStatement preparedStmt = null;
			  Connection con=null;
			  try {
				//  System.out.println("EVO ME: ");
				//  System.out.println("EVO ME: "+NameSU.getText());
					con=getConnection();
					
					//int ima=0;
						
					preparedStmt = con.prepareStatement("INSERT INTO projekat.iznajmljivac (Ime,Prezime,Br_telefona,Adresa_stanovanja,Datum_rodenja,email,Balance,Password,Kor_ime) VALUES (?,?,?,?,?,?,?,?,?)");
					//System.out.println("EVO ME: "+NameSU.getText());
					/*for(String exist: existing) {
						if(exist.equals(UNameSU.getText())) {
							System.out.println("Postoji!!!");
							ima=1;
							// preparedStmt.execute();
							preparedStmt.close();
							con.close();
							
							}
					}*/
					//if(ima==0) {
					preparedStmt.setString(1, NameSU.getText() );
					preparedStmt.setString(2, LNameSU.getText() );
					preparedStmt.setString(3, PNumberSU.getText() );
					preparedStmt.setInt(4, cities.get(AOLSU.getText()) );
					preparedStmt.setString(5, DOBSU.getText() );
					preparedStmt.setString(6, EmailSU.getText() );
					preparedStmt.setInt(7,Integer.parseInt( BalanceSU.getText()));
					preparedStmt.setString(8, PasswordSU.getText() );
					
					
					
					preparedStmt.setString(9, UNameSU.getText() );
					preparedStmt.execute();
					preparedStmt.close();
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
				    DataBaseConnectivity.closeAll(null,preparedStmt, con);
				    taken.setText("You are successfully signed up");
				    taken.setVisible(true);
				  //taken.setTextFill(Color.color(0, 1, 0));
				}
			 
		 
		 
		 
		 
		 }
		 else {
			 
			 taken.setText("\tWhat type of user are you?");
		 taken.setVisible(true);
		 }
			
			   }
		 else {
			 System.out.println("Ne moze taj username");
		 }
	}
	 
	
	 }


		 
		 
	
	
		
	
	
	
	
	
	
