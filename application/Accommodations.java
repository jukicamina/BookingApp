package application;

import java.io.InputStream;
import java.sql.SQLException;

import java.sql.Blob;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Accommodations {

	private int ID;
	private String locationacc;
	private int priceacc;
	private int capacityacc;
	private int activateacc;
	
	
	public Accommodations(int id,String lokacija, int cijena, int br_kreveta,int active) {
		ID=id;
		locationacc=lokacija;
		priceacc=cijena;
		capacityacc=br_kreveta;
		activateacc=active;
		
		
	//	imageacc.setImage(image);
	
		
	}
	
	
	
	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	/*public ImageView getImageacc() {
		return imageacc;
	}
	public void setImageacc(ImageView imageacc) {
		this.imageacc = imageacc;
	}*/
	public String getLocationacc() {
		return locationacc;
	}
	public void setLocationacc(String locationacc) {
		this.locationacc = locationacc;
	}
	public int getPriceacc() {
		return priceacc;
	}
	public void setPriceacc(int priceacc) {
		this.priceacc = priceacc;
	}
	public int getCapacityacc() {
		return capacityacc;
	}
	public void setCapacityacc(int capacityacc) {
		this.capacityacc = capacityacc;
	}
	public int getActivateacc() {
		return activateacc;
	}
	public void setActivateacc(int activateacc) {
		this.activateacc = activateacc;
	}
	
	
	
	
	
	
	
	
	
}