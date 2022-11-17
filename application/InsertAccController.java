package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

public class InsertAccController implements Initializable {

	
 @FXML
 private MenuButton TOA;
 
 @FXML
 private TextField InsertLoc;
 
 @FXML 
 private Button InsertButton;
 
 @FXML
 private Label LabeLa;
 @FXML
 private Label emptyLabel;
 
 @FXML
 private JFXComboBox<String> InsertComboBox;
 
 @FXML
 private Button LogOut;
 
 @FXML
 private TextField insertAcc;
 
 @FXML
 private TextField insertCapacity;
 
 @FXML
 private TextField insertPrice;
 
 @FXML
 private Button insertPicture;
 
 @FXML
 private MenuButton insertOP;
 
 @FXML
	private CheckMenuItem OPIAY;
 
	
	@FXML
	private CheckMenuItem OPIAN;
 
 @FXML
 private TextField insertCF;
 
 @FXML
 private TextField insertSurface;
 
 @FXML
 private TextField insertDB;
 
 @FXML
 private RadioButton insertWIFI;
 @FXML
 private RadioButton insertTV;
 @FXML
 private RadioButton insertAC;
 @FXML
 private RadioButton insertBalcony;
 @FXML
 private RadioButton insertBathroom;
 @FXML
 private RadioButton insertKitchen;
 @FXML
 private RadioButton insertParking;
 @FXML
 private RadioButton insertPool;
 
 
 File f;
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
 
