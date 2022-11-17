package application;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class probaController {

	
	@FXML 
	private ImageView probaImage;
	
	@FXML
	private Button probaButton;
	
	
	public void proba() {
		
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection con=null;
		int iznajmljivac_true=0;
		try {
		
			con=getConnection();
			ps = con.prepareStatement("SELECT ID,slika FROM projekat.Nekretnina WHERE ID=62");
			rs = ps.executeQuery();
			int a=0;
			while(rs.next())
			  {	
			try {
		Blob blob=rs.getBlob(2);
		InputStream inputStream=blob.getBinaryStream();
		Image image=new Image(inputStream);
		probaImage.setImage(image);
					
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
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}
	
	
	
}