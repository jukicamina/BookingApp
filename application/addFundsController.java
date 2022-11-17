package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class addFundsController {

	@FXML
	private Button confirmSum;
	@FXML
	private TextField sumField;
	@FXML
	private Label positiveSum;
	private int balansK;
	
	private void getBalance() {
	

	PreparedStatement ps=null;
	ResultSet rs = null;
	Connection con=null;
	int i=0;
	int x=(int) CurrentUser.cuser.values().toArray()[0];
	try {

		con=getConnection();
	ps = con.prepareStatement("select Korisnik.Balance FROM Korisnik WHERE Korisnik.ID=?\n;");
//		ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
	ps.setInt(1, x);	
	rs = ps.executeQuery();
		
		while(rs.next())
		  {	
		 
			
			
		balansK=rs.getInt("Korisnik.Balance");

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
	
	
	public void confirmSumClicked() {
		getBalance();
		try {
		int y=Integer.parseInt(sumField.getText());
		if(y>=0) {
		PreparedStatement preparedStmt = null;
		Connection con=null;
		
		try {
			con=getConnection();
			/*preparedStmt = con.prepareStatement("UPDATE projekat.iznajmljivac SET Activate = 1 where Kor_ime ="'lazar'");
			preparedStmt.executeUpdate();*/
			 String query = "UPDATE projekat.Korisnik SET Balance=? WHERE ID=? ";
			 preparedStmt = con.prepareStatement(query);
			 // preparedStmt.setInt(1, 1);
			 preparedStmt.setInt(1, balansK+Integer.parseInt(sumField.getText()));
			 preparedStmt.setInt(2, (int) CurrentUser.cuser.values().toArray()[0]);
			 preparedStmt.executeUpdate();
	         System.out.println("Balance is updated successfully......");
	      
			preparedStmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		    DataBaseConnectivity.closeAll(null,preparedStmt, con);
		   
		}	
		}
		else {
			positiveSum.setVisible(true);
			
		}
		}catch(Exception e) {
			positiveSum.setVisible(true);
			System.out.println("Not a number");
			
		}
	}
	
	
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	
}
