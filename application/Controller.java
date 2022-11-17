package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class Controller {

	static boolean startProgram = false;
	public static int a;

	@FXML
	private AnchorPane containerMainItems;

	@FXML
	private AnchorPane containerMainItemsHOME;
	/*
	 * @FXML private FontAwesomeIcon minimizebut;
	 */

	/*
	 * @FXML HomeScreenControler homeController;
	 * 
	 * @FXML NewEmailControler newemailController;
	 */

	@FXML
	private Button bLIN1;

	@FXML
	private Button bSU1;

	@FXML
	private Button bHOME;

	@FXML
	private Button bHOME1;

	@FXML
	private Pane Label;

	@FXML
	private Label letterB;

	public void setBlin1() {
		System.out.println("set bln1");
		bLIN1.setText("log out");
		System.out.println("set bln2");
	}

	public Controller() {

		System.out.println("EVO ME LOGIN CONTROLLER");

		/*
		 * try { AnchorPane ap; ap =
		 * FXMLLoader.load(getClass().getResource("sample2.fxml"));
		 * containerMainItems.getChildren().setAll(ap); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace();
		 */
	}

	public void LN1ButtonClick() {
		try {

			System.out.println("EVO ME LOGIN CONTROLLER");
			AnchorPane ap = FXMLLoader.load(getClass().getResource("login.fxml"));
			containerMainItemsHOME.getChildren().setAll(ap);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SU1ButtonClick() {

		try {

			AnchorPane ap = FXMLLoader.load(getClass().getResource("signup.fxml"));
			containerMainItemsHOME.getChildren().setAll(ap);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void HOME1ButtonClick() {

		try {

			AnchorPane ap = FXMLLoader.load(getClass().getResource("sample2.fxml"));
			containerMainItemsHOME.getChildren().setAll(ap);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * @FXML public void initialize() { typeLabel.getItems().addAll("Room",
	 * "Apartment", "Cottage", "House", "Villa", "Office Building", "Garage");
	 * typeLabel.setTooltip(new Tooltip("Pick a type of accomodation"));
	 * BooleanBinding bb = new BooleanBinding() { {
	 * super.bind(cityLabel.textProperty(), checkIn.valueProperty(),
	 * checkOut.valueProperty(), peopleNo.textProperty(), minPrice.textProperty(),
	 * maxPrice.textProperty(), typeLabel.valueProperty()); }
	 * 
	 * @Override protected boolean computeValue() { return
	 * (cityLabel.getText().isEmpty() || checkIn.getValue() == null ||
	 * checkOut.getValue() == null || peopleNo.getText().isEmpty() ||
	 * minPrice.getText().isEmpty() || maxPrice.getText().isEmpty() ||
	 * typeLabel.getValue() == null); } }; searchLabel.disableProperty().bind(bb); }
	 */

	/*************************************** faruk ********************************/

	@FXML
	private TextField cityLabel;
	@FXML
	private DatePicker checkIn;
	@FXML
	private DatePicker checkOut;
	@FXML
	private TextField peopleNo;
	@FXML
	private TextField minPrice;
	@FXML
	private TextField maxPrice;
	@FXML
	private ChoiceBox<String> typeLabel;
	@FXML
	private RadioButton wifi;
	@FXML
	private RadioButton tv;
	@FXML
	private RadioButton ac;
	@FXML
	private RadioButton parking;
	@FXML
	private RadioButton pool;
	@FXML
	private RadioButton bath;
	@FXML
	private RadioButton balcony;
	@FXML
	private RadioButton kitchen;
	@FXML
	private Button searchLabel;
	@FXML
	private AnchorPane containerMainItemsResults;
	public static Vector<Integer> id_vector = new Vector<>();

	@FXML
	public void initialize() {
		typeLabel.getItems().addAll("Room", "Apartment", "Cottage", "House", "Villa", "Office Building", "Garage");
		typeLabel.setTooltip(new Tooltip("Pick a type of accomodation"));
		BooleanBinding bb = new BooleanBinding() {
			{
				super.bind(cityLabel.textProperty(), checkIn.valueProperty(), checkOut.valueProperty(),
						peopleNo.textProperty(), minPrice.textProperty(), maxPrice.textProperty(),
						typeLabel.valueProperty());
			}

			@Override
			protected boolean computeValue() {
				return (cityLabel.getText().isEmpty() || checkIn.getValue() == null || checkOut.getValue() == null
						|| peopleNo.getText().isEmpty() || minPrice.getText().isEmpty() || maxPrice.getText().isEmpty()
						|| typeLabel.getValue() == null);
			}
		};
		searchLabel.disableProperty().bind(bb);
	}

	/*
	 * public void LN1ButtonClick() { try {
	 * 
	 * System.out.println("EVO ME LOGIN CONTROLLER"); AnchorPane ap =
	 * FXMLLoader.load(getClass().getResource("login.fxml"));
	 * containerMainItems.getChildren().setAll(ap);
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * public void SU1ButtonClick() {
	 * 
	 * try {
	 * 
	 * AnchorPane ap = FXMLLoader.load(getClass().getResource("signup.fxml"));
	 * containerMainItems.getChildren().setAll(ap);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } }
	 * 
	 * public void HOME1ButtonClick() {
	 * 
	 * try {
	 * 
	 * AnchorPane ap = FXMLLoader.load(getClass().getResource("sample2.fxml"));
	 * containerMainItemsHOME.getChildren().setAll(ap); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = DataBaseConnectivity.getInstance().getConnection();
		return conn;
	}

	public void searchLabelClick() {
		PreparedStatement prepStmt = null;
		Connection conn_other = null;
		try {
			conn_other = getConnection();
			// which city
			id_vector.clear();
			String city = cityLabel.getText();
			int townID = 0;
			city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
			prepStmt = conn_other.prepareStatement("SELECT ID FROM projekat.Lokacija WHERE Naziv LIKE ?");
			prepStmt.setString(1, city);
			ResultSet rs = prepStmt.executeQuery();
			if (rs.next()) {
				townID = rs.getInt("ID");
			} else {
				// Ako ne postoji taj grad, ima 16 gradova dodanih
				System.out.println("There are no accomodations in that town" + city);
				// ideja je da vrati na ponovnu pretragu ili neki novi fxml
			}
			prepStmt.close();
			// ID tip_nekretnine
			String type = typeLabel.getValue();
			int typeID = 0;
			prepStmt = conn_other.prepareStatement("SELECT ID FROM projekat.Tip_nekretnine WHERE Ime LIKE ?");
			prepStmt.setString(1, type);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				typeID = rs.getInt("ID");
			}

			// nubmer of beds
			int beds = Integer.parseInt(peopleNo.getText());
			// price range
			int min = Integer.parseInt(minPrice.getText());
			int max = Integer.parseInt(maxPrice.getText());

			// trazenje u tom gradu sa odredjenom cijenom i brojem kreveta
			prepStmt = conn_other.prepareStatement(
					"SELECT * FROM projekat.Nekretnina WHERE ID_N = ? AND ID_L = ? AND (cijena BETWEEN ? AND ?) AND broj_kreveta=? AND Active=1");
			prepStmt.setInt(1, typeID);
			prepStmt.setInt(2, townID);
			prepStmt.setInt(3, min);
			prepStmt.setInt(4, max);
			prepStmt.setInt(5, beds);

			rs = prepStmt.executeQuery();

			while (rs.next()) {
				id_vector.add(rs.getInt("ID"));
			}

			// id-evi koji ispunjavaju gornje kriterije su u id_vector
			// ****provjeriti da li se ulazi sa praznim vektorom u petlju za svaki izbor
			// WiFI // ID = 7
			Vector<Integer> withWiFi = new Vector<Integer>(id_vector);
			if (wifi.isSelected()) {
				for (int i : id_vector) {

					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=7 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withWiFi.remove(Integer.valueOf(i));
					}
				}
			}
			// ako je wifi vektor prazan, nema potrebe ostale karakteristike pretrazivat
			if (withWiFi.isEmpty() && wifi.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// TV // ID = 11
			Vector<Integer> withTV = new Vector<Integer>(withWiFi);
			// trazimo u smjestajima koji imaju wifi
			if (tv.isSelected()) {
				for (int i : withWiFi) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=11 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withTV.remove(Integer.valueOf(i));
					}
				}
			}
			if (withTV.isEmpty() && tv.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// AC // ID = 13
			Vector<Integer> withAC = new Vector<Integer>(withTV);
			if (ac.isSelected()) {
				for (int i : withTV) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=13 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withAC.remove(Integer.valueOf(i));
					}
				}
			}
			if (withAC.isEmpty() && ac.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Balcony // ID = 12
			Vector<Integer> withBalcony = new Vector<Integer>(withAC);
			if (balcony.isSelected()) {
				for (int i : withAC) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=12 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withBalcony.remove(Integer.valueOf(i));
					}
				}
			}
			if (withBalcony.isEmpty() && balcony.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Bathroom // ID = 9
			Vector<Integer> withBath = new Vector<Integer>(withBalcony);
			if (bath.isSelected()) {
				for (int i : withBalcony) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=9 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withBath.remove(Integer.valueOf(i));
					}
				}
			}
			if (withBath.isEmpty() && bath.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Kitchen // ID = 10
			Vector<Integer> withKitchen = new Vector<Integer>(withBath);
			if (kitchen.isSelected()) {
				for (int i : withBath) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=10 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withKitchen.remove(Integer.valueOf(i));
					}
				}
			}
			if (withKitchen.isEmpty() && kitchen.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Parking // ID = 8
			Vector<Integer> withParking = new Vector<Integer>(withKitchen);
			if (parking.isSelected()) {
				for (int i : withKitchen) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=8 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withParking.remove(Integer.valueOf(i));
					}
				}
			}
			if (withParking.isEmpty() && parking.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Pool // ID = 6
			Vector<Integer> withPool = new Vector<Integer>(withParking);
			if (pool.isSelected()) {
				for (int i : withParking) {
					prepStmt = conn_other.prepareStatement(
							"SELECT * FROM projekat.Nekretnina_Karakteristike WHERE ID_K=6 AND ID_N = ?");
					prepStmt.setInt(1, i);
					rs = prepStmt.executeQuery();
					if (!rs.next()) {
						withPool.remove(Integer.valueOf(i));
						// ------//
					}
				}
			}
			if (withPool.isEmpty() && pool.isSelected()) {
				System.out.println("No accomodations matching your criteria!");
				// Neka poruka u GUI-u
				// KRAJ
			}

			// Provjera da li je rezervisano u tom intervalu
			LocalDate from = checkIn.getValue();
			LocalDate to = checkOut.getValue();

			Vector<Integer> toRemove = new Vector<Integer>();
			for (int i : withPool) {
				prepStmt = conn_other
						.prepareStatement("SELECT * FROM projekat.Rezervacija WHERE ID_N=? AND kraj BETWEEN ? AND ? ");
				prepStmt.setInt(1, i);
				prepStmt.setDate(2, Date.valueOf(from));
				prepStmt.setDate(3, Date.valueOf(to));
				rs = prepStmt.executeQuery();
				if (rs.next()) {
					toRemove.add(Integer.valueOf(i));
					// -------------//
					if (withPool.size() == toRemove.size())
						break;
					continue;
				}
				prepStmt.close();

				prepStmt = conn_other.prepareStatement(
						"SELECT * FROM projekat.Rezervacija WHERE ID_N=? AND pocetak BETWEEN ? AND ? ");
				prepStmt.setInt(1, i);
				prepStmt.setDate(2, Date.valueOf(from));
				prepStmt.setDate(3, Date.valueOf(to));
				rs = prepStmt.executeQuery();
				if (rs.next()) {
					toRemove.add(Integer.valueOf(i));
					continue;
				}
				if (withPool.size() == toRemove.size()) {
					System.out.println("Room is already taken, try changing the date of your stay!");
					// Neka poruka u GUI-u
					// KRAJ
					break;
				}
			}
			for(Integer i : toRemove) {
				withPool.remove(i);
			}

			// ZA TESTIRANJE
			for (int i : withPool) {
				System.out.println("ID that match your criteria: " + i);
			}
			// ID_N nekretnina koje su ispunile sve navedene kriterije su u vektoru withPool
			// Kako izlistati? --- sample2 stari-unutrasnji okvir sa slikama
			id_vector = withPool;
			rs.close();
			prepStmt.close();
			conn_other.close();
			try {
				//>>>>>odavdje<<<<<//
				if (id_vector.isEmpty()) {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("accNotFound.fxml"));
					AnchorPane ap = fxmlLoader.load();
					containerMainItems.getChildren().setAll(ap);
				} else {

					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchResults.fxml"));
					AnchorPane ap = fxmlLoader.load();
					searchResultsController src = (searchResultsController) fxmlLoader.getController();
					src.listData(withPool, 0, from, to);
					containerMainItems.getChildren().setAll(ap);
				}
				//>>>>>dovdje<<<<<//
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnectivity.closeAll(null, prepStmt, conn_other);
		}
	}
}

/*
 * public void bLIN2Clicked() { System.out.println("HALOO6");
 * 
 * try {
 * 
 * AnchorPane ap= FXMLLoader.load(getClass().getResource("Welcome.fxml"));
 * containerMainItemsLOGIN.getChildren().setAll(ap); //
 * containerMainItemsHOME.getChildren().setAll(ap); // bLIN1.setDisable(false);
 * 
 * } catch (IOException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); }
 * 
 * System.out.println("HALOO");
 * 
 * 
 * }
 * 
 * 
 * private Connection getConnection() throws SQLException { Connection conn;
 * conn = DataBaseConnectivity.getInstance().getConnection(); return conn; }
 * 
 * }
 */

/*
 * private static Controller singlePattern = null;
 * 
 * public static Controller getInstance() { if (singlePattern == null) {
 * singlePattern = new Controller(); } return singlePattern; }
 * 
 * }
 * 
 * 
 */
