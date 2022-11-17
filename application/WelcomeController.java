package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class WelcomeController {

	
	@FXML
	Label nameLabel;
	
	
	@FXML
	private Button insertAcc;
	
	 @FXML 
	 private Pane Label;
	 
	 @FXML
	 private Button UaD;
	
	 @FXML 
	 private Label letterB;
	 
	@FXML
	private Button bllLOUT;
	@FXML
    private AnchorPane containerMainItemsWelcomeLL;
	
	@FXML
    private AnchorPane containerMainItemsll;
	
	@FXML
    private AnchorPane containerMainItemsWelcome;
	
	public void insertButtonClick() {
try {
	
			
			System.out.println("EVO ME WELCOME");
			AnchorPane ap= FXMLLoader.load(getClass().getResource("insert.fxml"));
			containerMainItemsll.getChildren().setAll(ap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	public void ReservationsClick() {
		try {
		System.out.println("EVO ME WELCOME");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("ReservationsLandLord.fxml"));
		containerMainItemsll.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
	}
	
	
public void UaDClick() {
		
	try {
		System.out.println("EVO ME WELCOME");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("UAD.fxml"));
		containerMainItemsll.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
		
		
		
	}
	

	
	
	public void displayName(String username) {
		nameLabel.setText("Hello" +username);
	}
	
	public void LandlordLOUT() {
		
		CurrentUser.cuser.clear();
		System.out.println("Size of the map is:"+CurrentUser.cuser.size());
		try {
			AnchorPane ap = FXMLLoader.load(getClass().getResource("sample2.fxml"));
			containerMainItemsWelcomeLL.getChildren().setAll(ap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
}