 private int counter2=0;
public void OPIAclick() {
		 if(OPIAY.isSelected() ) {
			OPIAN.setDisable(true);
			counter2=0;
			
		} if(OPIAN.isSelected()) {
			
			OPIAY.setDisable(true);
		counter2=0;
		}
		 if(!OPIAY.isSelected() && !OPIAN.isSelected()) {
			//emptyLabel.setText("OPIA is not selected");
			//emptyLabel.setVisible(true);
			OPIAN.setDisable(false);
			OPIAY.setDisable(false);
			counter2=1;
			System.out.println("Counter2 je"+counter2);
		}
		 if(OPIAY.isSelected() || OPIAN.isSelected()) {
			//	emptyLabel.setText("OPIA is not selected");
				//emptyLabel.setVisible(false);
				//OPIAN.setDisable(false);
				// OPIAY.setDisable(false);
				 counter2=0;
			}
		
		
	}
 

// ObservableList<City> cities=FXCollections.observableArrayList();
 HashMap<String,Integer> cities = new HashMap<String, Integer>();
 HashMap<String, Integer> smjestaj= new HashMap<String, Integer>();
 HashMap<String, Integer> characteristics= new HashMap<String, Integer>();
 ObservableList<Integer> postoji = FXCollections.observableArrayList();
 ObservableList<String> acctype = FXCollections.observableArrayList();
 ObservableList<String> locations = FXCollections.observableArrayList();
 private  int i=0;
 
public void ComboClick() {
	/*System.out.println("ACC type:"+acctype.get(0));
	System.out.println("Evo me combobox");
	InsertComboBox.setItems(acctype);
	if(i>0 && ((i%2)!=0)) {
		if(InsertComboBox.getValue()!=null) {
	String parts[] = InsertComboBox.getValue().split("-");
	
	System.out.println("Odabrana je: "+parts[0]);
	LabeLa.setVisible(false);
		}
}
	++i;*/
	
	
	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;

	try {
	
		con=getConnection();
		ps = con.prepareStatement("SELECT Ime,ID FROM projekat.Tip_nekretnine");
		rs = ps.executeQuery();
		
		while(rs.next())
		  {	 
			smjestaj.put(rs.getString("Ime"),rs.getInt("ID"));
			
		
		acctype.add(rs.getString("Ime"));

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
InsertComboBox.setItems(acctype);

if(i>0 && ((i%2)!=0)) {
	if(InsertComboBox.getValue()!=null) {
// String parts[] = InsertComboBox.getValue().split("-");

System.out.println("Evo me napokon2:"+smjestaj.get(InsertComboBox.getValue()));
LabeLa.setVisible(false);
	}
}
++i;	
	
}




	
	public void InsertButtonClicked() throws FileNotFoundException {
		
		
		

		
		
		
		/*******************************/
		
		
		System.out.println("Evo me Combo2CLick");
	int wifi=0,tv=0,balkon=0,kuhinja=0,parking=0,ac=0,kupatilo=0;
	int counter=0;
	
	if(InsertComboBox.getValue()==null || InsertComboBox.getValue().isEmpty()) {
		
		 emptyLabel.setText("\tAccomodation type is missing!");
		 System.out.println("Fali smjestaj");
			emptyLabel.setVisible(true);
			counter=1;	
	}
/*	for(int i=0;i<locations.size();++i) {
		if(InsertLoc.getText()!=locations.get(i)) {
		//counter=1;
		 emptyLabel.setText("\tgradname is missing!");
		 System.out.println("Grad name is missing: "+locations.get(i));
		}
		System.out.println("InsertLoc.getText(): "+InsertLoc.getText());
		System.out.println("Grad name is missing: "+counter);
	}
		*/
	
	
	 if(insertAcc.getText().isEmpty()) {
		 emptyLabel.setText("\tAccomodation name is missing!");
			emptyLabel.setVisible(true);
			counter=1;	 
			System.out.println("Acc name");
}
	else if(InsertLoc.getText().isEmpty()) {
		emptyLabel.setText("\tLocation is missing!");
		emptyLabel.setVisible(true);
		counter=1;		
		System.out.println("Location name");
}
	else if(insertCapacity.getText().isEmpty()) {
		emptyLabel.setText("\tCapacity is missing!");
		emptyLabel.setVisible(true);
		counter=1;			
		System.out.println("Capacity name");
}
	else if(insertPrice.getText().isEmpty()) {
		emptyLabel.setText("\t\tPrice is missing!");
		emptyLabel.setVisible(true);
		counter=1;			
		System.out.println("Price name");
}
	 
	 /************ OVO PROMIJENITI  ***********/
	else if(insertPicture.getText().equals("Add image")) {
		emptyLabel.setText("\t\tPicture is missing!");
		emptyLabel.setVisible(true);
		counter=1;	
		System.out.println("Picture name");
}
	
	else if(insertOP.getText().isEmpty()) {
		emptyLabel.setText("\t\tOnline payment is missing!");
		emptyLabel.setVisible(true);
		counter=1;		
		System.out.println("OP name");
}
	else if(insertCF.getText().isEmpty()) {
		
		emptyLabel.setText("\t\tCancellation fee is missing!");
		emptyLabel.setVisible(true);
		counter=1;			
		
}
	else if(insertSurface.getText().isEmpty()) {
		emptyLabel.setText("\t\tSurface is missing!");
		emptyLabel.setVisible(true);
		counter=1;	
		System.out.println("surface name");
}
	
	else if(insertDB.getText().isEmpty()) {
		emptyLabel.setText("\t\tDescription is missing!");
		emptyLabel.setVisible(true);
		counter=1;			
		System.out.println("db name");
}
	
	
else if((!isInteger(insertPrice.getText())) || (Integer.parseInt(insertPrice.getText())<0) ) {
emptyLabel.setText("\tPlease enter a positive number");
		emptyLabel.setVisible(true);
		counter=1;
		System.out.println("insert price positive number ");
	}
else if((!isInteger(insertCapacity.getText())) || (Integer.parseInt(insertCapacity.getText())<0) ) {
emptyLabel.setText("\tPlease enter a positive number");
		emptyLabel.setVisible(true);
		counter=1;
		System.out.println("insert capacity positive number ");
	}
else if((!isInteger(insertSurface.getText())) || (Integer.parseInt(insertSurface.getText())<0) ) {
emptyLabel.setText("\tPlease enter a positive number");
		emptyLabel.setVisible(true);
		counter=1;
		System.out.println("insert surface positive number ");
	}
else if((!isInteger(insertCF.getText())) || (Integer.parseInt(insertCF.getText())<0) ) {
emptyLabel.setText("\tPlease enter a positive number");
		emptyLabel.setVisible(true);
		counter=1;
		System.out.println("insert cf positive number ");
	}
	 
	 
OPIAclick();
	 
	if(counter2==1) {
		emptyLabel.setText("OPIA is not selected");
		emptyLabel.setVisible(true);
	}
	System.out.println("Counter2 je"+counter2);
	System.out.println("Counter1 je"+counter);
	if(counter==0 && counter2==0) {
	
	System.out.println("Evo me c");
	
	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
	int ima5=0;
	int generatedKey = 0;
	int generatedKey2=0;
	try {
	
		con=getConnection();
		ps = con.prepareStatement("SELECT Naziv,ID FROM projekat.Lokacija");
		rs = ps.executeQuery();
		
		while(rs.next())
		  {	 
			cities.put(rs.getString("Naziv"),rs.getInt("ID"));
		locations.add(rs.getString("Naziv"));

		  }
		
		rs.close();	
		ps.close();
		
		for(int i=0;i<locations.size();++i) {
			System.out.println("Postojeći gradovi: "+locations.get(i));
			if(InsertLoc.getText().equals(locations.get(i))) {
				
				ima5=1;
			}}
		if(ima5==0) {
		
		
		ps = con.prepareStatement("INSERT INTO projekat.Lokacija (Naziv) VALUES (?)",Statement.RETURN_GENERATED_KEYS);
		System.out.println("NOVA LOKACIJA DODANA U BAZU. ");
		 ps.setString(1,InsertLoc.getText());
			
		ps.execute();
		rs = ps.getGeneratedKeys();
		if(rs.next()) {
			
			generatedKey2=rs.getInt(1);	
			
		}
		cities.put(InsertLoc.getText(), generatedKey2);
		locations.add(InsertLoc.getText());
		ps.close();
		
		}else {
			System.out.println("Zapis već postoji u bazi!");
		}
		
		
		
		//  con=getConnection();
		
		
		ps = con.prepareStatement("SELECT ID,Ime FROM Karakteristike");
		rs = ps.executeQuery();
		
		while(rs.next())
		  {	 
			characteristics.put(rs.getString("Ime"),rs.getInt("ID"));
	//	locations.add(rs.getString("Naziv"));

		  }
		rs.close();	
		ps.close();
		
		
		System.out.println("Evo me napokon:"+cities.get(InsertLoc.getText()));
		System.out.println("Evo me u InsertButtonClick:"+smjestaj.get(InsertComboBox.getValue()));

		System.out.println("ID User: "+CurrentUser.cuser.values().toArray()[0]);
		System.out.println("Vel mape:"+CurrentUser.cuser.size());
//		System.out.println("Ime User: "+CurrentUser.cuser.get(CurrentUser.cuser.keySet().toArray()[0]));
		
		
		if(insertWIFI.isSelected()) {
			wifi=1;
		
			postoji.add(characteristics.get("wifi"));
			System.out.println("Wifi is checked");
		}
	
		if(insertTV.isSelected()) {
			tv=1;
			
			postoji.add(characteristics.get("tv"));
			System.out.println("TV is checked");
			
		}
		if(insertAC.isSelected()) {
			ac=1;
			
			postoji.add(characteristics.get("klima"));
			System.out.println("AC is checked");
		}
		if(insertBalcony.isSelected()) {
			balkon=1;
			
			postoji.add(characteristics.get("balkon"));
			System.out.println("Balcony is checked");
		}
		if(insertBathroom.isSelected()) {
			kupatilo=1;
			
			postoji.add(characteristics.get("kupaonica"));
			System.out.println("Bathroom is checked");
		}
		if(insertKitchen.isSelected()) {
			kuhinja=1;
			postoji.add(characteristics.get("kuhinja"));
			System.out.println("Kitchen is checked");
		}
		if(insertParking.isSelected()) {
			parking=1;
			postoji.add(characteristics.get("parking"));
			System.out.println("Parking is checked");
		}
		if(insertPool.isSelected()) {
			parking=1;
			postoji.add(characteristics.get("bazen"));
			System.out.println("Pool is checked");
		}
		
		ps = con.prepareStatement("INSERT INTO projekat.Nekretnina (ID_N,ID_L,cijena,opis,povrsina,broj_kreveta,slika,ID_I,OPIA,Accomodation_name,CF) VALUES (?,?,?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		
		
		InputStream is=new FileInputStream(new File(f.getAbsolutePath()));
		ps.setInt(1,smjestaj.get(InsertComboBox.getValue()));
		ps.setInt(2, cities.get(InsertLoc.getText()));
		ps.setInt(3, Integer.parseInt(insertPrice.getText()));
		ps.setString(4,insertDB.getText());
		ps.setInt(5, Integer.parseInt(insertSurface.getText()));
		ps.setInt(6, Integer.parseInt(insertCapacity.getText()));
		ps.setBlob(7,is);
		System.out.println("TU sam1");
		ps.setInt(8, (int) CurrentUser.cuser.values().toArray()[0]);
		if(OPIAY.isSelected()) {
		ps.setInt(9, 1);
		}else {
			ps.setInt(9, 0);	
		}
		
		ps.setString(10,insertAcc.getText());
		if(insertCF.getText().isEmpty()) {
			 ps.setInt(11,0);
		}else {
				ps.setInt(11, Integer.parseInt(insertCF.getText()));	
			}
		
		System.out.println("TU sam2");
		ps.execute();
		 rs = ps.getGeneratedKeys();
		 
		 if (rs.next()) {
			    generatedKey = rs.getInt(1);
			}
			ps.close();
			rs.close();
			
			
			
			if(postoji.size()>0) {
			ps = con.prepareStatement("INSERT INTO projekat.Nekretnina_Karakteristike (ID_N,ID_K) VALUES (?,?)");
			
			for(int i=0;i<postoji.size();++i) {
			ps.setInt(1, generatedKey);
			System.out.println("TU sam1");
			ps.setInt(2,postoji.get(i));
			ps.execute();
			System.out.println("Postoji.get(i): "+postoji.get(i));
			}
			postoji.clear();
			System.out.println("TU sam2");
			
			
			}
				ps.close();
				rs.close();
			
			
			
			
			
			con.close();
	 }catch ( java.lang.NullPointerException | SQLException e) {
		// TODO Auto-generated catch block
		 
		 if(!postoji.isEmpty()) {
		postoji.clear();
		 }
		// e.printStackTrace();
		 emptyLabel.setText("You forgot the image!");
		 emptyLabel.setVisible(true);
	
		 if(e instanceof NullPointerException) {
			 System.out.println("DEsio se nullpointer");
		 }
		 
	}finally {
		 if(!postoji.isEmpty()) {
				postoji.clear();
				 }
	    DataBaseConnectivity.closeAll(rs,ps, con);
	}

	}
	
	
	//System.out.println("Generated key is: "+generatedKey);
	
	
	 
	
	
	
	
	
	
	}
	
	
	
	public void LogOutClick() {
		
		CurrentUser.cuser.clear();
		System.out.println("Size of the map is: "+CurrentUser.cuser.size());
		
	}
	
 public void InsertBClick() {
	 System.out.println("NOVO22555");
	
	 System.out.println("Location click"+InsertLoc.getText());
		
 }
	
  public void TOAClick() {
	  System.out.println("EVO ME SEARCH");
	 //  System.out.println("Evo TOA:"+  );
  }
  
  public void LocClick() {
	  System.out.println("NOVO");
	  System.out.println("Location click"+InsertLoc.getText());
  }
  
  @FXML
  public void PictureClick() {
	  
	  
	FileChooser fc=new FileChooser();
	  fc.getExtensionFilters().add(new ExtensionFilter("Images", "*.jpeg"));
	   f=fc.showOpenDialog(null);
	  
	  if(f!=null) {
		  
		  insertPicture.setText("Selected File: "+f.getAbsolutePath());
	  }
	  
  }
  
  
  
  
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
//	InsertComboBox.setCellFactory(new PropertyValueFactory<City, String>("ime"));
	
}
	

private Connection getConnection() throws SQLException {
	Connection conn;
	conn = DataBaseConnectivity.getInstance().getConnection();
	return conn;
}
	
}