package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class UserController {

	@FXML
    private AnchorPane containerMainItemsUSER;
	
	
	
	@FXML
	private AnchorPane containerMainItemsHOMEUSER;
	
	//>>>>>odavdje<<<<<//
		@FXML
		private AnchorPane containerMainItemsInsideFrame;
		//>>>>>odavdje<<<<<//
	
	@FXML
	private Button bHOME;
	
	@FXML
	private Button LOGOUTUSER;

	@FXML
	private Pane Label;
	
	@FXML 
	private Label letterB;
	
	@FXML
	private Button ReservationsUS;
	
	@FXML
	private Button addFunds1;
	
	
	//>>>>>odavdje<<<<<//
	@FXML
	private Button searchLabel;

	public void searchLabelClick() {
		try {

			AnchorPane ap = FXMLLoader.load(getClass().getResource("searchAccomodation.fxml"));
			containerMainItemsUSER.getChildren().setAll(ap);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//>>>>>dovdje<<<<<//
	
	
	
	public void addFunds1Click() {
		
	try {
			
			System.out.println("EVO ME LOGIN CONTROLLER");
			AnchorPane ap= FXMLLoader.load(getClass().getResource("addFunds.fxml"));
			containerMainItemsUSER.getChildren().setAll(ap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			System.out.println("Prođe resUSclick");
		}
		
		
	}
	

	
	public void ReservationsUSClick() {
		
		try {
			
			System.out.println("EVO ME LOGIN CONTROLLER");
			AnchorPane ap= FXMLLoader.load(getClass().getResource("UsersReservations.fxml"));
			containerMainItemsUSER.getChildren().setAll(ap);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			System.out.println("Prođe resUSclick");
		}
		
	}
	

public void logoutUserClick() {
	
	CurrentUser.cuser.clear();
	
	System.out.println("Size of the map is: "+CurrentUser.cuser.size());
try {
		
		System.out.println("EVO ME LOGIN CONTROLLER");
		AnchorPane ap= FXMLLoader.load(getClass().getResource("sample2.fxml"));
		containerMainItemsHOMEUSER.getChildren().setAll(ap);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}